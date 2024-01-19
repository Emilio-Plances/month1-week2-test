package org.example.Test;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
        Archivio a=new Archivio();
        String[]  c={"HORROR,FANTASCIENZA,HORROR"};
        System.out.println(Libro.convertToGenere(c).toString());

        a.aggiungiLibro("Harry Potter e i doni della morte",2018,369,"J.K.Rowling",Genere.HORROR);
        a.aggiungiLibro("Harry Potter e la pietra filosofale",2000,369,"J.K.Rowling",
                Genere.FANTASCIENZA,Genere.HORROR);

        a.aggiungiRivista("Cioè",2024,20,Periodicita.SETTIMANALE);



        try{
            a.export();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
