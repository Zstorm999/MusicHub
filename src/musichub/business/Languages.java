package musichub.business;

/**
 * All possible languages for books
 * @see AudioBook
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public enum Languages {
    FRENCH("French"), ENGLISH("English"), ITALIAN("Italian"), SPANISH("Spanish"), GERMAN("German");

    private String str;

    
    private Languages(String str){
        this.str = str;
    }

    /**
     * Returns the associated string
     * @return the associated string
     */
    public String toString(){
        return str;
    }
}
