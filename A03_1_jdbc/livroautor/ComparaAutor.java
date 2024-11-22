package aulas.semana08.exemplosaula.livroautor;

import java.util.Comparator;

public class ComparaAutor implements Comparator<Autor>{
    @Override
    public int compare(Autor autor1, Autor autor2){
        return autor1.getNome().compareToIgnoreCase(autor2.getNome());
    }
}
