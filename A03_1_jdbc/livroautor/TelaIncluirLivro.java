package aulas.semana08.exemplosaula.livroautor;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaIncluirLivro {
    private JTextField tituloField, assuntoField, isbnField;
    private JDateChooser dataPublicacaoChooser;
    private JList<Autor> autoresList;
    private JButton salvarButton;

    public TelaIncluirLivro() {
        tituloField = new JTextField(20);
        assuntoField = new JTextField(20);
        isbnField = new JTextField(20);
        dataPublicacaoChooser = new JDateChooser();
        autoresList = new JList<>();
        salvarButton = new JButton("Salvar");
    }

    public void addSalvarButtonListener(ActionListener listener) {
        salvarButton.addActionListener(listener);
    }
}

