package com.ps2.titular_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

import static com.ps2.titular_app.ES.*;

@SpringBootApplication
public class TitularAppApplication implements CommandLineRunner {

    @Autowired
    private TitularRepo titularRepo;

    public static void main(String[] args) {
        SpringApplication.run(TitularAppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        boolean sair = false;
        while (!sair) {
            String menu = "\n(1) Listar todos os titulares"
                        + "\n(2) Buscar um titular específico pelo número"
                        + "\n(3) Criar um novo titular"
                        + "\n(4) Alterar os dados do titular"
                        + "\n(5) Apagar um titular"
                        + "\n(0) Sair"
                        + "\nEscolha uma opção: ";
            String op = input(menu);
            switch (op) {
                case "1":
                    listarTodos();
                    break;
                case "2":
                    buscarPorId();
                    break;
                case "3":
                    criarNovo();
                    break;
                case "4":
                    alterar();
                    break;
                case "5":
                    apagar();
                    break;
                case "0":
                    sair = true;
                    print("Saindo...");
                    break;
                default:
                    print("Opção inválida!");
            }
        }
    }

    private void listarTodos() {
        Iterable<Titular> titulares = titularRepo.findAll();
        for (Titular t : titulares) {
            print(t.toString());
        }
    }

    private void buscarPorId() {
        try {
            Long id = Long.parseLong(input("Número do titular a ser buscado: "));
            Optional<Titular> titular = titularRepo.findById(id);
            if (titular.isPresent()) {
                print(titular.get().toString());
            } else {
                print("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            print("Número inválido.");
        }
    }

    private void criarNovo() {
        String nome = input("Nome do novo titular: ");
        String cpf = input("CPF do novo titular: ");
        Titular t = new Titular(nome, cpf);
        titularRepo.save(t);
        print("Titular criado com o id " + t.getId());
    }

    private void alterar() {
        try {
            Long id = Long.parseLong(input("Número do titular a ser alterado: "));
            Optional<Titular> titularOpt = titularRepo.findById(id);
            if (titularOpt.isPresent()) {
                Titular t = titularOpt.get();
                String nome = input("Novo nome (atual: " + t.getNome() + "): ");
                String cpf = input("Novo CPF (atual: " + t.getCpf() + "): ");
                t.setNome(nome.isEmpty() ? t.getNome() : nome);
                t.setCpf(cpf.isEmpty() ? t.getCpf() : cpf);
                titularRepo.save(t);
                print("Titular alterado.");
            } else {
                print("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            print("Número inválido.");
        }
    }

    private void apagar() {
        try {
            Long id = Long.parseLong(input("Número do titular a ser apagado: "));
            if (titularRepo.existsById(id)) {
                titularRepo.deleteById(id);
                print("Titular apagado.");
            } else {
                print("Titular não encontrado.");
            }
        } catch (NumberFormatException e) {
            print("Número inválido.");
        }
    }
}