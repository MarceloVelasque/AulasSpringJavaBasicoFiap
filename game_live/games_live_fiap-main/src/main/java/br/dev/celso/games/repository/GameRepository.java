package br.dev.celso.games.repository;

import br.dev.celso.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
