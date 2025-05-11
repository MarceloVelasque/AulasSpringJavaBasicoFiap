package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.Notificacao;
import java.time.LocalDate;

public record NotificacaoExibicaoDto(
        Long id,
        String tipoNotificacao,
        String descricao,
        LocalDate dataNotificacao
) {
    public NotificacaoExibicaoDto(Notificacao n) {
        this(n.getId(), n.getTipoNotificacao(), n.getDescricao(), n.getDataNotificacao());
    }
}
