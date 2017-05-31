package hsbclearn.simpleapp.xmlparsers;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import hsbclearn.simpleapp.IntegerWrapper;

public class XMLDomParser implements IXMLMessageParser {

	@Override
	public String saveAsXML(List<IntegerWrapper> injIWlist) {

		String output = null;
		
		System.out.println("XMLDomParser::saveAsXML()");
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
	public List<IntegerWrapper> readXML(String inXML) {
	
	
		List<IntegerWrapper> retIWList = new ArrayList <IntegerWrapper> ();
		
		System.out.println("XMLDomParser::readXML()");
		
		try 
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource isxml = new InputSource(new StringReader(inXML));
			Document doc = dBuilder.parse(isxml);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("IntegerWrapper");
			
			 for (int temp = 0; temp < nList.getLength(); temp++) 
			 {
		            Node nNode = nList.item(temp);
		            
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
	            	{
		                Element eElement = (Element) nNode;
		                
		                IntegerWrapper tmpIW = new IntegerWrapper(Integer.parseInt( eElement.getElementsByTagName("value").item(0).getTextContent()));
		                retIWList.add(tmpIW);
	            	}
		            
			 }
		}
		catch (Exception e)
		{
	        e.printStackTrace();
	    }
	      
			return retIWList;
	}

}
