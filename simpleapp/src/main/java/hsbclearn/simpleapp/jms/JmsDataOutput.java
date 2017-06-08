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
import hsbclearn.simpleapp.resources.Request;
import hsbclearn.simpleapp.resources.Response;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;

@Dependent
public class JmsDataOutput implements IDataOutput 
{
	@Inject
	ConnectionFactory connFactory;
	
	@Inject @Request
	Queue reqqueue;
	
	@Inject @Response
	Queue resqueue;
	
	@Inject
	@ParserType(ParserName.XMLJaxbParser)
	@ParserProducer
	IXMLMessageParser msgparser;
	
	String msgId = null;
	
	@Override
	public String listout(List<IntegerWrapper> listToPrint) throws Exception {

		Session session = null;
	    MessageProducer producer = null;
	    Connection conn = null;
	    	    	
		try {
			conn = connFactory.createConnection();
						
		    //session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    
		    session = conn.createSession(true, Session.SESSION_TRANSACTED);
			
		    producer = session.createProducer(reqqueue);
		    
	        TextMessage message = session.createTextMessage();
			
	        message.setText(msgparser.saveAsXML(listToPrint));
	        
	    	// get from request queue
			// put into responselookup queue
			
			//setreplayto
			//setcorellationid
			//session scope bean	        
	        
	        message.setJMSReplyTo(resqueue);	        
	        
	        producer.send(message,
	                      Message.DEFAULT_DELIVERY_MODE,
	                      Message.DEFAULT_PRIORITY,
	                      Message.DEFAULT_TIME_TO_LIVE);
	        	        
	        session.commit();
	        
	        this.msgId = message.getJMSMessageID();
	        	       			
		    
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
	
	public String getMessageId()
	{
		return this.msgId;
	}

}
