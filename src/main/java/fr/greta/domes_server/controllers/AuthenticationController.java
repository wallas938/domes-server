package fr.greta.domes_server.controllers;

import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.repositories.EmployeeRepository;
import fr.greta.domes_server.services.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final DomesUserRepository domesUserRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    private final JwtTokenService jwtTokenService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @PostMapping("/signup")
    public ResponseEntity<DomesResponse> signup(@RequestBody DomesUser domesUser) {
        return null;
    }

    @PostMapping("/employee-authentication")
    public AuthenticationToken employeeAuthentication(@RequestBody Credentials credentials) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()
                )
        );

        String issuer = request.getRequestURI();

        var user = employeeRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthenticationToken(jwtTokenService.generateToken(user, issuer), jwtTokenService.generateRefreshToken(user, issuer));
    }

    @PostMapping("/client-authentication")
    public AuthenticationToken clientAuthentication(@RequestBody Credentials credentials) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()
                )
        );

        String issuer = request.getRequestURI();

        var user = clientRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthenticationToken(jwtTokenService.generateToken(user, issuer), jwtTokenService.generateRefreshToken(user, issuer));
    }
}
