package musichub.business;

/**
 * Thrown while trying to set the ID of an element if it has already been set
 * @author Thomas Archambeau
 */
public class IDAlreadySetException extends Exception{


    static final long serialVersionUID = 0;

    /**
     * Empty constructor
     */
    public IDAlreadySetException(){

    }

    /**
     * Creates a ne Exception
     * @param msg message to display
     */
    public IDAlreadySetException(String msg){
        super(msg);
    }
}
