package com.atharva.flightauthservice.Controller;


import com.atharva.flightauthservice.DTO.LoginRequestDTO;
import com.atharva.flightauthservice.DTO.RegisterUserDTO;
import com.atharva.flightauthservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;

     @PostMapping("/login")
      public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){

            return ResponseEntity.ok(authService.login(loginRequestDTO));
      }


      @PostMapping("/register")
      public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO){

             authService.registerUser(registerUserDTO);


           return ResponseEntity.created(null).body("User registered successfully");




      }

      @GetMapping
        public ResponseEntity<?> test(){
             return ResponseEntity.ok("Auth service is working");
        }

}
