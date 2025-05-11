package br.com.fiap.contatos.config.security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

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
                        requestMatchers(HttpMethod.POST,"/auth/register").permitAll()// aqui rota vai permitir se registrar sem está autenticado permitindo que o usuário possa se cadastrar no sistema
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()//aqui uma chamada para essa URL pode ser permitido para todos os papeis
                        .requestMatchers(HttpMethod.GET,"/api/contatos")//método GET pra essa URL "api" eu permito todo mundo
                        .hasAnyRole("ADMIN", "USER")//aqui estamos dizendo que para essa requisição o usuário vai ter que ter um papel Admin ou user passando uma lista com o Role
                        .requestMatchers(HttpMethod.POST, "/api/contatos")// método POST para essa URL "api" eu quero só que usuario ADMIN
                        .hasRole("ADMIN")//AQUI SÓ VAMOS PERMITIR SE O USUÁRIO FOR ADMIN PARA QUALQUER OUTRA REQUISIÇÃO.
                        .requestMatchers(HttpMethod.PUT, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contatos").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())// em em qualquer outra requisção eu só vou permitir se o usuário estiver autenticado
                        .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class) // oaddFilterBefore vai por um filtro para filtrar antes de todos os filtros aqui nesse método
                        //esse filtro foi criado em uma classe nova dedicada a isso VerifcarToken.java
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

