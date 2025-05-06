package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.LoginDto;
import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibicaoDto;
import br.com.fiap.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController             // define esta classe como um controller REST, ou seja, capaz de responder a requisições HTTP com JSON
@RequestMapping("/auth")  // define o caminho base para as requisições que esta classe vai tratar (neste caso, "/auth")
public class AuthController {

    @Autowired // injeta automaticamente o AuthenticationManager do Spring para autenticar usuários
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService service;

    @PostMapping("/login") // mapeia requisições POST para o endpoint "/auth/login"
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
        // cria um objeto de autenticação com o e-mail e senha recebidos no corpo da requisição
        UsernamePasswordAuthenticationToken usernamePassword
                = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        // autentica o usuário usando o AuthenticationManager; se as credenciais forem válidas, retorna um objeto Authentication
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        // retorna uma resposta HTTP 200 (OK) sem corpo
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        UsuarioExibicaoDto usuarioSalvo = null;
       usuarioSalvo = service.salvarUsuario(usuarioCadastroDto);
       return usuarioSalvo;
    }


}
