package org.example.Test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


public class Archivio {
    private List<Contenuto> contenuto=new ArrayList<>();
    private List<Libro> libri=new ArrayList<>();
    private List<Rivista> riviste=new ArrayList<>();
    private Random random=new Random();
    public Archivio(){}
    public void aggiungiLibro(String titolo, int anno, int pagine,String autore,Genere ...generi){
        long ISBN=generaISBN();
        Libro l=new Libro(ISBN,titolo,anno,pagine,autore,generi);
        contenuto.add(l);
        libri.add(l);
        System.out.println("Libro aggiunto");
    }
    public void aggiungiLibro(long ISBN,String titolo, int anno, int pagine,String autore,Genere ...generi){
        Libro l=new Libro(ISBN,titolo,anno,pagine,autore,generi);
        contenuto.add(l);
        libri.add(l);
        System.out.println("Libro aggiunto");
    }
    public void aggiungiRivista(String titolo, int anno, int pagine,Periodicita periodicita){
        long ISBN=generaISBN();
        Rivista r=new Rivista(ISBN,titolo,anno,pagine,periodicita);
        riviste.add(r);
        contenuto.add(r);
        System.out.println("Rivista aggiunta");
    }
    public void aggiungiRivista(long ISBN, String titolo, int anno, int pagine,Periodicita periodicita){
        Rivista r=new Rivista(ISBN,titolo,anno,pagine,periodicita);
        riviste.add(r);
        contenuto.add(r);
        System.out.println("Rivista aggiunta");
    }

    public long generaISBN(){
        List<Long> ISBNList= contenuto.stream().map(Contenuto::getCodiceISBN).toList();
        long ISBN;
        do {
            ISBN = (random.nextLong(89999) + 10000);
        } while (!cercaTramiteISBN(ISBN).equals("Non trovato"));
        return ISBN;
    }

    public List<Libro> getLibri() {
        return libri;
    }
    public List<Rivista> getRiviste() {
        return riviste;
    }
    public String cercaTramiteISBN (long ISBN){
        List<Contenuto> search=contenuto.stream().filter(el->el.getCodiceISBN()==ISBN).toList();
        if(!search.isEmpty())
            return search.getFirst().toString();
        return "Non trovato";
    }
    public String cercaTramiteAnno (int anno){
        List<Contenuto> search=contenuto.stream().filter(el->el.getAnno()==anno).toList();
        if(!search.isEmpty())
            return search.toString();
        return "Non trovato";
    }
    public String cercaTramiteAutore (String autore){
        List<Contenuto> search=contenuto.stream().filter(el->el instanceof Libro).filter(el->((Libro) el).getAutore().equals(autore)).toList();
        if(!search.isEmpty())
            return search.toString();
        return "Non trovato";
    }
    public void rimuovi(long ISBN){
        contenuto=contenuto.stream().filter(el->(el.getCodiceISBN()!=ISBN)).toList();
    }
    public void export() throws IOException{
        File archivioFile=new File("archivio/archivio.txt");

        FileUtils.writeStringToFile(archivioFile,"", Charset.defaultCharset(),false);
        contenuto.forEach(el->{
            String stringaContenuti = contenuto.stream().map(c->{
                String s= c.getCodiceISBN()+
                        "@"+c.getTitolo()+
                        "@"+c.getPagine()+
                        "@"+c.getAnno()+
                        "@";
                if(el instanceof Libro l){
                    s+="Libro@"+l.getAutore()+
                            "@"+l.getGeneri();
                } else if (el instanceof Rivista r) {
                    s+="Rivista@"+r.getPeriodicita();
                }
                return s;
            }).collect(Collectors.joining("#"));

            try{
                FileUtils.writeStringToFile(archivioFile,stringaContenuti, Charset.defaultCharset(),true);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }

        });
    }
    /*public void importa() throws IOException{
        File archivioFile=new File("archivio/archivio.txt");
        String stringaContenuti =  FileUtils.readFileToString(archivioFile, Charset.defaultCharset());
        String[] contenuti=stringaContenuti.split("#");
        contenuto=Arrays.stream(contenuti).map(c->{
            String[] cont=c.split("@");
            if(cont[4].equals("Libro"))
               cont[6]=cont[6].substring(1,cont[6].length()-1);
                String[] generi=cont[6].split(", ");
                List<Genere> g=Libro.convertToGenere(generi);

                return new Libro(
                        Long.parseLong(cont[0]),
                        cont[1],
                        cont[2],
                        Integer.parseInt(cont[3]),
                        cont[5],
                        g.toArray());
        });
    }*/

}
