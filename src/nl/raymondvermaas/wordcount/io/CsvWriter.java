/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import nl.raymondvermaas.wordcount.table.CountTable;

/**
 *
 * @author Raymond
 */
public class CsvWriter {
    private BufferedWriter bw;
    private String[] docs;
    private String SEP = ";";
    
    public CsvWriter(String filepath, String[] docs) throws IOException {
        bw = new BufferedWriter(new FileWriter(filepath));
        this.docs = docs;
    }
    
    public void writeToFile(CountTable ct) throws IOException {
        String[] words = ct.getWords();
        for(String word : words) {
            bw.write(SEP+word);
            
        }
        bw.newLine();
        
        int i = 0;
        for (String doc : docs) {
            bw.write(doc);
            for (String word : words)
                bw.write(SEP+ct.get(i, word));
            bw.newLine();
            i++;
        }
    }
    
    public void close() throws IOException {
        bw.flush();
        bw.close();
    }
}
