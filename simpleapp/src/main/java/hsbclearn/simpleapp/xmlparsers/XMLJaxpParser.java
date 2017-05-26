package hsbclearn.simpleapp.xmlparsers;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import hsbclearn.simpleapp.IntegerWrapper;

public class XMLJaxpParser implements IXMLMessageParser {

	@Override
	public String saveAsXML(List<IntegerWrapper> injIWlist) {

		String output = null;
		try 
		{
			DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("IntegerWrapperElements");
			doc.appendChild(rootElement);
			
			int idx = 0;
			for (IntegerWrapper element : injIWlist) 
			{
				Element iwElement = doc.createElement("IntegerWrapper");
		        rootElement.appendChild(iwElement);
		        //Element index = doc.createElement("index");
		        //iwElement.appendChild(index);
		        //index.appendChild(doc.createTextNode(""+idx));
		        Element value = doc.createElement("value");
		        iwElement.appendChild(value);
		        value.appendChild(doc.createTextNode(""+element.GetValue()));
			}
		
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString().replaceAll("\n|\r", "");
			
		} 
		catch (Exception e)
		{
	        e.printStackTrace();
	    }
		         
		return output;		

	}

	@Override
	public List<IntegerWrapper> readXML(String inXML) 
	{		
		 //Create a empty link of users initially
        List<IntegerWrapper> iwList = new ArrayList<IntegerWrapper>();
        
        try
        {
            //Create default handler instance
            userParserHandler handler = new userParserHandler();
 
            //Create parser from factory
            XMLReader parser = XMLReaderFactory.createXMLReader();
 
            //Register handler with parser
            parser.setContentHandler(handler);
 
            //Create an input source from the XML input stream
            
            InputSource sourcexml = new InputSource(new StringReader(inXML));            
 
            //parse the document
            parser.parse(sourcexml);
 
            //populate the parsed users list in above created empty list; You can return from here also.
            iwList = handler.GetList(); 
        } 
        catch (SAXException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return iwList;		
		
	}
	    

}
