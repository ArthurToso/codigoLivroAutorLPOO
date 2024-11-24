package aulas.semana08.exemplosaula.livroautor;

import java.util.List;

public class Controller {
    private LivroDAO livroDAO;
    private AutorDAO autorDAO;

    public Controller() {
        livroDAO = new LivroDAO();
        autorDAO = new AutorDAO();
    }

    public void adicionarLivro(Livro livro) {
        livroDAO.adicionarLivro(livro);
    }

    public void adicionarAutor(Autor autor) {
        autorDAO.adicionarAutor(autor);
    }

    public List<Livro> listarLivros() {
        return livroDAO.listarLivros();
    }

    public List<Autor> listarAutores() {
        return autorDAO.listarAutores();
    }
}
