package com.atharva.flightauthservice.DTO;

import java.util.List;
import java.util.UUID;

public record LoginResponseDTO(
        String accessToken,

        String tokenType,


        UUID uuid,
        String email,
        List<String> roles

) {

    public LoginResponseDTO(String accessToken, String tokenType, UUID uuid, String email, List<String> roles) {

        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.uuid = uuid;
        this.email = email;
        this.roles = roles;


    }
}
