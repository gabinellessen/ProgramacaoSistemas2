package com.ps2.titular_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static ps2.titular_app.ES.*;

@SpringBootApplication
public class TitularAppApplication implements CommandLineRunner {

	@Autowired
	private TitularRepo titularrepo;

	public static void main(String[] args) {
		SpringApplication.run(TitularAppApplication.class, args);
	}

	public void criar() {
		Titular t;
		t = new Titular();
		String nome = input("Nome do novo titular: ");
		t.setNome(nome);
		String cpf = input("CPF do novo titular: ");
		t.setCpf(cpf);
		titularrepo.save(t);
		System.out.println("Titular criado com o id " + t.getId());
	}

	public void lerTudo() {
		Iterable<Titular> titulares = titularrepo.findAll();
		for (Titular t : titulares) {
			System.out.println(t);
		}
	}

	public void listar() {
		long id = Long.parseLong(input("Número do titular a ser buscado: "));
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("# GERENCIADOR DE TITULARES!");
		boolean sair = false;
		String menu = "\n(1) Listar todos os titulares";
		menu += "\n(2) Buscar um titular específico pelo número";
		menu += "\n(3) Criar novo titular";
		menu += "\n(0) Sair \n";
		menu += "Escolha uma opção: ";

		while (!sair) {
			String op = input(menu);
			switch (op) {
				case "1":
					lerTudo();
					break;
				case "2":
					buscar();
					break;
				case "3":
					criar();
					break;
				case "0":
					sair = true;
					break;
				default:
					print("Opção inválida!");
			}
		}
	}

}