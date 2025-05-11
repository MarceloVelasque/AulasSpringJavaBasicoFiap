package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.Coleta;

import java.time.LocalDate;

public record ColetaExibicaoDto(
        Long id,
        String tipoMaterial,
        String tratamento,
        LocalDate dataColeta
) {
    public ColetaExibicaoDto(Coleta coleta) {
        this(
                coleta.getId(),
                coleta.getTipoMaterial(),
                coleta.getTratamento(),
                coleta.getDataColeta()
        );
    }
}
