package br.dev.celso.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamesApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando a aplicação...");
		SpringApplication.run(GamesApplication.class, args);
	}

}
