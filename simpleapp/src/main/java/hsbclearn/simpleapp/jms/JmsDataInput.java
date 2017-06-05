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
		
		Connection conn = null;
		
		XMLJaxbParser xmljaxb = new XMLJaxbParser();
		
		System.out.println("checkpoint1");
		try {
			 
			conn = connFactory.createConnection();
			System.out.println("checkpoint2");
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//session = conn.createSession(true, Session.SESSION_TRANSACTED);
			System.out.println("checkpoint3");
			consumer = session.createConsumer(msgqueue);
			
			System.out.println("checkpoint4");
			conn.start();
			
			System.out.println("checkpoint5");
			
			
			msg = (TextMessage) consumer.receive();
			
			System.out.println("checkpoint6");
			
			System.out.println(msg.getText() + " timestamp=" + msg.getJMSTimestamp());
				
			output = xmljaxb.readXML(msg.getText());
			
			//session.commit();
			
			session.close();
			
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
