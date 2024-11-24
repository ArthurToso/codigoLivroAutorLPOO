package aulas.semana08.exemplosaula.livroautor;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TabelaAutor extends AbstractTableModel {
    private List<Autor> autores;

    @Override
    public int getRowCount() {
        return autores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autor autor = autores.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> autor.getNome();
            case 1 -> autor.getDataNascimento();
            case 2 -> autor.getDocumento();
            case 3 -> autor.getNaturalidade();
            default -> null;
        };
    }
}
