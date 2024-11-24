package aulas.semana08.exemplosaula.livroautor;

public class Livro {
    private int id;
    private String titulo;
    private String assunto;
    private String isbn;
    private String dataPublicacao;

    public Livro(int id, String titulo, String assunto, String isbn, String dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.assunto = assunto;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return id + " - " + titulo;
    }
}


