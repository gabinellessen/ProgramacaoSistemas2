import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppStreaming {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem vindo(a) ao sistema de cadastramento de Series e Filmes! :) ");
        Scanner sc = new Scanner(System.in);
        List<Midia> midias = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("(1) Adiconar novo Filme");
            System.out.println("(2) Adicionar nova Série");
            System.out.println("(3) Listar todas as mídias");
            System.out.println("(4) Sair");
             
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
                System.out.println("Opção inválida! Tente novamente.");}
            } while(opcao != 4);
                sc.close();
            }

            public static void adicionarFilme(List<Midia> midias, Scanner sc) {
                 System.out.println("\n---ADICIONAR FILME ---");
        System.out.println("Digite o titulo do filme: ");
        String titulo = sc.nextLine();

        System.out.println("Digite a duração do filme: (minutos)");
        int duracao = sc.nextInt();
        sc.nextLine();

        Filme filme = new Filme(titulo, duracao);
        midias.add(filme);
        System.out.println("Filme Adicionado com sucesso!");
    }

    private static void adicionarSerie(List<Midia> midias, Scanner sc) {
        System.out.println("\n ---ADICIONAR SERIE---");
        System.out.println("Digite o nome da serie: ");
        String titulo = Scanner.nextLine();

        
    }



        Midia midia = new Filme("Titanic 2 and Knuckles", 300);
        System.out.println(midia.info());
        System.out.println(midia.getDuracao());

        Temporada temporada = new Temporada(1);
        temporada.adicionar(new Episodio("Piloto", 60));
        temporada.adicionar(new Episodio("Surpresa", 45));
        temporada.adicionar(new Episodio("Continuação", 30));
        System.out.println(temporada.info());
        System.out.println(temporada.getDuracao());
    }
}