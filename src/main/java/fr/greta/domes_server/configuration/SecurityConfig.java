package fr.greta.domes_server.configuration;


import fr.greta.domes_server.entities.DomesUser;
import fr.greta.domes_server.repositories.DomesUserRepository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserAuthenticationProvider authProvider;

    private final DomesUserRepository domesUserRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeHttpRequests(
                        ahr -> ahr.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/animals/**").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .authenticationProvider(authProvider)
                                .addFilterBefore(new JwtAuthorizationFilter(domesUserRepository), UsernamePasswordAuthenticationFilter.class));


        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

        auth.userDetailsService(email -> {
            DomesUser domesUser = domesUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Found"));

            return new User(domesUser.getEmail(), domesUser.getPassword(), null);

        });
        return auth.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            DomesUser domesUser = domesUserRepository.findByEmail(email).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));
            return new User(domesUser.getEmail(), domesUser.getPassword(), null);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
