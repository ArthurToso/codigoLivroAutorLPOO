package aulas.semana08.exemplosaula.livroautor;
public class Autor {
    private int id;
    private String nome;
    private String dataNascimento;
    private String documento;
    private String naturalidade;

    public Autor(int id, String nome, String dataNascimento, String documento, String naturalidade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.documento = documento;
        this.naturalidade = naturalidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public String getDocumento() { return documento; }
    public String getNaturalidade() { return naturalidade; }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}


