package musichub.business;

import java.util.List;

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

    public int getID(){
        return id;
    }

    /**
     * Returns the first element having a title passed as parameter in a list
     * @param title title to search for
     * @param list the list to search in
     * @return the element if found
     * @throws ElementNotFoundException if the element with given title was not found in the list
     */
    public static AudioElement getElementWithTitle(String title, List<AudioElement> list) throws ElementNotFoundException{
        for(AudioElement elt : list){
            if(elt.title == title) return elt;
        }

        throw new ElementNotFoundException("Unable to find AudioElement with title: " + title + " in the list given");
    }

}
