/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.raymondvermaas.wordcount.filter.*;
import nl.raymondvermaas.wordcount.io.*;
import nl.raymondvermaas.wordcount.table.*;

/**
 *
 * @author Raymond
 */
public class Main {

    private static final String INPUTDIR = "C:\\Users\\Raymond\\Documents\\Mijn Huiswerk\\Master-1\\Datamining and Knowledge Discovery\\Papers";
    
    private static final Filter[] FILTERS = { 
        new BaseFilter(),
        new StopWordFilter(), 
        new StemmingFilter("english")};
    
    private static final String OUTPUTFILE = "d:\\output.csv";
    
    private static final int NUMDOCS = 26;
    
    public static void main(String[] args) {
        String[] docList = new String[NUMDOCS];
        CountTable ct = new CountTable(NUMDOCS);
        try {
            TextFileReader txt = new TextFileReader(INPUTDIR);
            
            int doc = 0;
            String curdoc = "";
            
            String in = txt.getNext();
            while(in != null) {
                if(curdoc.compareTo("") == 0 || curdoc.compareTo(txt.getCurrentFile()) != 0) {
                    curdoc = txt.getCurrentFile();
                    doc++;
                    docList[doc] = curdoc;
                }
                
                for(Filter f : FILTERS) {
                    in = f.filter(in);
                    if(in == null)
                        break;
                }
                
                if(in == null) {
                    in = txt.getNext();
                    continue;
                }
                ct.add(doc, in);
                in = txt.getNext();
            }
            
            ct.prune((float) 0.1, (float) 0.1);
            
            CsvWriter csv = new CsvWriter(OUTPUTFILE, docList);
            CountObject out = ct.iterateWord();
            while (out!=null) {
                csv.write(out);
                out = ct.iterateWord();
            }
            csv.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
