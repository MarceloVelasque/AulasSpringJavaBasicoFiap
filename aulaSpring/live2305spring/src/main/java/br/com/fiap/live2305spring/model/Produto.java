package br.com.fiap.live2305spring.model;

import jakarta.persistence.*;

import java.util.Objects;

    @Entity
    @Table(name = "tb_produto")
    public class Produto {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
        @SequenceGenerator(name = "SEQ_PRODUTO",sequenceName = "SEQ_PRODUTO", allocationSize = 1)
        @Column(name = "produto_id")
        private Long id;

        @Column(length = 100)
        private String nome;

        private String descricao;

        private double valor;

        private int estoque;


        public Produto() {

        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public int getEstoque() {
            return estoque;
        }

        public void setEstoque(int estoque) {
            this.estoque = estoque;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Produto produto = (Produto) o;
            return Double.compare(valor, produto.valor) == 0 && estoque == produto.estoque && Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, nome, descricao, valor, estoque);
        }

        @Override
        public String toString() {
            return "Produto{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", descricao='" + descricao + '\'' +
                    ", valor=" + valor +
                    ", estoque=" + estoque +
                    '}';
        }
    }


