/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.table;

/**
 *
 * @author Raymond
 */
public class CountObject {
    private int[] docCount;
    private int total;
    private String word;
    
    public CountObject(int numberOfDocuments) {
        docCount = new int[numberOfDocuments];
    }
    
    
    public void increase(int doc) {
        docCount[doc]++;
        total++;
    }
    
    public void decrease(int doc) {
        docCount[doc]--;
        total--;
    }
    
    public int getTotal() {
        return total;
    }
    
    public int getDocTotal(int doc) {
        return docCount[doc];
    }
    
    public int[] getDocCount() {
        return docCount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int get(int document) {
        return docCount[document];
    }
    
}
