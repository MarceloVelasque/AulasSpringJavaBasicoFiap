package br.com.fiap.contatos.service;

import br.com.fiap.contatos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    //aqui é um método que vai consumir
    // nosso repository para buscar o username que no caso é o nosso email, aqui criamos um método do tipo UserDetails que
    // ira retornar um usuarioRepositoy com o nosso usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
}
