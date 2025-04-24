package br.com.fiap.contatos;

import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContatoApp {
    public static void main(String[] args) {



        //Criação EntityManager de forma correta com classe para conexão e uma classe DAO para a classe Contato
        EntityManager em = Conexao.getEntityManager();
   // cadastrar(em);
    //atualizar(em);
    //excluir(em);
    //consultarContatoPorId(em);
      //  listarTodosOsContatos(em);
        listarContatosPeloEmail(em);

    }
    public static void cadastrar(EntityManager em){
        Contato contato = new Contato();
        contato.setNome("João da Silva");
        contato.setEmail("joao@gmail.com");
        contato.setDataNascimento(LocalDate.of(1992,01,23));
        //criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.salvar(contato);
        em.getTransaction().commit();

    }

    public static void atualizar(EntityManager em){
        Contato contato = new Contato();
        contato.setId(2L);
        contato.setNome("Raquel");
        contato.setEmail("Raquel@gmail.com");
        contato.setDataNascimento(LocalDate.of(1982,01,16));
        //criar uma instância do DAO
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.atualizar(contato);
        em.getTransaction().commit();

    }

//para e exclusão só preciso do ID por isso mais enchuto..
    public static void excluir(EntityManager em){
        Contato contato = new Contato();
        contato.setId(2L);
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.excluir(contato);
        em.getTransaction().commit();

    }


    public static void consultarContatoPorId(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();
        contatoDao.consultaContatoPorId(4L);
        em.getTransaction().commit();

    }


    public static void listarTodosOsContatos(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);
        List<Contato> contatos = contatoDao.listarTodosOsContatos();
        for (Contato c : contatos) {
            System.out.println(c.toString());
        }

    }


    public static void listarContatosPeloEmail(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);
        List<Contato> contatos = contatoDao.listarContatosPorEmail("marcelo@gmail.com");
        for (Contato c : contatos) {
            System.out.println(c.toString());
        }

    }





      /*  //Criação EntityManager aqui estou criando uma fabrica de conexão aonde ele vai acessar os dados do arquivo persistence.xml
        // de aonde tem todos os dados com informações para a conexão com o banco de dados..
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contatos");
        EntityManager em = emf.createEntityManager(); //aqui criei um Objeto do EntityManager que vai receer essa conexão da fabrica de conexão
        em.getTransaction().begin();//vou dizer aqui que estou começando uma transação com o banco de dados ou seja vai iniciar uma transação com o banco de dados
        em.persist(contato); // aqui ele vai persistir no banco criando a tabela da nossa entidade contato com todos os campos ela está estanciada aqui Contato contato = new Contato();.
        em.getTransaction().commit();// o commit é aonde vai finalizar essa transação com o banco  e que o novo registro foi criado lá no banco
        em.close(); //aqui vamos terminar a execução liberando assim a conexão não sendo mais necessario*/
}
