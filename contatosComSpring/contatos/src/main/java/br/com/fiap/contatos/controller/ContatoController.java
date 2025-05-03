package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED) //anotação de Status que objeto foi criado ou gravado
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) {
        return contatoService.gravar(contatoCadastroDto);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return contatoService.listarTodosOsContatos(paginacao);

    }
    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoPorId(@PathVariable Long  id) {
        return contatoService.buscarPorId(id);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //anotação de status que foi deletado não tendo mais conteúdo para olhar
    public void excluir(@PathVariable Long id) {
        contatoService.excluir(id);
    }


    @PutMapping("/contatos")
    public ContatoExibicaoDto atualizar(@RequestBody Contato contato) {
        return contatoService.atualizar(contato);
    }

    @GetMapping("/contatos/{nome}")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public ContatoExibicaoDto buscarContatoPorNome(@PathVariable String nome) {
        return contatoService.buscarPeloNome(nome);
    }

    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public List<ContatoExibicaoDto> mostrarAniversariante(@PathVariable LocalDate dataInicial,@PathVariable LocalDate dataFinal) {
        return contatoService.mostrarAniversariantes(dataInicial, dataFinal);
    }

    //consulta jpql pelo nome
    @GetMapping("/contatos/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoPeloNome(@PathVariable String nome) {
        return contatoService.buscarPeloNome(nome);
    }

//essa é outra forma de fazer no controle a consulta pelo nome usando jpql
    @GetMapping(value = "/contatos",params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatosPorNome(@RequestParam String nome) {
        return contatoService.buscarPeloNome(nome);
    }

    // consulta de data JPQL de um periodo de data
    @GetMapping(value = "/contatos", params = {"dataInicio", "dataFinal"})
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoExibicaoDto> listarAniversariantes(@RequestParam LocalDate dataInicial,@RequestParam LocalDate dataFinal) {
        return contatoService.listarAniversariantesDoPeriodo(dataInicial, dataFinal);
    }

    //consulta pelo e-mail usando métodos de consulta no repository, criando a camada no service e por fim chamando no controller.
    @GetMapping(value = "/contato", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoPorEmail(@RequestParam String email) {
        return contatoService.buscarContatoPeloEmail(email);
    }

}
