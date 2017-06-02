package hsbclearn.simpleapp.cdiex;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import hsbclearn.simpleapp.DataInput;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.jms.JmsDataInput;
import hsbclearn.simpleapp.jms.JmsDataOutput;
import hsbclearn.simpleapp.jms.JmsDataProcessor;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;

@RequestScoped
public class XMLController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	JmsDataInput jmsdatain;
	
	@Inject
	JmsDataProcessor jmsdatapro;
	
	@Inject
	JmsDataOutput jmsdataout;
	
	@Inject
	DataInput dti;

	@Inject
	@ParserType(ParserName.XMLJaxbParser)
	@ParserProducer
	IXMLMessageParser msgparser;
	
	public void execute() {
		
		try {
			dti.InsertInput(new int[]{1,2,3,4,5,6,-1,-4,-10,56,101});
			
			jmsdataout.listout(jmsdatapro.Execute(dti.GetData()));
			
			List<IntegerWrapper> listFromQueue = jmsdatain.GetData();
			
			String tmpstr = msgparser.saveAsXML(listFromQueue);
			
			System.out.println(tmpstr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
