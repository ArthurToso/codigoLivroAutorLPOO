package aulas.semana08.exemplosaula.livroautor;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TabelaLivro extends AbstractTableModel {
    private List<Livro> livros;

    @Override
    public int getRowCount(){
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = livros.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> livro.getTitulo();
            case 1 -> livro.getAssunto();
            case 2 -> livro.getIsbn();
            case 3 -> livro.getDataPublicacao();
            default -> null;
        };
    }
}
