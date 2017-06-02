package hsbclearn.simpleapp.cdi;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.print.attribute.standard.DateTimeAtProcessing;

import hsbclearn.simpleapp.DataInput;
import hsbclearn.simpleapp.DataOutput;
import hsbclearn.simpleapp.DataProcessor;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;

@Dom
@Dependent
public class ParserBean01 implements IParserBean {

	@Inject
	@ParserType(ParserName.XMLDomParser)
	@ParserProducer
	IXMLMessageParser msgparser;

	@Override
	public void executeOperation(String xml) {		
		
		List <IntegerWrapper> tmplist = msgparser.readXML(xml);		
		
		DataInput dti = new DataInput(tmplist);
		DataOutput dto = new DataOutput();
		DataProcessor dtp = new DataProcessor();		
		
		try {
			System.out.println("ParserBean01: " + dto.listout(dtp.Execute(tmplist)));
			
			System.out.println(msgparser.saveAsXML(dti.GetData()));			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
