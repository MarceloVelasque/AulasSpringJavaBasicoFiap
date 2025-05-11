package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContatoDao {

    private EntityManager em;

    public ContatoDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Contato contato) {
        em.persist(contato);
    }

    public void atualizar(Contato contato) {
        em.merge(contato);
    }
//aqui o find recupera o id do contato no banco de dados, na sequência é passada para o em.remover esse atributo aonde foi recuperado o id do contato
    //o porque do Contato.class ? quando o find traz o do banco o id ele transforma em um objeto do tipo Contato, ai o remove vai receber o objeto que foi encontrado
    // que no caso é o id
    public void excluir(Contato contato) {
        Contato contatoExcluir = em.find(Contato.class,contato.getId());
        em.remove(contatoExcluir);
    }

    public void consultaContatoPorId(Long id) {
        Contato contatoConsulta = em.find(Contato.class, id);
        if(contatoConsulta != null) {
            System.out.println("Contato não encontrado");
        } else {
            System.out.println("Contato encontrado");
            System.out.println(contatoConsulta.toString());

        }

    }
//jpql.... aqui criamos um método do tipo list que retorna uma list, aonde criamos um atributo que recebe uma consulta jpql
    public List<Contato> listarTodosOsContatos() {
       String consulta = "SELECT c FROM Contato c ORDER BY c.nome ASC";
       return em.createQuery(consulta).getResultList();//retornamos um em.createQuery para passar a consulta que tem nossa jpql, em seguida usamos o
        // método do hibernate para nos trazer uma lista getResultList.
    }

    public List<Contato> listarContatosPorEmail(String email){
    String consulta = "SELECT c FROM Contato c WHERE c.email = :email";
    return em.createQuery(consulta, Contato.class).setParameter("email", email).getResultList();
    }

}
