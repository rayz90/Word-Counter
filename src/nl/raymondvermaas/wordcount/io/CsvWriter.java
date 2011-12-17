/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import nl.raymondvermaas.wordcount.table.CountObject;

/**
 *
 * @author Raymond
 */
public class CsvWriter {
    private BufferedWriter bw;
    
    public CsvWriter(String filepath, String[] docs) throws IOException {
        bw = new BufferedWriter(new FileWriter(filepath));
        bw.write("-");
        for(String doc:docs) {
            bw.write(";" + doc);
        }
        bw.newLine();
    }
    
    public void write(CountObject co) throws IOException {
        bw.write(co.getWord());
        for(int i:co.getDocCount())
            bw.write(";"+i);
        bw.newLine();
    }
    
    public void close() throws IOException {
        bw.flush();
        bw.close();
    }
}
