package aulas.semana08.exemplosaula.livroautor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private List<Autor> autores = new ArrayList<>();
    private int idCounter = 1;

    public void adicionarAutor(Autor autor) {
        autor = new Autor(idCounter++, autor.getNome(), autor.getDataNascimento(), autor.getDocumento(), autor.getNaturalidade());
        autores.add(autor);
    }

    public List<Autor> listarAutores() {
        return autores;
    }
}
