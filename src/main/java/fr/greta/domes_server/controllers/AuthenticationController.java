package fr.greta.domes_server.controllers;

import fr.greta.domes_server.entities.DomesUser;
import fr.greta.domes_server.entities.Credentials;
import fr.greta.domes_server.entities.DomesResponse;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final DomesUserRepository domesUserRepository;

    private final JwtTokenService jwtTokenService;

    @PostMapping("/signup")
    public ResponseEntity<DomesResponse> signup(@RequestBody DomesUser domesUser) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<DomesResponse> signup(@RequestBody Credentials credentials) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()
                )
        );

        var user = domesUserRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtTokenService.generateToken(user);

        System.out.println(jwtToken);
        return null;
    }
}
