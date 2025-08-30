



public abstract class Midia {
    private String titulo;

    public Midia(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public abstract long getDuracao();

    public String info() {
        return titulo;
    }
}