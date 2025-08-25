import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppStreaming {
    public static void main(String[] args) {
        System.out.println("Bem vindo(a) ao sistema de cadastramento de Series e Filmes! :) ");
        Scanner sc = new Scanner(System.in);
        List<Midia> midias = new ArrayList<>();
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("(1) Adicionar novo Filme");
            System.out.println("(2) Adicionar nova Série");
            System.out.println("(3) Listar todas as mídias");
            System.out.println("(4) Sair");
            System.out.print("Escolha uma opção: ");
            while (!sc.hasNextInt()) {
                System.out.println("Digite um número válido!");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao) {
                case 1:
                    adicionarFilme(midias, sc);
                    break;
                case 2:
                    adicionarSerie(midias, sc);
                    break;
                case 3:
                    listarMidias(midias);
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 4);
        sc.close();
    }

    public static void adicionarFilme(List<Midia> midias, Scanner sc) {
        System.out.println("\n---ADICIONAR FILME ---");
        System.out.print("Digite o título do filme: ");
        String titulo = sc.nextLine();
        System.out.print("Digite a duração do filme (minutos): ");
        while (!sc.hasNextLong()) {
            System.out.println("Digite um número válido para a duração!");
            sc.next();
        }
        long duracao = sc.nextLong();
        sc.nextLine();
        Filme filme = new Filme(titulo, duracao);
        midias.add(filme);
        System.out.println("Filme adicionado com sucesso!");
    }

    public static void adicionarSerie(List<Midia> midias, Scanner sc) {
        System.out.println("\n---ADICIONAR SÉRIE ---");
        System.out.print("Digite o título da série: ");
        String titulo = sc.nextLine();
        Serie serie = new Serie(titulo);
        for (int t = 1; t <= 2; t++) {
            Temporada temporada = new Temporada(t);
            System.out.println("Adicionando episódios para a Temporada " + t + ":");
            for (int e = 1; e <= 2; e++) {
                System.out.print("  Título do episódio " + e + ": ");
                String tituloEp = sc.nextLine();
                System.out.print("  Duração do episódio " + e + " (minutos): ");
                while (!sc.hasNextLong()) {
                    System.out.println("Digite um número válido para a duração!");
                    sc.next();
                }
                long duracaoEp = sc.nextLong();
                sc.nextLine();
                Episodio ep = new Episodio(tituloEp, duracaoEp);
                temporada.adicionarEpisodio(ep);
            }
            serie.adicionarTemporada(temporada);
        }
        midias.add(serie);
        System.out.println("Série adicionada com sucesso!");
    }

    public static void listarMidias(List<Midia> midias) {
        System.out.println("\n---LISTA DE MÍDIAS---");
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada.");
        } else {
            for (Midia m : midias) {
                System.out.println(m.info() + "Duração total: " + m.getDuracao() + " minutos\n");
            }
        }
    }
}
