package musichub.business;

/**
 * Thrown when an element is not found in a list
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
