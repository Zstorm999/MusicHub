package musichub.business;

/**
 * All possible categories of books
 * @see AudioBook
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public enum Categories{
    YOUTH("Youth"), NOVEL("Novel"), THEATER("Theater"), SPEECH("Speech"), DOCUMENTARY("Documentary");

    private final String str;

    /**
     * Associates a string to the category
     * @param str the string to associate
     */
    private Categories(String str){
        this.str = str;
    }

    /**
     * Returns the string associated
     * @return the string associated
     */
    public String toString(){
        return str;
    }
}