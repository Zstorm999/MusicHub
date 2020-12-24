package musichub.business;

import musichub.util.AudioToXML;

/**
 * Represents any audio element 
 * @see Song 
 * @see AudioBook
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public abstract class AudioElement implements AudioToXML{
    protected String title;
    protected int length;
    protected int id;
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
     * @param id the id
     * @param content the file associated to this element
     */
    public AudioElement(String title, int length, int id, String content){
        this.title = title;
        this.length = length;
        this.id = id;
        this.content = content;
    }

}
