package hsbclearn.simpleapp.xmlparsers;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import hsbclearn.simpleapp.IntegerWrapper;

public class XMLJaxbParser implements IXMLMessageParser {

	private List <IntegerWrapper> finalList;

    
	@Override
	public String saveAsXML(List<IntegerWrapper> injIWlist) {
		// TODO Auto-generated method stub
		
		
	    
		return null;
	}

	@Override
	public List<IntegerWrapper> readXML(String inXML) {

		xmlMsg iwtmp = null;
		
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
