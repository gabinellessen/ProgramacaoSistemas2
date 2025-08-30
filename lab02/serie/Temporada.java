import java.util.ArrayList;
import java.util.List;

public class Temporada {
    private int numero;
    private List<Episodio> episodios;

    public Temporada(int numero) {
        this.numero = numero;
        this.episodios = new ArrayList<>();
    }

    public void adicionarEpisodio(Episodio ep) {
        episodios.add(ep);
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Temporada ").append(numero).append(":\n");
        for (Episodio ep : episodios) {
            sb.append("    ").append(ep).append("\n");
        }
        return sb.toString();
    }
}
