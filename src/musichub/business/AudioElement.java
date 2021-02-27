package musichub.business;

import java.util.List;

import musichub.util.IAudioToXML;

/**
 * Represents any audio element 
 * @see Song 
 * @see AudioBook
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public abstract class AudioElement implements IAudioToXML, IHasAnID{
    protected String title;
    protected int length;
    protected int id = -1;
    protected String content;

    /**
     * Empty constructor, does nothing (but needed for XML storage)
     */
    public AudioElement(){
        
    }

    /**
     * Creates a new AudioElement
     * @param title the title
     * @param length the length in seconds
     * @param content the file associated to this element
     */
    public AudioElement(String title, int length, String content){
        this.title = title;
        this.length = length;
        this.content = content;
    }

    /**
     * Copy constructor
     * @param other another audio element
     */
    public AudioElement(AudioElement other){
        this.title = other.title;
        this.length = other.length;
        this.content = other.content;
    }

    /**
     * Returns the element's ID
     * @return the element's ID
     */
    public int getID(){
        return id;
    }

    public void setID(int newID) throws IDAlreadySetException{

        if(id != -1){
            throw new IDAlreadySetException("This element already has an ID! Why are you trying to resetting it ?");
        }

        this.id = newID;

    }

    /**
     * Returns the total length in seconds
     * @return the total length in seconds
     */
    public int getLength(){ return length;}

    /**
     * Returns the title
     * @return the title
     */
    public String getTitle(){ return title;}

    /**
     * Returns the content (the path to the file)
     * @return the content (the path to the file)
     */
    public String getContent(){ return content;}

    public abstract String toString();

    /**
     * Returns the first element having a title passed as parameter in a list
     * @param title title to search for
     * @param list the list to search in
     * @return the element if found
     * @throws ElementNotFoundException if the element with given title was not found in the list
     */
    public static AudioElement getElementWithTitle(String title, List<? extends AudioElement> list) throws ElementNotFoundException{
        for(AudioElement elt : list){
            if(elt.title.equals(title)) return elt;
        }

        throw new ElementNotFoundException("Unable to find AudioElement with title: " + title + " in the list given");
    }

}
