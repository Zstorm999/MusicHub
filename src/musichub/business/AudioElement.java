package musichub.business;

import musichub.util.AudioToXML;

public abstract class AudioElement implements AudioToXML{
    protected String title;
    protected int length;
    protected int id;
    protected String content;

    public AudioElement(){
        
    }

    public AudioElement(String title, int length, int id, String content){
        this.title = title;
        this.length = length;
        this.id = id;
        this.content = content;
    }

}
