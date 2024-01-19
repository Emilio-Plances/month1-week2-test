package org.example.Test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


public class Archivio {
    private List<Contenuto> contenuto=new ArrayList<>();
    private Random random=new Random();
    public Archivio(){}
    public void aggiungiLibro(String titolo, int anno, int pagine,String autore,Genere ...generi){
        long ISBN=generaISBN();
        contenuto.add(new Libro(ISBN,titolo,anno,pagine,autore, generi));
        System.out.println("Libro aggiunto");
    }
    public void aggiungiLibro(long ISBN,String titolo, int anno, int pagine,String autore,Genere ...generi){
        contenuto.add(new Libro(ISBN,titolo,anno,pagine,autore, generi));
    }
    public void aggiungiRivista(String titolo, int anno, int pagine,Periodicita periodicita){
        long ISBN=generaISBN();
        contenuto.add(new Rivista(ISBN,titolo,anno,pagine,periodicita));
        System.out.println("Rivista aggiunta");
    }
    public void aggiungiRivista(long ISBN, String titolo, int anno, int pagine,Periodicita periodicita){
        contenuto.add(new Rivista(ISBN,titolo,anno,pagine,periodicita));
    }

    public long generaISBN(){
        List<Long> ISBNList= contenuto.stream().map(Contenuto::getCodiceISBN).toList();
        long ISBN;
        do {
            ISBN = (random.nextLong(89999) + 10000);
        } while (!cercaTramiteISBN(ISBN).equals("Non trovato"));
        return ISBN;
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
                        "@"+c.getAnno();
                if(el instanceof Libro l){
                    s+="@Libro"+
                        "@"+l.getAutore()+
                        "@"+l.getGeneri();
                } else if (el instanceof Rivista r) {
                    s+="@Rivista"+
                       "@"+r.getPeriodicita();
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
    public void importa() throws IOException{
        File archivioFile=new File("archivio/archivio.txt");
        String stringaContenuti =  FileUtils.readFileToString(archivioFile, Charset.defaultCharset());
        String[] contenuti=stringaContenuti.split("#");
        if (contenuti.length==0) return;
        Arrays.stream(contenuti).forEach(c->{
            String[] cont=c.split("@");
            if(cont[4].equals("Libro")) {
                cont[6] = cont[6].substring(1, cont[6].length() - 1);
                String[] generi = cont[6].split(", ");
                List<Genere> g = Libro.convertToGenere(generi);
                Genere[] array = g.toArray(new Genere[g.size()]);
                aggiungiLibro(
                        Long.parseLong(cont[0]),
                        cont[1],
                        Integer.parseInt(cont[2]),
                        Integer.parseInt(cont[3]),
                        cont[5],
                        array
                );
            }else{
                aggiungiRivista(
                        Long.parseLong(cont[0]),
                        cont[1],
                        Integer.parseInt(cont[2]),
                        Integer.parseInt(cont[3]),
                        Rivista.convertToPeriodicita(cont[5])
                );
            }
        });
    }

}
