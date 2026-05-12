package com.atharva.flightauthservice.DTO;

import jakarta.validation.constraints.*;

public record RegisterUserDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
        String firstName,
        @NotBlank(message = "Last Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in format: user@example.com")
        String email,

        @NotBlank(message = "Password is required")
        String password


) {
}
