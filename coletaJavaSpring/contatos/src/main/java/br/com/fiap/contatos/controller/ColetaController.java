package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.ColetaCadastroDto;
import br.com.fiap.contatos.dto.ColetaExibicaoDto;
import br.com.fiap.contatos.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    private final ColetaService service;

    public ColetaController(ColetaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ColetaExibicaoDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColetaExibicaoDto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //anotação de Status que objeto foi criado ou gravado
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid ColetaCadastroDto dto) {
        service.cadastrar(dto);
        return ResponseEntity.status(201).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
