package org.example.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Contenuto {

    private final long codiceISBN;
    private final String titolo;
    private final int anno;
    private final int pagine;

    public Contenuto(long codiceISBN, String titolo, int anno, int pagine){
        this.codiceISBN=codiceISBN;
        this.titolo=titolo;
        this.anno=anno;
        this.pagine=pagine;
    }
    public String getTitolo() {
        return titolo;
    }
    public int getAnno() {
        return anno;
    }
    public int getPagine() {
        return pagine;
    }
    public long getCodiceISBN() {
        return codiceISBN;
    }

    @Override
    public String toString() {
        return  "CodiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", pagine=" + pagine;
    }
}
