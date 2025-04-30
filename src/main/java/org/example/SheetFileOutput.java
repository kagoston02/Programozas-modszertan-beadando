package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Kiírja a végleges kottát egy XML file-ba.
 */
public class SheetFileOutput extends Sheet implements SheetOperations {

    public SheetFileOutput(String title, String author, String key, Integer bars, Integer segment) {
        super(title, author, key, bars, segment);
    }

    @Override
    public void display(ArrayList<ArrayList<Character>> sheet) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("SheetMusic");
            doc.appendChild(rootElement);

            // Kotta tulajdonságainak elemei
            Element properties = doc.createElement("Properties");
            rootElement.appendChild(properties);

            Element title = doc.createElement("Title");
            title.appendChild(doc.createTextNode(getTitle()));
            properties.appendChild(title);

            Element composer = doc.createElement("Composer");
            composer.appendChild(doc.createTextNode(getComposer()));
            properties.appendChild(composer);

            Element key = doc.createElement("Key");
            key.appendChild(doc.createTextNode(getKey()));
            properties.appendChild(key);

            Element bars = doc.createElement("Bars");
            bars.appendChild(doc.createTextNode(Integer.toString(getBars())));
            properties.appendChild(bars);

            Element segment = doc.createElement("Segment");
            segment.appendChild(doc.createTextNode(Integer.toString(getSegment())));
            properties.appendChild(segment);

            //kotta eleme
            Element sheetElement = doc.createElement("Sheet");
            rootElement.appendChild(sheetElement);

            for (ArrayList<Character> row : sheet) {
                Element rowElement = doc.createElement("Row");
                StringBuilder rowContent = new StringBuilder();
                for (Character ch : row) {
                    rowContent.append(ch);
                }
                rowElement.appendChild(doc.createTextNode(rowContent.toString()));
                sheetElement.appendChild(rowElement);
            }

            // XML-be írás
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("outputSheet.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
