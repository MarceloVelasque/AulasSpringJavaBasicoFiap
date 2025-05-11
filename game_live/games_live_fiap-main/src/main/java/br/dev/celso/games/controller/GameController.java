package br.dev.celso.games.controller;

import br.dev.celso.games.model.Game;
import br.dev.celso.games.repository.GameRepository;
import br.dev.celso.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;


    @GetMapping
    public List<Game> getGames() {
        List<Game> games = gameService.findAll();
        return gameService.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> save(@RequestBody Game game) {

        return ResponseEntity.created(null).body(gameService.save(game));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            gameRepository.delete(game);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Game> update(@RequestBody Game game) {
        Game actualGame = gameRepository.findById(game.getId()).orElse(null);
        if (actualGame != null) {
            return ResponseEntity.ok(gameRepository.save(game));
        }
        return ResponseEntity.notFound().build();
    }

}