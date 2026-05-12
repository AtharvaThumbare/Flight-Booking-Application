package com.atharva.flightauthservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO(@NotBlank(message = "Email is required")
                       @Email(message = "Please provide a valid email")
                       @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in format: user@example.com")
                       String email,

                              @NotBlank(message = "Password is required")
                       String password



                       ) {
}
