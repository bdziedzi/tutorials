package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class XMLDomParser implements IXMLMessageParser {

	@Override
	public String saveAsXML(List<IIntegerWrapper> injIWlist) {

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
			for (IIntegerWrapper element : injIWlist) 
			{
				Element iwElement = doc.createElement("IntegerWrapper");
		        rootElement.appendChild(iwElement);
		        Element index = doc.createElement("index");
		        iwElement.appendChild(index);
		        index.appendChild(doc.createTextNode(""+idx));
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
	public List<IIntegerWrapper> readXML(String inXML) {
	
	
		List<IIntegerWrapper> retIWList = new ArrayList <IIntegerWrapper> ();
		
		
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
