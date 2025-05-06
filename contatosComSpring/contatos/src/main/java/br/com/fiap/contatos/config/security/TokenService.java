package br.com.fiap.contatos.config.security;

import br.com.fiap.contatos.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.PublicKey;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String palavraSecreta;

    // o método gerarToken vai ser usado no momento em que o usuário mandar os dados dele pra gente, ou seja ele não tem token e vai ser gerado no
    //momento do login
    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta); //algoritimo que vamos usar vai ser o HMAC256 para fazer a criptografia da nossa palavra secreta
            // essa variavel vai guardar o token
            String token = JWT.create() //aqui usamos o create para criar o token
                    .withIssuer("contatos") //withIssuer seria o emissor do token aqui seria o idetificado de quem emitiu o token, colocamos contato
                    .withSubject(usuario.getEmail()) // o withSubject identifica o nome do usuario o user name
                    .withExpiresAt(gerarDataDeExpiracao()) //aqui vamos definir o tempo de expiração desse token para isso criamos um método gerarDataDeExpiracao
                    .sign(algorithm); // aqui vamos assinar o token que seri aplicar a criptografia
            return token; // se o token for gerado com sucesso ele vai retornar
        } catch (JWTCreationException exception) { //criando um tratamento de exceção , se for gerado o token sinal que o usuário está valido se não conseguir gerar o token volta vazio
            throw  new RuntimeException("Não foi possível gerar o token");
        }
    }

    //quando gerar o token no método GerarToken aqui vai validado com esse método
    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta); // se eu usei o Algorithm.HMAC256 para criar o token eu tenho que usar ele também para decifrar o token
            return JWT.require(algorithm) //aqui queremos que ele decifre o token usando esse algoritimo
                    .withIssuer("contatos")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }

    }


        //método para criar um tempo de expiração para usar no token
    // vai pegar a hora atual de agora somar mais +2 horas a minha zona de tempo fuzuhorario
        public Instant gerarDataDeExpiracao(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }


    }
