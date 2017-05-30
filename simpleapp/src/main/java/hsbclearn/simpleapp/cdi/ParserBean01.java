package hsbclearn.simpleapp.cdi;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.print.attribute.standard.DateTimeAtProcessing;

import hsbclearn.simpleapp.DataOutput;
import hsbclearn.simpleapp.DataProcessor;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.xmlparsers.DomReady;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;

@Dom
@Dependent
public class ParserBean01 implements IParserBean {

	@Inject @DomReady
	IXMLMessageParser msgparser;

	@Override
	public void executeOperation(String xml) {
		
		List <IntegerWrapper> tmplist = msgparser.readXML(xml);
		
		DataOutput dto = new DataOutput();
		DataProcessor dtp = new DataProcessor();
		try {
			System.out.println("ParserBean01: " + dto.listout(dtp.Execute(tmplist)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
