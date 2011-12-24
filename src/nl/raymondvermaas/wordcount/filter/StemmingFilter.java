/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package nl.raymondvermaas.wordcount.filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tartarus.snowball.*;

/**
 *
 * @author Raymond
 */
public class StemmingFilter implements Filter{

    public SnowballStemmer stemmer;
    public StemmingFilter(String language){
        try {
            Class stemClass = Class.forName("org.tartarus.snowball.ext." +
                                            language + "Stemmer");
            this.stemmer = (SnowballStemmer) stemClass.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(StemmingFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public StemmingFilter() {
        try {
            Class stemClass = Class.forName("org.tartarus.snowball.ext.english.Stemmer");
            this.stemmer = (SnowballStemmer) stemClass.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(StemmingFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String filter(String word) {
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }
    
}
