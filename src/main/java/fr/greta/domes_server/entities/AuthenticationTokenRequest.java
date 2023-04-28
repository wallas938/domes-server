package fr.greta.domes_server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationTokenRequest {
    private String email;
    private String refreshToken;
}
