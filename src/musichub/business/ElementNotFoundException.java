package musichub.business;

/**
 * Represents the exception catch when the current element is not founds
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class ElementNotFoundException extends Exception{
    
    static final long serialVersionUID = 0;

    /**
     * Empty constructor
     */
    public ElementNotFoundException(){

    }

    /**
     * Creates an exception of type "ElementNotFoundException"
     * @param msg message to display
     */
    public ElementNotFoundException(String msg){
        super(msg);
    }

}
