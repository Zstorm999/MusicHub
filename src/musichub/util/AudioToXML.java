package musichub.util;

import java.util.Map;
import java.util.Arrays;
import java.util.List;
/**
 * Represents an object that can be loaded from and to XML via the AudioXML class. <br>
 * Class implementing this interface should always have a constructor with 0 arguments.
 * 
 * @author Thomas Archambeau
 */
public interface AudioToXML {

    /**
     * Given a list of attributes as parameter, stores this attributes in an instance of the class (previously created). <br>
     * If the attribute corresponding to a name is only a single value (as opposed to a list), it will appear in the map as a list containing only one element
     * 
     * @param attributes a map of all the attributes, mapped by name 
     */
    public void load(Map<String, List<String>> attributes);

    /**
     * Puts all the attributes of the class to be saved into an XML file into a map. <br>
     * The key should be the name of the attribute. <br>
     * The value should be a list of values, or a list of one value if the attribute is not a list itself. 
     * 
     * @return the Map created
     */
    public Map<String, List<String>> save();

    /**
     * Transforms a String into a List&lt;String&gt; containing only this element.
     * @param attribute the element to put in the list
     * @return a list containing only one element
     */
    public static List<String> toList(String attribute){
        return Arrays.asList(new String[] {attribute});
    }
}
