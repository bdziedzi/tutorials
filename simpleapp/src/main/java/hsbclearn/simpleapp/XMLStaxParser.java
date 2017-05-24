package hsbclearn.simpleapp;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLStaxParser implements IXMLMessageParser {

	@Override
	public String saveAsXML(List<IIntegerWrapper> injIWlist) {

		String outputXMLString = null;
		 
		try 
		{
			StringWriter stringWriter = new StringWriter();

			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
   
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("IntegerWrapperElements");
   
			for (IIntegerWrapper element : injIWlist)
			{
	        	xMLStreamWriter.writeStartElement("IntegerWrapper");
	    		xMLStreamWriter.writeCharacters(""+element.GetValue());
			 	xMLStreamWriter.writeEndElement();
			}
			
		 	xMLStreamWriter.writeEndElement();
		 	xMLStreamWriter.writeEndDocument();

		 	xMLStreamWriter.flush();
		 	xMLStreamWriter.close();

		 	outputXMLString = stringWriter.getBuffer().toString();

		 	stringWriter.close();
		} 
		catch (XMLStreamException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{         
         e.printStackTrace();
		}
		
		return outputXMLString;
	}

	@Override
	public List<IIntegerWrapper> readXML(String inXML) {
		// TODO Auto-generated method stub
		
		List<IIntegerWrapper> retIWList = new ArrayList <IIntegerWrapper> ();
		
		boolean isvalue = false;
		
		try 
		{
	         XMLInputFactory factory = XMLInputFactory.newInstance();
	         XMLEventReader eventReader = factory.createXMLEventReader(new StringReader(inXML));

	            while(eventReader.hasNext())
	            {
	               XMLEvent event = eventReader.nextEvent();
	               switch(event.getEventType()){
	                  case XMLStreamConstants.START_ELEMENT:
	                     StartElement startElement = event.asStartElement();
	                     String qName = startElement.getName().getLocalPart();
	                     if (qName.equalsIgnoreCase("value")) 
	                     {
	                    	 isvalue = true;
	                     } 	                     				        
	                     break;
	                     
	                  case XMLStreamConstants.CHARACTERS:
	                	  
	                	  Characters characters = event.asCharacters();	                	 
	                	  
	                	  if(isvalue)
	                	  {
	                		  IntegerWrapper tmpIW = new IntegerWrapper(Integer.parseInt(characters.getData()));
	                		  
	                		  retIWList.add(tmpIW);  
	                		  
	                		  isvalue = false;	                		  
		                  }
	                	  break;
	                	  
	                  case  XMLStreamConstants.END_ELEMENT:
	                     
	                     break;
	                     
	               }		    
	            }
	         } 
		
		catch (XMLStreamException e) 
		{
	        e.printStackTrace();
	    }	   
	
		return retIWList;
	}

}
