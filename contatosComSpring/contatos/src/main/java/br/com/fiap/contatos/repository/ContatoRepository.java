package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContatoRepository  extends JpaRepository<Contato, Long> {


    public Contato findByNome(String nome);

    public List<Contato> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);



    @Query("SELECT c FROM Contato c WHERE c.nome = :nome")
    Optional<Contato> BuscarContatoPeloNome(@Param("nome") String nome);

//método usando de consulta derived query methods ou consulta derivadas de métodos, aonde estamos consultado o e=mail
    Optional<Contato> findByEmail(String email);


    @Query ("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<Contato> listarAniversariantesDoPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

}
