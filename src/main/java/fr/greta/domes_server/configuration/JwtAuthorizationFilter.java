package fr.greta.domes_server.configuration;

import fr.greta.domes_server.entities.DomesUser;
import fr.greta.domes_server.repositories.DomesUserRepository;
import fr.greta.domes_server.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.*;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final DomesUserRepository domesUserRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        JwtTokenService jwtTokenService = new JwtTokenService();
        String tokenPrefix = "Bearer ";
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader!=null && authorizationHeader.startsWith(tokenPrefix)) {
            try {
                String token = authorizationHeader.substring(tokenPrefix.length());
                String email = jwtTokenService.extractUsername(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    DomesUser domesUser =  domesUserRepository.findByEmail(email).get();
                    if (jwtTokenService.isTokenValid(token, domesUser)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                new UserDetails() {
                                    @Override
                                    public Collection<? extends GrantedAuthority> getAuthorities() {
                                        return null;
                                    }

                                    @Override
                                    public String getPassword() {
                                        return null;
                                    }

                                    @Override
                                    public String getUsername() {
                                        return domesUser.getEmail();
                                    }

                                    @Override
                                    public boolean isAccountNonExpired() {
                                        return true;
                                    }

                                    @Override
                                    public boolean isAccountNonLocked() {
                                        return true;
                                    }

                                    @Override
                                    public boolean isCredentialsNonExpired() {
                                        return true;
                                    }

                                    @Override
                                    public boolean isEnabled() {
                                        return true;
                                    }
                                },
                                null,
                                null
                        );
                        authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }   filterChain.doFilter(request, response);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
            }
        }
    }
}
