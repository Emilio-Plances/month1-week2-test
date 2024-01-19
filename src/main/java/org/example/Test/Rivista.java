package org.example.Test;

public class Rivista extends ContenutoArchivio{
    public Rivista(long codiceISBN, String titolo, int anno, int pagine) {
        super(codiceISBN, titolo, anno, pagine);
    }

    public Rivista(String titolo, int anno, int pagine) {
        super(titolo, anno, pagine);
    }
}
