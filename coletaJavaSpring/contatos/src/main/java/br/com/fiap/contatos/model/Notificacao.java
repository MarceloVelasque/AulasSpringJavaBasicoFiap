package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICACAO_SEQ")
    @SequenceGenerator(name = "NOTIFICACAO_SEQ", sequenceName = "NOTIFICACAO_SEQ", allocationSize = 1)
    private Long id;


    private String tipoNotificacao;
    private String descricao;

    @Column(name = "data_notificacao")
    private LocalDate dataNotificacao;

    public Notificacao() {}

    public Notificacao(String tipoNotificacao, String descricao, LocalDate dataNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
        this.descricao = descricao;
        this.dataNotificacao = dataNotificacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }
}
