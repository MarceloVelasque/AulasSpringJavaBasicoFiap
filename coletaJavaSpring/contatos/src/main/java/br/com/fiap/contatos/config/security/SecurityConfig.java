package br.com.fiap.contatos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // AUTENTICAÇÃO
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // COLETAS
                        .requestMatchers(HttpMethod.GET, "/coletas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/coletas").hasRole("ADMIN")

                        // NOTIFICAÇÕES
                        .requestMatchers(HttpMethod.GET, "/notificacoes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/notificacoes").hasRole("ADMIN")

                        // CONTATOS (rotas anteriores)
                        .requestMatchers(HttpMethod.GET, "/api/contatos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contatos").hasRole("ADMIN")

                        // OUTRAS REQUISIÇÕES
                        .anyRequest().authenticated()
                )
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
