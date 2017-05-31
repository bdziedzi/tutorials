package hsbclearn.simpleapp.cdi;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import hsbclearn.simpleapp.DataOutput;
import hsbclearn.simpleapp.DataProcessor;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.JaxpReady;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;

@Dependent
public class ParserBean02 implements IParserBean {

	@Inject //@JaxpReady
	@ParserType(ParserName.XMLJaxpParser)
	@ParserProducer
	IXMLMessageParser msgparser;
	
	@Override
	public void executeOperation(String xml) {
	
		List <IntegerWrapper> tmplist = msgparser.readXML(xml);
		
		DataOutput dto = new DataOutput();
		DataProcessor dtp = new DataProcessor();
		try {
			System.out.println("ParserBean02: " + dto.listout(dtp.Execute(tmplist)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
