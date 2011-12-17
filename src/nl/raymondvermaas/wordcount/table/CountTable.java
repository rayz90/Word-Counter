/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.table;

import nl.raymondvermaas.wordcount.table.CountObject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author Raymond
 */
public class CountTable {

    private HashMap<String, CountObject> countTable;
    private int numDocs;
    private Iterator itw;
    
    public CountTable(int numDocs) {
        countTable= new HashMap<String, CountObject>();
        this.numDocs=numDocs;
        itw =null;
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
    
    public CountObject iterateWord() {
        if(itw==null)          
            itw = countTable.values().iterator();
        if (itw.hasNext())
            return (CountObject) itw.next();
        itw = null;
        return null;          
    }
    
    public void prune(float topPercent, float bottomPercent) {
        TotalComparator tc =  new TotalComparator(countTable);
        TreeMap<String, CountObject> sortedMap = new TreeMap<String, CountObject>(tc);
        sortedMap.putAll(countTable);
        int top = Math.round(topPercent*sortedMap.size());
        int bottom = Math.round((1-bottomPercent)*sortedMap.size());
        
        int i=0;
        for (String key : sortedMap.keySet()) {
            if(i<=top || i>=bottom )
                countTable.remove(key);
            i++;          
        }

    }
}

class TotalComparator implements Comparator {

  HashMap<String, CountObject> map;
  public TotalComparator(HashMap<String, CountObject> map) {
      this.map = map;
  }

    @Override
  public int compare(Object a, Object b) {

    if((Integer) map.get(a).getTotal() < (Integer)map.get(b).getTotal()) {
      return 1;
    } else if((Integer) map.get(a).getTotal() == (Integer)map.get(b).getTotal()) {
      return 0;
    } else {
      return -1;
    }
  }
}



