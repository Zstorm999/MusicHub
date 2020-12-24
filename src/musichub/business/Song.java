package musichub.business;

import java.util.Map;

import musichub.util.AudioToXML;

import java.util.HashMap;
import java.util.List;

public class Song extends AudioElement{
    private String artist;
    private Genres gen;

    public Song(){
        
    }

    public Song(String artist, Genres gen, String title, int length, int id, String content){
        super(title, length, id, content);
        this.artist = artist;
        this.gen = gen;
    }

    public String artistToString(){
        return artist;
    }
    public String genreToString(){
        return gen.toString();
    }
    public String toString(){
        String result = null;
        result = artist + " " + gen + " " + title + " " + length + " " + id + " " + content;
        return result;
    }

    public Map<String, List<String>> save(){
        
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Artist", AudioToXML.toList(artist));
        attributes.put("Genre", AudioToXML.toList(gen.toString()));
        attributes.put("Title", AudioToXML.toList(title));
        attributes.put("Length", AudioToXML.toList(Integer.toString(length)));
        attributes.put("ID", AudioToXML.toList(Integer.toString(id)));
        attributes.put("Content", AudioToXML.toList(content));
        

        return attributes;
    }

    public void load(Map<String, List<String>> arguments){

        try{
            artist = arguments.get("Artist").get(0);
            gen = Genres.valueOf(arguments.get("Genre").get(0).toUpperCase().replaceAll("\\s", ""));
            title = arguments.get("Title").get(0);
            length = Integer.parseInt(arguments.get("Length").get(0));
            id = Integer.parseInt(arguments.get("ID").get(0));
            content = arguments.get("Content").get(0);

        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        
    }

}
