package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_coleta")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLETA_SEQ")
    @SequenceGenerator(name = "COLETA_SEQ", sequenceName = "COLETA_SEQ", allocationSize = 1)
    private Long id;

    private String tipoMaterial;
    private String tratamento;
    private LocalDate dataColeta;

    public Coleta() {}

    public Coleta(String tipoMaterial, String tratamento, LocalDate dataColeta) {
        this.tipoMaterial = tipoMaterial;
        this.tratamento = tratamento;
        this.dataColeta = dataColeta;
    }

    public Long getId() {
        return id;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }
}
