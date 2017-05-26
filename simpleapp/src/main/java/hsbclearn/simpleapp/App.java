package hsbclearn.simpleapp;

import hsbclearn.simpleapp.xmlparsers.XMLDomParser;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;
import hsbclearn.simpleapp.xmlparsers.XMLJaxpParser;
import hsbclearn.simpleapp.xmlparsers.XMLStaxParser;

//import hsbclearn.simpleapp.jms.JmsDataOutput;

public class App 
{ 
	public static void main(String[] args) 
	{
        System.out.println( "Hello World!" );
        
        //int [] tmpIntTab = {1,-300,8,7,6,4,-3,5,6,6,9,0,4};
        int [] tmpIntTab = {1,-3,0,2};
        DataInput dti = new DataInput(tmpIntTab);
        DataProcessor dtp = new DataProcessor();
        DataOutput dto = new DataOutput();
        
        String inputXML = "<?xml version=\"1.0\" ?><IntegerWrapperElements><IntegerWrapper><value>100</value></IntegerWrapper><IntegerWrapper><value>-30</value></IntegerWrapper><IntegerWrapper><value>99</value></IntegerWrapper><IntegerWrapper><value>68</value></IntegerWrapper></IntegerWrapperElements>";
        
        String inputXML2 = "<?xml version=\"1.0\" ?><IntegerWrapperElements><Lista><IntegerWrapper><value>1</value></IntegerWrapper><IntegerWrapper><value>2</value></IntegerWrapper><IntegerWrapper><value>3</value></IntegerWrapper><IntegerWrapper><value>4</value></IntegerWrapper></Lista></IntegerWrapperElements>";
        
        XMLDomParser xmldom = new XMLDomParser();
        
        XMLStaxParser xmlstax = new XMLStaxParser();

        XMLJaxpParser xmljaxp = new XMLJaxpParser();
        
        XMLJaxbParser xmljaxb = new XMLJaxbParser();
        
        //JmsDataOutput jmsdto = new JmsDataOutput();
        String output = "";
        try 
        {
        	output = dto.listout(dtp.Execute(dti.GetData()));	
			
        	System.out.println("<<< DOM: >>>");
			System.out.println(xmldom.saveAsXML(dti.GetData()));
			output = dto.listout(dtp.Execute(xmldom.readXML(inputXML)));	
			
			System.out.println("<<< STAX: >>>");
			System.out.println(xmlstax.saveAsXML(dti.GetData()));
			output = dto.listout(dtp.Execute(xmlstax.readXML(inputXML)));
			
			System.out.println("<<< JAXP: >>>");
			System.out.println(xmljaxp.saveAsXML(dti.GetData())); 	
			output = dto.listout(dtp.Execute(xmljaxp.readXML(inputXML)));
			
			System.out.println("<<< JAXB 111: >>>");
			System.out.println(xmljaxb.saveAsXML(dti.GetData()));	
			
			System.out.println("<<< JAXB 222: >>>");
			output = dto.listout(dtp.Execute(xmljaxb.readXML(inputXML2)));
			
		} 
        catch (Exception e) 
        {			
			e.printStackTrace();
		}
       
    }
}
