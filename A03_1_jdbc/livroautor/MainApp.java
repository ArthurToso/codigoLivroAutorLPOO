package aulas.semana08.exemplosaula.livroautor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainApp extends JFrame {
    private Controller controller;

    public MainApp() {
        controller = new Controller();
        setTitle("Sistema Livro/Autor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Tela de Inclusão de Autor
        tabs.add("Incluir Autor", criarTelaIncluirAutor());

        // Tela de Inclusão de Livro
        tabs.add("Incluir Livro", criarTelaIncluirLivro());

        // Tela de Listagem de Livros
        tabs.add("Listar Livros", criarTelaListarLivros());

        // Tela de Listagem de Autores
        tabs.add("Listar Autores", criarTelaListarAutores());

        add(tabs);
    }

    private JPanel criarTelaIncluirAutor() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nomeField = new JTextField();
        JTextField dataNascimentoField = new JTextField();
        JTextField documentoField = new JTextField();
        JTextField naturalidadeField = new JTextField();
        JButton btnSalvar = new JButton("Salvar");

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);

        panel.add(new JLabel("Data de Nascimento:"));
        panel.add(dataNascimentoField);

        panel.add(new JLabel("Documento:"));
        panel.add(documentoField);

        panel.add(new JLabel("Naturalidade:"));
        panel.add(naturalidadeField);

        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            Autor autor = new Autor(0, nomeField.getText(), dataNascimentoField.getText(),
                    documentoField.getText(), naturalidadeField.getText());
            controller.adicionarAutor(autor);
            JOptionPane.showMessageDialog(this, "Autor cadastrado com sucesso!");
        });

        return panel;
    }

    private JPanel criarTelaIncluirLivro() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField tituloField = new JTextField();
        JTextField assuntoField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField dataPublicacaoField = new JTextField();
        JButton btnSalvar = new JButton("Salvar");

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);

        panel.add(new JLabel("Assunto:"));
        panel.add(assuntoField);

        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        panel.add(new JLabel("Data de Publicação:"));
        panel.add(dataPublicacaoField);

        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            Livro livro = new Livro(0, tituloField.getText(), assuntoField.getText(),
                    isbnField.getText(), dataPublicacaoField.getText());
            controller.adicionarLivro(livro);
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
        });

        return panel;
    }

    private JPanel criarTelaListarLivros() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            List<Livro> livros = controller.listarLivros();
            StringBuilder builder = new StringBuilder();
            for (Livro livro : livros) {
                builder.append(livro.toString()).append("\n");
            }
            textArea.setText(builder.toString());
        });

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(btnAtualizar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel criarTelaListarAutores() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            List<Autor> autores = controller.listarAutores();
            StringBuilder builder = new StringBuilder();
            for (Autor autor : autores) {
                builder.append(autor.toString()).append("\n");
            }
            textArea.setText(builder.toString());
        });

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(btnAtualizar, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}





