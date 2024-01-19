package org.example.Test;

import java.util.Arrays;
import java.util.List;

public class Rivista extends Contenuto {
    Periodicita periodicita;
    public Rivista(long codiceISBN, String titolo, int anno, int pagine, Periodicita periodicita) {
        super(codiceISBN, titolo, anno, pagine);
        this.periodicita= periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", periodicità=" + periodicita+"\n";
    }
    public static Periodicita convertToPeriodicita(String s){
        if(s.equals("SETTIMANALE")) return Periodicita.SETTIMANALE;
        if(s.equals("MENSILE")) return Periodicita.MENSILE;
        return Periodicita.SEMESTRALE;
    }
}
