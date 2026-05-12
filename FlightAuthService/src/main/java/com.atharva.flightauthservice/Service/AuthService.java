package com.atharva.flightauthservice.Service;

import com.atharva.flightauthservice.DTO.LoginRequestDTO;
import com.atharva.flightauthservice.DTO.LoginResponseDTO;
import com.atharva.flightauthservice.DTO.RegisterUserDTO;

public interface AuthService {

          void registerUser(RegisterUserDTO registerUserDTO);
          LoginResponseDTO login(LoginRequestDTO loginRequestDTO);


}
