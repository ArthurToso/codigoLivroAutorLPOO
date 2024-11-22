package aulas.semana08.exemplosaula.livroautor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainLivroAutor {
    
    private AutorDAO autorDAO;
    private LivroDAO livroDAO;

    public MainLivroAutor() throws Exception{
        autorDAO = new AutorDAO();
        livroDAO = new LivroDAO();
    }

    public static void main(String args[]) throws Exception{
        MainLivroAutor main = new MainLivroAutor();
        String opcao = "";
        while (true) {
            try{
            System.out.println("Escolha uma das opções e tecle <ENTER>: ");
            System.out.println("  1 - Incluir Autor");
            System.out.println("  2 - Incluir Livro");
            System.out.println("  3 - Listar Autores");
            System.out.println("  4 - Listar Livro com autores");
            System.out.println("  5 - Listar Autores de um livro");
            System.out.println("  6 - Listar Livros de um autor");
            System.out.println("  7 - Sair");
            Scanner sc = new Scanner(System.in,"ISO-8859-1");
            opcao = sc.nextLine();
            switch(opcao){
                case "1":
                    main.incluirAutor();
                    break;
                case "2":
                    main.incluirLivro();
                    break;
                case "3":
                    main.listarAutores();
                    break;
                case "4":
                    main.listarLivroComAutores();
                    break;
                case "5":
                    main.listarAutoresDeLivro();
                    break;
                case "6":
                    main.listarLivrosDeAutor();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            if(opcao.equals("7")){
                break;
            }
            }catch(Exception ex){
                System.out.println("Falha na operação. Mensagem="+ ex.getMessage());
                //ex.printStackTrace();
            }
        }
    }
    public void incluirAutor() throws Exception{
        System.out.print("Digite o nome do autor:");
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String nome = sc.nextLine();
        Autor autor = new Autor(nome);
        List<Integer> livrosIds = new ArrayList<>();
        System.out.println("Digite os ID's dos livros escritos pelo autor, para sair digite -1");

        while(true){
            try{
                System.out.println("ID Livro: ");
                int idLivro = sc.nextInt();

                if(idLivro == -1) break;

                Livro livro = livroDAO.consultarLivro(idLivro);
                if(livro != null){
                    livrosIds.add(idLivro);
                    System.out.println("Livro: " + livro.getTitulo() + "associado com sucesso");
                }else{
                    System.out.println("Livro nao encontrado");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!livrosIds.isEmpty()) {
            autorDAO.inserirAutor(autor, livrosIds);
            System.out.println("Autor e seus livros associados inseridos com sucesso!");
        } else {
            autorDAO.inserirAutor(autor, null);
            System.out.println("Autor inserido sem livros associados.");
        }

    }

    public void incluirLivro() {
        System.out.print("Digite o título do livro:");
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String titulo = sc.nextLine();
        int numAutores=1;
        List<Autor> listaAutores = new ArrayList();
        int idAutor=0;
        do{
            try{
                Scanner sc2 = new Scanner(System.in,"ISO-8859-1");
                System.out.print("ID Autor "+numAutores+":");
                idAutor = sc2.nextInt();
                if(idAutor==-1)
                    break;
                Autor autor = autorDAO.consultarAutor(idAutor);
                if(autor != null){
                    listaAutores.add(autor);
                    numAutores++;
                }else{
                    System.out.println("Autor não existe!");
                }
            }
            catch(Exception ex){
                System.out.println("ID autor não é inteiro ou inválido!");
            }
        }while(true);


        Livro livro = new Livro(titulo,listaAutores);
        livroDAO.inserirLivro(livro);
    }

    public void listarAutores() throws Exception{
        List<Autor> listaAutores = autorDAO.listarAutores();

        Collections.sort(listaAutores, new ComparaAutor());

        System.out.println("ID\tNOME");
        for(Autor autor:listaAutores){
            System.out.println(autor.getId()+" \t"+autor.getNome());
        }
    }

    public void listarLivroComAutores() throws Exception{
        List<Livro> listaLivros = livroDAO.listarLivroComAutores();
        Collections.sort(listaLivros, new Comparator<Livro>() {
           public int compare(Livro arg0, Livro arg1) {
               String titulo = arg0.getTitulo();
               int i = titulo.compareToIgnoreCase(arg1.getTitulo());
               return i;
             }
        });
        System.out.println("ID\tTitulo do Livro\tAutores");
        for(Livro livro:listaLivros){
            System.out.print(livro.getId()+"\t"+livro.getTitulo()+"\t");
            for(Autor autor:livro.getAutores()){
                System.out.print(autor.getNome()+";");
            }
            System.out.print("\n");
        }

    }

    public void listarAutoresDeLivro() {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");

        try {
            // Solicitar ID do livro
            System.out.print("Digite o ID do livro: ");
            int idLivro = sc.nextInt();

            // Consultar autores associados ao livro
            List<Autor> autores = livroDAO.listarAutoresDeLivro(idLivro);

            if (autores.isEmpty()) {
                System.out.println("Nenhum autor encontrado para o livro com ID " + idLivro);
            } else {
                System.out.println("Autores do Livro com ID " + idLivro + ":");
                for (Autor autor : autores) {
                    System.out.println("- ID: " + autor.getId() + " | Nome: " + autor.getNome());
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar autores de um livro. Mensagem: " + ex.getMessage());
        }
    }

    public void listarLivrosDeAutor() {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        try {
            // Solicitar ID do autor
            System.out.print("Digite o ID do autor: ");
            int idAutor = sc.nextInt();

            // Consultar livros associados ao autor
            List<Livro> livros = autorDAO.listarLivrosDeAutor(idAutor);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado para o autor com ID " + idAutor);
            } else {
                System.out.println("Livros do Autor com ID " + idAutor + ":");
                for (Livro livro : livros) {
                    System.out.println("- ID: " + livro.getId() + " | Título: " + livro.getTitulo());
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar livros de um autor. Mensagem: " + ex.getMessage());
        }
    }
}
