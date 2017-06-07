package hsbclearn.simpleapp.jms;

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

import hsbclearn.simpleapp.resources.Request;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;

@Dependent
public class RequestCreator {
	
	@Inject
	ConnectionFactory connFactory;
	
	@Inject @Request
	Queue msgqueue;
	
	public void makeRequest(String msgtext)
	{
		Session session = null;
	    MessageProducer producer = null;
	    Connection conn = null;
	    
		try {
			conn = connFactory.createConnection();
		    
		    session = conn.createSession(true, Session.SESSION_TRANSACTED);
			
		    producer = session.createProducer(msgqueue);
		    
	        TextMessage message = session.createTextMessage();
			
	        message.setText(msgtext);
	        
	        producer.send(message,
	                      Message.DEFAULT_DELIVERY_MODE,
	                      Message.DEFAULT_PRIORITY,
	                      Message.DEFAULT_TIME_TO_LIVE);
	        	        
	        session.commit();
	        
			conn.close();
		    
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
	}

}
