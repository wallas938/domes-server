package fr.greta.domes_server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Integer statusCode;
}
