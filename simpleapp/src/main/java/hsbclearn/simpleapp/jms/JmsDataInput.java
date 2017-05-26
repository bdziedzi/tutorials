package hsbclearn.simpleapp.jms;

import java.util.List;

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

public class JmsDataInput implements IDataInput {

	@Override
	public List<IntegerWrapper> GetData() {
		
		List<IntegerWrapper> output = null;
		
		TextMessage msg = null;
		JMSResources jmsr = new JMSResources();
		jmsr.init();
		
		ConnectionFactory connFactory;
		Queue msgqueue;
		
		connFactory = jmsr.getConnFactory();
		msgqueue = jmsr.getDefaultQueue();
		
		Session session = null;		
		
		Connection conn;
		
		XMLJaxbParser xmljaxb = new XMLJaxbParser();
		
		try {
			 
			conn = connFactory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageConsumer consumer = session.createConsumer(msgqueue);
			conn.start();
			
			//long now = System.currentTimeMillis();
			//do {
				// receive our message
				msg = (TextMessage) consumer.receive();
				System.out.println(msg.getText() + " timestamp=" + msg.getJMSTimestamp());
				
				output = xmljaxb.readXML(msg.getText());

			//} while (now + 1000 * 60 * 10 > System.currentTimeMillis());
			 
			// close everything
			session.close();
			conn.close();
			consumer.close();
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return msg.toString();
		return output;
	}



}
