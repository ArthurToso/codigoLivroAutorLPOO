package aulas.semana08.exemplosaula.livroautor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private final String stmtInserir = "INSERT INTO autor(nome) VALUES(?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor";
    private final String stmtInserirRelacionamento = "INSERT INTO Livro_Autor(idLivro, idAutor) VALUES(?, ?)";

    public void inserirAutor(Autor autor, List<Integer> livrosIds) {
        Connection con = null;

        PreparedStatement stmt = null;
        try {
            // Inserir autor
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
            autor.setId(lerIdAutor(stmt));

            // Relacionar com livros
            if (livrosIds != null && !livrosIds.isEmpty()) {
                inserirRelacionamento(con, autor.getId(), livrosIds);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor e seus livros no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try { stmt.close(); } catch (Exception ex) { System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage()); }
            try { con.close(); } catch (Exception ex) { System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage()); }
        }
    }

    private void inserirRelacionamento(Connection con, int idAutor, List<Integer> livrosIds) throws SQLException {
        PreparedStatement stmtRelacionamento = null;
        try {
            stmtRelacionamento = con.prepareStatement(stmtInserirRelacionamento);
            for (Integer idLivro : livrosIds) {
                stmtRelacionamento.setInt(1, idLivro);
                stmtRelacionamento.setInt(2, idAutor);
                stmtRelacionamento.addBatch();
            }
            stmtRelacionamento.executeBatch();
        } finally {
            if (stmtRelacionamento != null) stmtRelacionamento.close();
        }
    }

    private int lerIdAutor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Autor consultarAutor(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autor autorLido;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                autorLido = new Autor(rs.getString("nome"));
                autorLido.setId(rs.getInt("id"));
                return autorLido;
            } else {
                throw new RuntimeException("Não existe autor com este id. Id=" + id);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um autor no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try { rs.close(); } catch (Exception ex) { System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage()); }
            try { stmt.close(); } catch (Exception ex) { System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage()); }
            try { con.close(); } catch (Exception ex) { System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage()); }
        }
    }

    public List<Autor> listarAutores() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Autor> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("nome"));
                autor.setId(rs.getInt("id"));
                lista.add(autor);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        } finally {
            try { rs.close(); } catch (Exception ex) { System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage()); }
            try { stmt.close(); } catch (Exception ex) { System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage()); }
            try { con.close(); } catch (Exception ex) { System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage()); }
        }
    }

    public List<Livro> listarLivrosDeAutor(int idAutor) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Livro> livros = new ArrayList<>();
        String sql = """
        SELECT l.id, l.titulo
        FROM Livro l
        JOIN Livro_Autor la ON l.id = la.idLivro
        WHERE la.idAutor = ?
    """;

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAutor);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"), new ArrayList<>());
                livro.setId(rs.getInt("id"));
                livros.add(livro);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar livros do autor. Origem=" + ex.getMessage());
        } finally {
            try { rs.close(); } catch (Exception ex) { System.out.println("Erro ao fechar ResultSet. Ex=" + ex.getMessage()); }
            try { stmt.close(); } catch (Exception ex) { System.out.println("Erro ao fechar PreparedStatement. Ex=" + ex.getMessage()); }
            try { con.close(); } catch (Exception ex) { System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage()); }
        }
        return livros;
    }

}
