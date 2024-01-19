package org.example.Test;

import java.util.Random;

public abstract class ContenutoArchivio {
    Random random=new Random();
    long codiceISBN;
    String titolo;
    int anno;
    int pagine;

    public ContenutoArchivio(long codiceISBN,String titolo,int anno,int pagine){
        this.codiceISBN=codiceISBN;
        this.titolo=titolo;
        this.anno=anno;
        this.pagine=pagine;
    }

    public ContenutoArchivio(String titolo,int anno,int pagine){
        this.codiceISBN=(random.nextLong(89999)+10000);
        this.titolo=titolo;
        this.anno=anno;
        this.pagine=pagine;
    }
}
