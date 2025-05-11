package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.NotificacaoCadastroDto;
import br.com.fiap.contatos.dto.NotificacaoExibicaoDto;
import br.com.fiap.contatos.model.Notificacao;
import br.com.fiap.contatos.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public void enviar(NotificacaoCadastroDto dto) {
        Notificacao n = new Notificacao();
        n.setTipoNotificacao(dto.tipoNotificacao());
        n.setDescricao(dto.descricao());
        n.setDataNotificacao(LocalDate.now());
        repository.save(n);
    }

    public List<NotificacaoExibicaoDto> listar() {
        return repository.findAll().stream().map(NotificacaoExibicaoDto::new).toList();
    }
}
