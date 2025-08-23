import java.util.List;
import java.util.Scanner;

public class Filme extends Midia {
    private long duracao;

    public Filme(String titulo, long duracao) {      
        super(titulo);
        this.duracao = duracao;
    }
    public adicionarFilme(List<Midia> midias, Scanner sc) {
        System.out.println("\n---ADICIONAR FILME ---");
        System.out.println("Digite o titulo do filme: ");
        String titulo = sc.nextLine();

        System.out.println("Digite a duração do filme: (minutos)");
        int duracao = sc.nextInt();
        sc.nextLine();

        Filme filme = new Filme(titulo, duracao);
        midias.add(filme);
        System.out.println("Filme Adicionado com sucesso!");
        return midias;
    }


    @Override
    public long getDuracao() {
        return duracao;
    }

    @Override
    public String info() {
        return "Filme: " + super.info() + "; Duração: " + duracao;
    }
}