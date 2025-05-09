package br.com.fiap.contatos.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {
      Map<String, String> mapaDeerro = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for(FieldError campo : campos) {
            mapaDeerro.put(campo.getField(),campo.getDefaultMessage());
        }
        return mapaDeerro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados() {
        Map<String, String> mapaDeerro = new HashMap<>();
        mapaDeerro.put("Erro","Usuário já cadastrado!");
        return mapaDeerro;
    }
}
