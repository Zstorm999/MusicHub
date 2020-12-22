package musichub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Handles all XML-related manipulation for objects implementing the AudioToXML interface.
 * @see AudioToXML
 * @author Thomas Archambeau
 */
public class AudioXML<T extends AudioToXML>{
    
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dbBuilder;
    private TransformerFactory transformerFactory;
    private Transformer transformer;

    private String filepath;
    private Class<T> klass;
    private String xmlTypeIdentifier;

    /**
     * Creates a new AudioXML object.
     * @param file The path to the xml file where the data is stored
     * @param klass The class of the objects that will be handled: Object.class
     */
    public AudioXML(String file, Class<T> klass){
        this.filepath = file;
        this.klass = klass;
        this.xmlTypeIdentifier = klass.getSimpleName();
        System.out.println(xmlTypeIdentifier);

        try{
            dbFactory = DocumentBuilderFactory.newInstance();
            dbBuilder = dbFactory.newDocumentBuilder();
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch(TransformerException tfe){
            tfe.printStackTrace();
        }
    }

    private NodeList parseXMLFile(){
        NodeList elementNodes = null;
        try{
            Document document = dbBuilder.parse(new File(filepath));
            Element root = document.getDocumentElement();

            elementNodes = root.getChildNodes();
        }
        catch(SAXException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return elementNodes;
    }

    /**
     * Loads a list of elements from an XML file. <br>
     * <br>
     * The list can be empty at the beginning: take care that this function does not check for duplicates, 
     * and will thus append the elements extracted from the XML file at the end of the already existing list.
     * 
     * @param audioElements a list of elements to be loaded from the file
     */
    public void loadXML(List<T> audioElements){
        NodeList nodes = parseXMLFile();
        if(nodes == null) return;

        //going through the list of all nodes
        for(int i=0; i<nodes.getLength(); i++){
            if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element currentElement = (Element) nodes.item(i);

                //if currentElement is a node <xmlTypeIdentifier>
                if(currentElement.getNodeName().equals(xmlTypeIdentifier)){

                    audioElements.add(createListElement(currentElement));
                }
            }
        }
    }

    private T createListElement(Element currentNode){
        T elt = null;
        HashMap<String, List<String>> attributes = new HashMap<>();

        Node n = currentNode.getFirstChild();

        try{
            elt = klass.getDeclaredConstructor().newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        while(n != null){
            String key = n.getNodeName();
            String value = n.getTextContent();

            attributes.computeIfAbsent(key, k -> new ArrayList<String>()).add(value);

            n = n.getNextSibling();
        }

        elt.load(attributes);

        return elt;
    }

    /**
     * Saves the list of elements into the XML file specified at the creation of the AudioXML object. <br>
     * If the file does not exist prior to the call, it will be created. Beware that setting overwrite to false with a non-existing
     * file might raise exceptions 
     * @param elements a list of Elements to be saved
     * @param rootName the name of the root node. Will be ignored if overwrite is set to false
     * @param overwrite specify if the function should conserve previous information (if any) in the file
     */
    public void saveXML(List<T> elements, String rootName, boolean overwrite){

        Document document = null;
        Element root = null;
        if(overwrite){
            document = dbBuilder.newDocument();
            root = document.createElement(rootName);
            document.appendChild(root);
        }
        else{
            try{
                document = dbBuilder.parse(new File(filepath));
                root = document.getDocumentElement();
            }
            catch(SAXException e){
                e.printStackTrace();
            }
            catch(FileNotFoundException e){
                System.out.println("File not found, creating it");
                document = dbBuilder.newDocument();
                root = document.createElement(rootName);
                document.appendChild(root);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
        }
        

        //for each element in the global list
        for(AudioToXML audioElt : elements){
            Map<String, List<String>> attributes = audioElt.save();
            Set<String> keySet = attributes.keySet();

            Element element = document.createElement(xmlTypeIdentifier);
            //go through the map
            for(Iterator<String> it_key = keySet.iterator(); it_key.hasNext(); ){
                String key = it_key.next();
                
                //TODO: make a better way of listing values inside XML
                //create all elements in the XML
                for(String obj : attributes.get(key)){
                    Element attribute = document.createElement(key);
                    attribute.appendChild(document.createTextNode(obj));
                    element.appendChild(attribute);
                }

            }

            root.appendChild(element);

        }

        createXMLFile(document);

    }

    private void createXMLFile(Document document){
        try{
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, streamResult);
        }
        catch(TransformerException tfe){
            tfe.printStackTrace();
        }
        System.out.println("Created XML file :" +filepath);
    }

}
