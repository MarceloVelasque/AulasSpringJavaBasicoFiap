package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.NotificacaoCadastroDto;
import br.com.fiap.contatos.dto.NotificacaoExibicaoDto;
import br.com.fiap.contatos.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Void> enviar(@RequestBody @Valid NotificacaoCadastroDto dto) {
        service.enviar(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public List<NotificacaoExibicaoDto> listar() {
        return service.listar();
    }
}
