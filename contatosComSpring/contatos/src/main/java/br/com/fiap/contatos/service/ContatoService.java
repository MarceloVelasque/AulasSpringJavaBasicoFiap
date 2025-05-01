package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    public ContatoExibicaoDto gravar(ContatoCadastroDto contatoCadastroDto) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastroDto, contato);//essa BeanUtils vai popular meu objeto contato com os dados de ContatoCadastroDto.
        return  new ContatoExibicaoDto(contatoRepository.save(contato));
    }


    public ContatoExibicaoDto buscarPorId(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            return  new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não existe");
        }

    }
    public List<ContatoExibicaoDto> listarTodosOsContatos() {
        return contatoRepository.findAll()
                .stream()//.stream() transforma a List (ou outra coleção) em um Stream, que é uma sequência de dados.
                .map(ContatoExibicaoDto::new)//transforma cada elemento da sequência. No caso, de Contato para ContatoExibicaoDto.
                .toList(); //transforma o Stream de volta em uma List.
    }


    public void excluir(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ContatoExibicaoDto> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal).stream().map(ContatoExibicaoDto::new).toList();
    }

    public ContatoExibicaoDto atualizar(Contato contato) {
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());
        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoRepository.save(contato));
        } else {
            throw new RuntimeException("Esse Contato não existe");
        }
    }

    public ContatoExibicaoDto buscarPeloNome(String nome) {
        Optional<Contato> contatoOptional = Optional.ofNullable(contatoRepository.findByNome(nome));
        if(contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoRepository.findByNome(nome));
        } else {
            throw new RuntimeException("Nome não encontrado");
        }
    }
    // consulta de nomes usando o JPQL
    public ContatoExibicaoDto buscarContatoPeloNome(String nome) {
        Optional<Contato> contatoOptional = contatoRepository.BuscarContatoPeloNome(nome);
        if(contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new RuntimeException("Nome não encontrado");
        }

    }
    // consulta de data JPQL de um periodo de data
    public List<ContatoExibicaoDto> listarAniversariantesDoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.listarAniversariantesDoPeriodo(dataInicial, dataFinal).stream().map(ContatoExibicaoDto::new).toList();
    }


}
