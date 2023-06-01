package fr.greta.domes_server.controllers;

import fr.greta.domes_server.dtos.animal.AnimalPage;
import fr.greta.domes_server.dtos.client.ClientGetDTO;
import fr.greta.domes_server.dtos.client.ClientPostDTO;
import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.repositories.EmployeeRepository;
import fr.greta.domes_server.services.ClientService;
import fr.greta.domes_server.services.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientService clientService;

    private final JwtTokenService jwtTokenService;
    private final HttpServletRequest request;


    @PostMapping("/signup")
    public ResponseEntity<DomesResponse> saveClient(@RequestBody ClientPostDTO clientPostDTO) {
        DomesResponse domesResponse = clientService.saveClient(clientPostDTO);
        return new ResponseEntity<>(domesResponse, domesResponse.getCode());
    }

    @PostMapping("/employee-authentication")
    public AuthenticationTokenResponse employeeAuthentication(@RequestBody Credentials credentials) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()
                )
        );

        String issuer = request.getRequestURI();

        var user = employeeRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthenticationTokenResponse(jwtTokenService.generateToken(user, issuer), jwtTokenService.generateRefreshToken(user, issuer), HttpStatus.ACCEPTED.value());
    }

    @PostMapping("/client-authentication")
    public ResponseEntity<AuthenticationTokenResponse> clientAuthentication(@RequestBody Credentials credentials) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(), credentials.getPassword()
                )
        );

        String issuer = request.getRequestURI();

        var user = clientRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new ResponseEntity<>(new AuthenticationTokenResponse(jwtTokenService.generateToken(user, issuer), jwtTokenService.generateRefreshToken(user, issuer), HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/employee/token/refresh")
    public ResponseEntity<AuthenticationTokenResponse> renewEmployeeAccessToken(@RequestBody AuthenticationTokenRequest authenticationTokenRequest) {

        var emailFromRefreshToken = jwtTokenService.extractUsername(authenticationTokenRequest.getRefreshToken().substring("Bearer ".length()));

        if (emailFromRefreshToken == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        var employee = employeeRepository.findByEmail(authenticationTokenRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (jwtTokenService.isTokenValid(authenticationTokenRequest.getRefreshToken().substring("Bearer ".length()), employee)) {
            String issuer = request.getRequestURI();

            return new ResponseEntity<>(new AuthenticationTokenResponse(jwtTokenService.generateToken(employee, issuer),
                    authenticationTokenRequest.getRefreshToken(), HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new AuthenticationTokenResponse(null,
                null, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/client/token/refresh")
    public ResponseEntity<AuthenticationTokenResponse> renewClientAccessToken(@RequestBody AuthenticationTokenRequest authenticationTokenRequest) {

        String emailFromRefreshToken = jwtTokenService.extractUsername(authenticationTokenRequest.getRefreshToken());

        if (emailFromRefreshToken != null) {

            var client = clientRepository.findByEmail(authenticationTokenRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (jwtTokenService.isTokenValid(authenticationTokenRequest.getRefreshToken(), client)) {
                String issuer = request.getRequestURI();
                return new ResponseEntity<>(new AuthenticationTokenResponse(jwtTokenService.generateToken(client, issuer),
                        authenticationTokenRequest.getRefreshToken(),
                        HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(new AuthenticationTokenResponse(null,
                    null, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new AuthenticationTokenResponse(null,
                null, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }
}
