package br.com.fiap.contatos.dto;

import java.time.LocalDate;

public record ColetaCadastroDto(
        String tipoMaterial,
        String tratamento,
        LocalDate dataColeta
) {}
