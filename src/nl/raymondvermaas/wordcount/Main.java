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

    private static String inputdir = "C:\\Papers";
    
    private static Filter[] filters= { 
        new BaseFilter(),
        new StopWordFilter(), 
        new StemmingFilter("english")
    };
    
    private static String outputfile= "C:\\output.csv";
    
    
    public static void main(String[] args) {
       if(args.length < 1 || args[0].compareTo("help") == 0);
           Main.usage();
       try {
           inputdir = args[0];
           outputfile = args[1];
           if(args[2].compareTo("-f") == 0) {
               filters = new Filter[(args.length-3)];
               for(int i=3;i<args.length;i++) {
                   Class c = Class.forName(args[i]);
                   filters[(i-3)] = (Filter) c.newInstance();
               }
           }
       } catch (Exception ex) {
           Main.usage();
           
       }
       
       WordCount wc = new WordCount(inputdir, outputfile, filters);
       wc.run();       
    }

    private static void usage() {
        System.out.println("wordcount INPUTDIR OUTPUTFILE [-f nl.raymondvermaas.wordcount.filter]\n");
        System.out.println("INPUTDIR");
        System.out.println("\tThe location of the documents");
        System.out.println("OUTPUTFILE\t");
        System.out.println("\tThe location of the output CSV file");
        System.out.println("-f nl.raymondvermaas.wordcount.filter");
        System.out.println("\tThe filters to run in the right order. Possible filters are");
        System.out.println("\tnl.raymondvermaas.wordcount.BaseFilter ; nl.raymondvermaas.wordcount.StopWordFilter");
        System.out.println("\tnl.raymondvermaas.wordcount.StemmingFilter ; nl.raymondvermaas.wordcount.LemmaFilter");
        System.exit(0);
    }
    
    
}
