package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificacaoCadastroDto(
        @NotBlank(message = "Tipo da notificação é obrigatório")
        String tipoNotificacao,

        @NotBlank(message = "Descrição da notificação é obrigatória")
        String descricao
) {}
