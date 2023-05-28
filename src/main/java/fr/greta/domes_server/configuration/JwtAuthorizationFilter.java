package fr.greta.domes_server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes_server.entities.DomesUser;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.*;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final DomesUserRepository domesUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/auth") ||
                request.getServletPath().contains("/token/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }
        DomesUser domesUser = null;
        JwtTokenService jwtTokenService = new JwtTokenService();
        String tokenPrefix = "Bearer ";
        String authorizationHeader = request.getHeader(AUTHORIZATION); // Bearer access_token
        String emailFromAccessToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(tokenPrefix)) {
            String access_token = authorizationHeader.substring(tokenPrefix.length()); // ey.....
            emailFromAccessToken = jwtTokenService.extractUsername(access_token);
            if (emailFromAccessToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (domesUserRepository.findByEmail(emailFromAccessToken).isPresent())
                    domesUser = domesUserRepository.findByEmail(emailFromAccessToken).get();

                assert domesUser != null;


                if (jwtTokenService.isTokenValid(access_token, domesUser)) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            domesUser,
                            null,
                            domesUser.getAuthorities());
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);
            } else {
                response.setHeader("error", "Token Expired or Not Valid");
                response.setStatus(SC_UNAUTHORIZED);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", "Token Expired or Not Valid");
                error.put("status", String.valueOf(SC_UNAUTHORIZED));
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
