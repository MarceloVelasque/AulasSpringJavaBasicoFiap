package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository  usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioCadastroDto) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha()); //usando o BCryptPasswordEncoder para criptografar nossa senha
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario); //essa BeanUtils vai popular meu objeto usuario com os dados de UsuarioCadastroDto.
        usuario.setSenha(senhaCriptografada); //aqui estamos setando nossa senha para a senha criptografada
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return  new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDto buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            return new UsuarioExibicaoDto(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");
        }
    }



    }


