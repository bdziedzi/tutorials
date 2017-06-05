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
	    System.out.println("1checkpoint");
	    
		try {
			conn = connFactory.createConnection();
			
			System.out.println("2checkpoint");
			
		    session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    
		    System.out.println("3checkpoint");
			//session = conn.createSession(true, Session.SESSION_TRANSACTED);
			
		    producer = session.createProducer(msgqueue);
		    
		    System.out.println("4checkpoint");
		    
	        TextMessage message = session.createTextMessage();
	        
	        System.out.println("5checkpoint");
			
	        message.setText(xmljaxb.saveAsXML(listToPrint));
	        
	        System.out.println("6checkpoint");
	        
	        producer.send(message,
	                      Message.DEFAULT_DELIVERY_MODE,
	                      Message.DEFAULT_PRIORITY,
	                      Message.DEFAULT_TIME_TO_LIVE);
	        
	        System.out.println("7checkpoint");
	        //session.commit();
	        
	       			
		    
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
