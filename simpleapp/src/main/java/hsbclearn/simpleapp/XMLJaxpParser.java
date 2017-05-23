package hsbclearn.simpleapp;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class XMLJaxpParser implements IXMLMessageParser {

	@Override
	public String saveAsXML(List<IIntegerWrapper> injIWlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IIntegerWrapper> readXML(String inXML) 
	{
		
		 //Create a empty link of users initially
        List<IIntegerWrapper> iwList = new ArrayList<IIntegerWrapper>();
        
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
 
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
        }
        return iwList;
		
		/*
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser saxParser;
		
		userParserHandler
		try 
		{
			saxParser = factory.newSAXParser();
			saxParser.parse(inXML, this);			
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
		*/
	}
	
	 //This is the list which shall be populated while parsing the XML.
    
 
  
	 
	    

}
