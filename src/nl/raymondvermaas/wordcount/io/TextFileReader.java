/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raymond
 */
public class TextFileReader {
    
    private File[] filelist;
    private Scanner in;
    private int currentfile;
    
    public TextFileReader(String path) throws FileNotFoundException {
        currentfile = 0;
        File dir = new File(path);
        filelist = dir.listFiles(new TxtFilenameFilter());
        in = new Scanner(new FileReader(filelist[currentfile].getAbsolutePath()));
    }
    
    private boolean checkScanner() throws FileNotFoundException {
        // If the end of the file is not reached, continue
        if(in.hasNext())
            return true;
        
        // If possible open next file
        currentfile++;
        if(currentfile < filelist.length) {
            in.close();
            in = new Scanner(new FileReader(filelist[currentfile].getAbsolutePath()));
            return true;
        }
        
        // All files are processed
        in.close();
        return false;   
    }
    
    public String getNext() throws FileNotFoundException {
        if(!checkScanner())
            return null;
        return in.next();
    }
    
    public String getCurrentFile() {
        return filelist[currentfile].getName();
    }
    
}

class TxtFilenameFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return (name.endsWith(".txt"));
    }
    
}
