package org.example.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Periodicita> convertToPeriodicita(String[] arr){
        return Arrays.stream(arr).map(el->{
            if(el.equals("SETTIMANALE")) return Periodicita.SETTIMANALE;
            if(el.equals("MENSILE")) return Periodicita.MENSILE;
            return Periodicita.SEMESTRALE;
        }).toList();
    }
}
