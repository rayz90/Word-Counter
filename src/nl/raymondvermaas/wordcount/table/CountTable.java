/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.table;

import java.util.*;

/**
 *
 * @author Raymond
 */
public class CountTable {

    private HashMap<String, CountObject> countTable;
    private int numDocs;
    
    public CountTable(int numDocs) {
        countTable= new HashMap<String, CountObject>();
        this.numDocs=numDocs;
    }
    
    public void add(int document, String word) {
        int docloc = document;        
        if(countTable.containsKey(word))
            countTable.get(word).increase(docloc);
        else {
            countTable.put(word, new CountObject(numDocs));
            countTable.get(word).increase(docloc);
            countTable.get(word).setWord(word);
        }
            
    }
    
    public int get(int document, String word) {
        return countTable.get(word).get(document);
    }
    
    public String[] getWords() {
       return (String[]) countTable.keySet().toArray(new String[1]);
    }
    
    
    public void prune(int threshold) {        
        Iterator it = countTable.values().iterator();
        ArrayList<String> delete = new ArrayList<String>();
        while(it.hasNext()) {
            CountObject co = (CountObject) it.next();
            if(co.getTotal() < threshold)
                delete.add(co.getWord());
        }
        
        for(Object del : delete.toArray())
            countTable.remove((String) del);
    }
}



