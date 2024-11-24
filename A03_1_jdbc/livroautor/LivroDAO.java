package aulas.semana08.exemplosaula.livroautor;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private List<Livro> livros = new ArrayList<>();
    private int idCounter = 1;

    public void adicionarLivro(Livro livro) {
        livro = new Livro(idCounter++, livro.getTitulo(), livro.getAssunto(), livro.getIsbn(), livro.getDataPublicacao());
        livros.add(livro);
    }

    public List<Livro> listarLivros() {
        return livros;
    }
}


