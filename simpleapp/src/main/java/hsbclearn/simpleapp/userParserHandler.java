package hsbclearn.simpleapp;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class userParserHandler extends DefaultHandler  {

	private List<IIntegerWrapper> iwList = new ArrayList <IIntegerWrapper> ();
	
	//As we read any XML element we will push that in this stack
	//private Stack elementStack = new Stack();

	//As we complete one user block in XML, we will push the User instance in userList
	//private Stack objectStack = new Stack();
	private boolean isvalue = false;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
	    //Push it in element stack
	        //this.elementStack.push(qName);
	 
	        //If this is start of 'user' element then prepare a new User instance and push it in object stack
	    if ("value".equals(qName))
	    {
	    	isvalue = true;
	    }
	}
	 
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
	    //Remove last added  element
	        //this.elementStack.pop();	 
	      
	    if ("value".equals(qName))
	    {
	    	isvalue = false;
	    }
	}
	 

	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String value = new String(ch, start, length).trim();
		 
		if (value.length() == 0)
		{
			return; // ignore white space
		}
		 
		        
		if (isvalue)
		{
			IIntegerWrapper iwtmp = new IntegerWrapper(Integer.parseInt(value));
			
			iwList.add(iwtmp);

		}
	}
	
	public List<IIntegerWrapper> GetList()
	{
		return iwList;
	}
}
