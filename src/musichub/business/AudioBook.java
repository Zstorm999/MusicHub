package musichub.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import musichub.util.AudioToXML;

public class AudioBook extends AudioElement{
    private String author;
    private Languages language;
    private Categories category;

    public AudioBook(){

    }

    public AudioBook(String author, Languages language, Categories category, String title, int length, int id, String content){
        super(title, length, id, content);
        this.author = author;
        this.language = language;
        this.category = category;
    }

    public String authorToString(){
        return author;
    }
    public String languageToString(){
        return language.toString();
    }
    public String categoryToString(){
        return category.toString();
    }
    public String toString(){
        String result = author + " " + language + " " + category + " " + title + " " + length + " " + id + " " + content;
        return result;
    }

    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Author", AudioToXML.toList(author));
        attributes.put("Language", AudioToXML.toList(language.toString()));
        attributes.put("Category", AudioToXML.toList(category.toString()));
        attributes.put("Title", AudioToXML.toList(title));
        attributes.put("Length", AudioToXML.toList(Integer.toString(length)));
        attributes.put("ID", AudioToXML.toList(Integer.toString(id)));
        attributes.put("Content", AudioToXML.toList(content));

        return attributes;
    }

    public void load(Map<String, List<String>> arguments){
        try{
            author = arguments.get("Author").get(0);
            language = Languages.valueOf(arguments.get("Language").get(0).toUpperCase());
            category = Categories.valueOf(arguments.get("Category").get(0).toUpperCase());
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
