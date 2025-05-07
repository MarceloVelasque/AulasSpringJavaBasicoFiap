package br.com.fiap.contatos.config.security;

import br.com.fiap.contatos.repository.UsuarioRepository;
import br.com.fiap.contatos.service.AuthorizationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerificarToken  extends OncePerRequestFilter {

    @Autowired
    private TokenService  tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    private final AuthorizationService authorizationService;
   // private final TokenService tokenService;

    public VerificarToken(AuthorizationService authorizationService, TokenService tokenService) {
        this.authorizationService = authorizationService;
        this.tokenService = tokenService;
    } //com essa extensão vamos garantir que  essa requisição só vai acontecer uma vez por filtragem
    // para isso vamos herdar o método  doFilterInternal de OncePerRequestFilter, que nos força a implementar os seguintes parâmetros
    @Override
    protected void doFilterInternal(HttpServletRequest request,//vamos ter "receber" os dados da requisição que o usuário mandou
                                    HttpServletResponse response,//objeto que vai devolver a  requisição de resposta para o usuário
                                    FilterChain filterChain) // sabemos que ele vai ser filtrado
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization"); //aqui vira o nome bearer seguido do token, precisamos extrar só o token então
        String token = ""; // essa variavel token vai guardar o token extraido da requisição
        if(authorizationHeader == null){ // vamos verificar se o authorizationHeader é igual a null
            token = null; // se for retorna null

        } else { // se não for token recebe o  authorizationHeader, replace ou seja eu quero fazer uma troca do nome bearer por uma string vazia
            // isso para extrairmos só o token mesmo, e o .trim() vai matar os espaços que tiverem deixando apenas o token
            token = authorizationHeader.replace("Bearer ", ""). trim();
            String login = tokenService.validarToken(token);// aqui estamos chamando o método do nosso service que valida os tokens
            UserDetails usuario = usuarioRepository.findByEmail(login);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities()
            );
            // pegamos o contexto na nossa autenticação e setamos o objeto authenticationToken que acabamos de gerar
            //sabendo e reconhecendo o tipo de usuário que está solicitando algo
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        //devolvendo a resquisição e a resposta
        filterChain.doFilter(request, response);


    }
}
