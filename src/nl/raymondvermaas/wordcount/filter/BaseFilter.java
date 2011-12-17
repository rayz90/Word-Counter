/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.filter;

/**
 *
 * @author Raymond
 */
public class BaseFilter implements Filter {
    
    @Override
    public String filter (String word) {
        //remove white spaces
        word = word.trim(); 
        // make word lowercase
        word = word.toLowerCase();
        //remove non-word characters
        word = word.replaceAll("\\W", ""); 
        //Omit single characters and empty strings
        if(word.length() <= 1)
            return null;
        return word;
    }
    
}
