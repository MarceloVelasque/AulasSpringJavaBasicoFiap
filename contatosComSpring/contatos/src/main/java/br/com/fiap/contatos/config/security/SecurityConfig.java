package br.com.fiap.contatos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // este método configura a cadeia de filtros de segurança da aplicação
    @Bean //anotação pra dizer que está sendo gerenciado pelo spring
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // desativa a proteção contra CSRF (Cross-Site Request Forgery),
                // comum em aplicações REST ou com autenticação via token (ex: JWT)
                .csrf(csrf -> csrf.disable())

                // define a política de criação de sessões como STATELESS (sem sessão).
                // isso significa que o servidor não manterá estado da autenticação entre requisições,
                // ideal para APIs seguras com tokens que é o nosso caso.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //aqui estamos falando Requisições GET permitem
                .authorizeHttpRequests(authorize -> authorize.
                        requestMatchers(HttpMethod.POST,"/auth/register").permitAll()// aqui rota vai permitir se registrar sem está autenticado
                        // permitindo que o usuário possa se cadastrar no sistema

                        .requestMatchers(HttpMethod.GET,"/api/contatos").permitAll()//método GET pra essa URL "api" eu permito todo mundo
                        .requestMatchers(HttpMethod.POST, "/api/contatos")// método POST para essa URL "api" eu quero só que usuario ADMIN
                        .hasRole("ADMIN")//AQUI SÓ VAMOS PERMITIR SE O USUÁRIO FOR ADMIN PARA QUALQUER OUTRA REQUISIÇÃO.
                        .anyRequest()
                        .authenticated())// em em qualquer outra requisção eu só vou permitir se o usuário estiver autenticado
                        .build();// constrói e retorna a cadeia de filtros de segurança configurada
    }


    //método de gerenciador de  autenticação, vai pegar os dados do usuário e fazer a autenticação dele
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { //preciso criar um método do tipo PassWordEncoder retornando um BcryptPassWordEncoder
        return new BCryptPasswordEncoder(); //para criptografar nossa senha
    }


}

