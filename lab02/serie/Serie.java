import java.util.ArrayList;
import java.util.List;

public class Serie extends Midia {
    private List<Temporada> temporadas;

    public Serie(String titulo) {
        super(titulo);
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada temp) {
        temporadas.add(temp);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    @Override
    public long getDuracao() {
        long total = 0;
        for (Temporada t : temporadas) {
            for (Episodio ep : t.getEpisodios()) {
                total += ep.getDuracao();
            }
        }
        return total;
    }

    @Override
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Série: ").append(getTitulo()).append("\n");
        for (Temporada t : temporadas) {
            sb.append(t);
        }
        return sb.toString();
    }
}