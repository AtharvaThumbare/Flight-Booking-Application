package com.atharva.flightauthservice.Serviceimpl;

import com.atharva.flightauthservice.DTO.LoginRequestDTO;
import com.atharva.flightauthservice.DTO.LoginResponseDTO;
import com.atharva.flightauthservice.DTO.RegisterUserDTO;
import com.atharva.flightauthservice.Entity.User;
import com.atharva.flightauthservice.Entity.Role;
import com.atharva.flightauthservice.Repository.RoleRepository;
import com.atharva.flightauthservice.Repository.UserRepository;
import com.atharva.flightauthservice.Security.JwtUtils;
import com.atharva.flightauthservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
            if(userRepository.existsByEmail(registerUserDTO.email())){
                throw new RuntimeException("Email already in use");
            }
            
            User user=new User();
            user.setFirstName(registerUserDTO.firstName());
            user.setLastName(registerUserDTO.lastName());
            user.setEmail(registerUserDTO.email());
            user.setPasswordHash(bCryptPasswordEncoder.encode(registerUserDTO.password()));

            
            Role role = roleRepository.findByRole("ROLE_CUSTOMER").orElseGet(() -> {
                Role newRole = new Role();
                newRole.setRole("ROLE_CUSTOMER");
                return roleRepository.save(newRole);
            });

            user.setRoles(Set.of(role));

            userRepository.save(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow((() -> new UsernameNotFoundException("User not found")));

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new LoginResponseDTO(jwt, "Bearer", user.getUuid(), user.getEmail(), user.getRoles().stream().map(Role::getRole).toList());
    }
}
