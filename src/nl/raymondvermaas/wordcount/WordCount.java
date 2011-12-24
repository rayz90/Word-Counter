/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.raymondvermaas.wordcount;

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.raymondvermaas.wordcount.filter.Filter;
import nl.raymondvermaas.wordcount.io.CsvWriter;
import nl.raymondvermaas.wordcount.io.WordReader;
import nl.raymondvermaas.wordcount.table.CountObject;
import nl.raymondvermaas.wordcount.table.CountTable;

/**
 *
 * @author Raymond
 */
public class WordCount {
    private String inputdir;
    private Filter[] filters;
    private String outputfile;
    
    public WordCount(String indir, String output, Filter[] filters) {
        this.inputdir = indir;
        this.filters = filters;
        this.outputfile = output;
    }
    
    public void run() {
        String[] docList = null;
        CountTable ct = null;
        try {
            WordReader txt = new WordReader(inputdir, "txt");
            docList = new String[txt.numberOfFiles()];
            ct = new CountTable(txt.numberOfFiles());
            
            int doc = -1;
            String curdoc = "";
            
            String in = txt.getNext();
            while(in != null) {
                if(curdoc.compareTo("") == 0 || curdoc.compareTo(txt.getCurrentFile()) != 0) {
                    curdoc = txt.getCurrentFile();
                    doc++;
                    docList[doc] = curdoc;
                }
                
                for(Filter f : filters) {
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
            
            // Removing words occuring only once
            ct.prune(2);
            
            CsvWriter csv = new CsvWriter(outputfile, docList);
            csv.writeToFile(ct);
            csv.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
