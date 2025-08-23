import java.util.List;
import java.util.Scanner;

public class Serie extends Midia {
    private long duracao;

    public Serie(String titulo, long duracao) {      
        super(titulo);
        this.duracao = duracao;
    }
    public List<Midia> adicionarSerie(List<Midia> midias, Scanner sc) {
        System.out.println("\n---ADICIONAR SERIE ---");
        System.out.println("Digite o titulo do serie: ");
        String titulo = sc.nextLine();        
        midias.add(serie);
        return midias;
    }
    @Override
    public long getDuracao() {
        return duracao;
    }

    @Override
    public String info() {
        return "Serie: " + super.info();
    }
}