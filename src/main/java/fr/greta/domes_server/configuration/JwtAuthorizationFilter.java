package fr.greta.domes_server.configuration;

import fr.greta.domes_server.entities.DomesUser;
import fr.greta.domes_server.repositories.ClientRepository;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.repositories.EmployeeRepository;
import fr.greta.domes_server.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final DomesUserRepository domesUserRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        JwtTokenService jwtTokenService = new JwtTokenService();
        String tokenPrefix = "Bearer ";
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(tokenPrefix)) {
            try {
                String token = authorizationHeader.substring(tokenPrefix.length());
                String email = jwtTokenService.extractUsername(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    DomesUser domesUser = domesUserRepository.findByEmail(email).get();

                    if (jwtTokenService.isTokenValid(token, domesUser)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                domesUser,
                                null,
                                domesUser.getAuthorities());
                        authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
            }
        }
    }
}
