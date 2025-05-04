package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //nosso repository para buscar o username que no caso é o nosso email, sendo ele do tipo UserDetails, por padrão seguindo as o que é
    //pedido na JPA usamos sempre o findBy no começo do nome do método
    UserDetails findByEmail(String email);
}
