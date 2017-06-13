package hsbclearn.simpleapp.jms;

import java.util.List;

import javax.ejb.EJB;
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
import hsbclearn.simpleapp.resources.Response;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;
import hsbclearn.simpleapp.xmlparsers.XMLJaxpParser;

@Dependent
public class JmsDataInput implements IDataInput {

	@Inject
	ConnectionFactory connFactory;
	
	@Inject @Response
	Queue resqueue;
	
	//@EJB
	@Inject
	JmsBuffer buffer;
	
	@Override
	public List<IntegerWrapper> GetData() {
		
		List<IntegerWrapper> output = null;		
		TextMessage msg = null;
		Session session = null;		
		MessageConsumer consumer = null;		
		Connection conn = null;
		
		XMLJaxbParser xmljaxb = new XMLJaxbParser();		
		
		try {
			System.out.println("JMSDataInput checkpoint (1)");
			
			conn = connFactory.createConnection();			
			
			System.out.println("JMSDataInput checkpoint (2)");
			
			session = conn.createSession(true, Session.SESSION_TRANSACTED);

			System.out.println("JMSDataInput checkpoint (3)");
			
			if (buffer.getCorrelation() != null)
			{
				consumer = session.createConsumer(resqueue, buffer.getCorrelation());
				System.out.println("JMSDataInput checkpoint (3aaa)");
			}
			else
			{
				consumer = session.createConsumer(resqueue);
				System.out.println("JMSDataInput checkpoint (3bbb)");
			}
			
			System.out.println("JMSDataInput checkpoint (4)");
			
			conn.start();

			System.out.println("JMSDataInput checkpoint (5)");
			
			msg = (TextMessage) consumer.receive();
			
			System.out.println("JMSDataInput checkpoint (6)");
			
			System.out.println(msg.getText() + " timestamp=" + msg.getJMSTimestamp());
			
			System.out.println("JMSDataInput checkpoint (7)");
			
			output = xmljaxb.readXML(msg.getText());
			
			session.commit();
			
			session.close();
			conn.close();			
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
				
		}
		
		//return msg.toString();
		return output;
	}



}
