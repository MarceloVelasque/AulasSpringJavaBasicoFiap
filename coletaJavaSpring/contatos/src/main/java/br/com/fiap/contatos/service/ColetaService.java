package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.ColetaCadastroDto;
import br.com.fiap.contatos.dto.ColetaExibicaoDto;
import br.com.fiap.contatos.model.Coleta;
import br.com.fiap.contatos.repository.ColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    private final ColetaRepository repository;

    public ColetaService(ColetaRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(ColetaCadastroDto dto) {
        Coleta coleta = new Coleta();
        coleta.setTipoMaterial(dto.tipoMaterial());
        coleta.setTratamento(dto.tratamento());
        coleta.setDataColeta(dto.dataColeta());
        repository.save(coleta);
    }

    public List<ColetaExibicaoDto> listar() {
        return repository.findAll().stream().map(ColetaExibicaoDto::new).toList();
    }

    public Optional<ColetaExibicaoDto> buscarPorId(Long id) {
        return repository.findById(id).map(ColetaExibicaoDto::new);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
