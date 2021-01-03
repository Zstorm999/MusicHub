package musichub.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import musichub.util.AudioToXML;

/**
 * Represents an audio book
 * @see Song
 * @see AudioElement
 * @see Categories
 * @see Languages
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class AudioBook extends AudioElement implements Comparable<AudioBook>{
    private String author;
    private Languages language;
    private Categories category;

    /**
     * Empty constructor, does nothing (but needed for XML storage)
     */
    public AudioBook(){

    }

    /**
     * Creates a new AudioBook
     * @param author the author
     * @param language the language
     * @param category the category
     * @param title the title
     * @param length the length in seconds
     * @param id the id
     * @param content the file associated to this book
     */
    public AudioBook(String author, Languages language, Categories category, String title, int length, int id, String content){
        super(title, length, id, content);
        this.author = author;
        this.language = language;
        this.category = category;
    }

    /**
     * Returns the author of the book
     * @return the author of the book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Returns the language of the book
     * @return the language of the book
     */
    public Languages getLanguage(){
        return language;
    }

    /**
     * Returns the category of the book
     * @return the category of the book
     */
    public Categories getCategory(){ return category; }

    /**
     * Returns a complete string with all the attributes
     * @return a complete string with all the attributes
     */
    public String toString(){
        String result = title + ", by : " + author + ", " + language + ", " + category + ", " + length + "s, " + content + "; ";
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

    
    public void load(Map<String, List<String>> attributes){
        try{
            author = attributes.get("Author").get(0);
            language = Languages.valueOf(attributes.get("Language").get(0).toUpperCase());
            category = Categories.valueOf(attributes.get("Category").get(0).toUpperCase());
            title = attributes.get("Title").get(0);
            length = Integer.parseInt(attributes.get("Length").get(0));
            id = Integer.parseInt(attributes.get("ID").get(0));
            content = attributes.get("Content").get(0);

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

    /**
     * @param book the book to compare (by author)
     * @return the result of the comparison of author of both books
     */
    public int compareTo(AudioBook book) {
        return this.author.compareTo(book.author);
    }
}
