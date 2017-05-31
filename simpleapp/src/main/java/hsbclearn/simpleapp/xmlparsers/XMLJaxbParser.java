package hsbclearn.simpleapp.xmlparsers;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;

import hsbclearn.simpleapp.IntegerWrapper;

public class XMLJaxbParser implements IXMLMessageParser {

	private List <IntegerWrapper> finalList;
    
	@Override
	public String saveAsXML(List<IntegerWrapper> injIWlist) {
	
		StringWriter sw = new StringWriter();
		xmlMsg msgtmp = new xmlMsg();
		
		System.out.println("XMLJaxbParser::saveAsXML()");
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(xmlMsg.class);
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			msgtmp.SetList(injIWlist);
			jaxbMarshaller.marshal(msgtmp, sw);			
			//jaxbMarshaller.marshal(msgtmp, System.out);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sw.toString();
	}

	@Override
	public List<IntegerWrapper> readXML(String inXML) {

		xmlMsg iwtmp = null;
		
		System.out.println("XMLJaxbParser::readXML()");
		
		try
		{
			JAXBContext jc = JAXBContext.newInstance(xmlMsg.class);
		    Unmarshaller unmarshaller = jc.createUnmarshaller();
		    iwtmp = (xmlMsg) unmarshaller.unmarshal(new StringReader(inXML));
		    Marshaller marshaller = jc.createMarshaller();
	
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
			marshaller.marshal(iwtmp, System.out);
		
		}
		catch (PropertyException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JAXBException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return iwtmp.GetList();
		//return  null;
	}

}
