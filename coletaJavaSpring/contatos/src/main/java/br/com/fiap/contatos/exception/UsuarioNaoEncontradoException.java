package br.com.fiap.contatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//vamos devolver um 404 aqui estamos falando que o status da resposata é not found mostrando que o erro é no cliente e não na nossa aplicação
public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }



}
