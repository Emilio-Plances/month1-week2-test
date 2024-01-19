package org.example.Test;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
        Archivio a=new Archivio();
        try{
            a.importa();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(a.cercaTramiteISBN(24266));
        System.out.println(a.cercaTramiteISBN(14277));

        try{
            a.export();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
