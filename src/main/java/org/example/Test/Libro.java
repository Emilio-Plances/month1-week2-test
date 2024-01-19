package org.example.Test;

public class Libro extends ContenutoArchivio{
    public Libro(long codiceISBN, String titolo, int anno, int pagine) {
        super(codiceISBN, titolo, anno, pagine);
    }

    public Libro(String titolo, int anno, int pagine) {
        super(titolo, anno, pagine);
    }
}
