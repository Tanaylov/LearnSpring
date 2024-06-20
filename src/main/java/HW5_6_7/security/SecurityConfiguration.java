package HW5_6_7.security;

import HW5_6_7.model.Reader;
import HW5_6_7.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CurrentUser currentUser;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/page/reader/**").hasAuthority("admin")
                        .requestMatchers("/page/book/**").hasAuthority("reader")
                        .requestMatchers("/page/reader/{userId}/**").access(currentUser)
                        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }


}
