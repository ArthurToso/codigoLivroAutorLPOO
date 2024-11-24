package aulas.semana08.exemplosaula.livroautor;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaIncluirAutor extends JFrame {
    private JTextField nomeField, documentoField, naturalidadeField;
    private JDateChooser dataNascimentoChooser;
    private JButton salvarButton;

    public TelaIncluirAutor() {
        nomeField = new JTextField(20);
        documentoField = new JTextField(20);
        naturalidadeField = new JTextField(20);
        dataNascimentoChooser = new JDateChooser();
        salvarButton = new JButton("Salvar");
    }

    public void addSalvarButtonListener(ActionListener listener) {
        salvarButton.addActionListener(listener);
    }
}
