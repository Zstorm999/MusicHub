package musichub.business;

public class ElementNotFoundException extends Exception{
    
    static final long serialVersionUID = 0;

    public ElementNotFoundException(){

    }

    public ElementNotFoundException(String msg){
        super(msg);
    }

}
