package br.com.fiap.live2305spring.repository;

import br.com.fiap.live2305spring.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
