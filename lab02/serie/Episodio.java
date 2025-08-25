public class Episodio {
    private String titulo;
    private long duracao;

    public Episodio(String titulo, long duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public long getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Episódio: " + titulo + " (" + duracao + " min)";
    }
}
