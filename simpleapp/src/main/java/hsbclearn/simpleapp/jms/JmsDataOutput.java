package hsbclearn.simpleapp.jms;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import hsbclearn.simpleapp.IDataOutput;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.resources.JMSResources;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;

@Dependent
public class JmsDataOutput implements IDataOutput 
{
	@Inject
	ConnectionFactory connFactory;
	
	@Inject
	Queue msgqueue;
	
	@Override
	public String listout(List<IntegerWrapper> listToPrint) throws Exception {

		Session session = null;
	    MessageProducer producer = null;
	    Connection conn = null;
	    
	    XMLJaxbParser xmljaxb = new XMLJaxbParser();
	    
		try {
			conn = connFactory.createConnection();
			
		    session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
		    producer = session.createProducer(msgqueue);
		    
	        TextMessage message = session.createTextMessage();
			
	        message.setText(xmljaxb.saveAsXML(listToPrint));
	        
	        producer.send(message,
	                      Message.DEFAULT_DELIVERY_MODE,
	                      Message.DEFAULT_PRIORITY,
	                      Message.DEFAULT_TIME_TO_LIVE);
	        
	       			
		    
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
			conn.close();
			producer.close();
		}
		return null;

	}

}
