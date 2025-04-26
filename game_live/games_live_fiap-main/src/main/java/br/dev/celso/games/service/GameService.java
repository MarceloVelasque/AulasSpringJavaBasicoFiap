package br.dev.celso.games.service;

import br.dev.celso.games.model.Game;
import br.dev.celso.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game) {
        return gameRepository.save(game) ;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

}
