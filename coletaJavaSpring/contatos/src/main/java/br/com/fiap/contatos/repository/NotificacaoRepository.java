package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {}
