package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Contato gravar(@RequestBody Contato contato) {
        return contatoService.gravar(contato);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public List<Contato> listarTodosOsContatos() {
        return contatoService.listarTodosOsContatos();

    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //anotação de status que foi deletado não tendo mais conteúdo para olhar
    public void excluir(@PathVariable Long id) {
        contatoService.excluir(id);
    }


    @PutMapping("/contatos")
    public Contato atualizar(Contato contato) {
        return contatoService.atualizar(contato);
    }

    @GetMapping("/contatos/{nome}")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public Contato buscarContatoPorNome(@PathVariable String nome) {
        return contatoService.buscarPeloNome(nome);
    }

    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK) //anotação de Status 200 que ocorreu tudo ok
    public List<Contato> mostrarAniversariante(@PathVariable LocalDate dataInicial,@PathVariable LocalDate dataFinal) {
        return contatoService.mostrarAniversariantes(dataInicial, dataFinal);
    }

}
