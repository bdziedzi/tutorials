package hsbclearn.simpleapp.jms;

import java.util.List;

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
import hsbclearn.simpleapp.xmlparsers.XMLJaxpParser;

public class JmsDataOutput implements IDataOutput 
{

	@Override
	public String listout(List<IntegerWrapper> listToPrint) throws Exception {

		ConnectionFactory connFactory;
		Queue msgqueue;
		JMSResources jmsr = new JMSResources();
		
		jmsr.init();		
		connFactory = jmsr.getConnFactory();
		msgqueue = jmsr.getDefaultQueue();
		
		Session session = null;
	    MessageProducer producer = null;
	    
	    XMLJaxpParser xmljaxp = new XMLJaxpParser();
	    
		try {
			Connection conn = connFactory.createConnection();
			
		    session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
		    producer = session.createProducer(msgqueue);
		    
	        TextMessage message = session.createTextMessage();
			
	        message.setText(xmljaxp.saveAsXML(listToPrint));
	        
	        producer.send(message,
	                      Message.DEFAULT_DELIVERY_MODE,
	                      Message.DEFAULT_PRIORITY,
	                      Message.DEFAULT_TIME_TO_LIVE);
	        
	        session.close();
			conn.close();
			producer.close();			
		    
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
