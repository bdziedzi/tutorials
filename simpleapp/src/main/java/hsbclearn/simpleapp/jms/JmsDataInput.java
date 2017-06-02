package hsbclearn.simpleapp.jms;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import hsbclearn.simpleapp.IDataInput;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.resources.JMSResources;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;
import hsbclearn.simpleapp.xmlparsers.XMLJaxpParser;

@Dependent
public class JmsDataInput implements IDataInput {

	@Inject
	ConnectionFactory connFactory;
	
	@Inject
	Queue msgqueue;
	
	@Override
	public List<IntegerWrapper> GetData() {
		
		List<IntegerWrapper> output = null;		
		TextMessage msg = null;
		Session session = null;		
		MessageConsumer consumer = null;
		
		Connection conn;
		
		XMLJaxbParser xmljaxb = new XMLJaxbParser();
		
		try {
			 
			conn = connFactory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);			
			consumer = session.createConsumer(msgqueue);
			conn.start();
			
			
			msg = (TextMessage) consumer.receive();
			System.out.println(msg.getText() + " timestamp=" + msg.getJMSTimestamp());
				
			output = xmljaxb.readXML(msg.getText());
			
			session.close();
			conn.close();
			consumer.close();	
			
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
				
		}
		
		//return msg.toString();
		return output;
	}



}
