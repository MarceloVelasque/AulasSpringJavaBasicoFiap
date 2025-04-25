package br.com.fiap.live2305spring.controller;

import br.com.fiap.live2305spring.model.Produto;
import br.com.fiap.live2305spring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    @GetMapping("/oi")
    public String oi(){
        return "oi pessoal da fiap";
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        System.out.println("Salvando o produto...");
        Produto produtoNovo = produtoRepository.save(produto);
        return ResponseEntity.created(null).body(produtoNovo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
        return ResponseEntity.ok().body(produto.get());
    } return ResponseEntity.notFound().build();

        }


}
