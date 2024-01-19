package org.example.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Libro extends Contenuto {
    private String autore;
    private List<Genere> generi=new ArrayList<>();
    public Libro(long codiceISBN, String titolo, int anno, int pagine, String autore,Genere ...generi) {
        super(codiceISBN, titolo, anno, pagine);
        this.autore=autore;
        Arrays.stream(generi).forEach(el->this.generi.add(el));
    }

    public String getAutore() {
        return autore;
    }
    public List<Genere> getGeneri() {
        return generi;
    }
    public void aggiungiGenere(Genere g){
        generi.add(g);
    }
    @Override
    public String toString() {
        return super.toString()+
                ", autore='" + autore + '\'' +
                ", generi=" + generi+"\n";
    }

    public static List<Genere> convertToGenere(String[] arr){
        return Arrays.stream(arr).map(el->{
            if(el.equals("FANTASCIENZA")) return Genere.FANTASCIENZA;
            if(el.equals("GIALLO")) return Genere.GIALLO;
            return Genere.HORROR;
        }).toList();
    }
}
