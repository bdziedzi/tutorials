package hsbclearn.simpleapp.cdiex;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import hsbclearn.simpleapp.DataInput;
import hsbclearn.simpleapp.resources.Request;
import hsbclearn.simpleapp.resources.Response;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;

public class ResponseToRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Response
	private Queue responseQ;
	
	@Inject
	ConnectionFactory connFactory;
	
	@Inject
	DataInput dti;
	
	@Inject
	@ParserType(ParserName.XMLJaxbParser)
	@ParserProducer
	IXMLMessageParser msgparser;	
	
	private String responseLookup = null;
	
	public ResponseToRequest()
	{
		responseLookup = "jms/simpleAppDefaultQueue";
	}
	
	public void setResponseQueue(String inLookup)
	{
		responseLookup = inLookup;
	}
	
	private Queue getResponseQueue() 
	{		
		Queue responseQueue = null;
		 
		try {
			Context ctx = new InitialContext();			
			responseQueue = (Queue) ctx.lookup(this.responseLookup);
			System.out.println("found: "+ responseQueue.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseQueue;
	}
	
	public void executeTransmission()
	{
	
		// get from request queue
		// put into responselookup queue
		
		//setreplayto
		//setcorellationid
		//session scope bean
		
		Session session = null;
	    MessageProducer producer = null;
	    MessageConsumer consumer = null;		
	    Connection conn = null;	    
	    Queue responseQ2 = null;
	    TextMessage msg = null;
	  
		try {
			//System.out.println("checkpoint - 1 -");
			
			conn = connFactory.createConnection();	
			
			//System.out.println("checkpoint - 2 -");
			
			session = conn.createSession(true, Session.SESSION_TRANSACTED);
			
			//System.out.println("checkpoint - 3 -");
			
			consumer = session.createConsumer(responseQ);		    
			
			//System.out.println("checkpoint - 4 -");
			
			responseQ2 = getResponseQueue();
		    
			//System.out.println("checkpoint - 5 -");
			
			producer = session.createProducer(responseQ2);
		    
			//System.out.println("checkpoint - 6 -");
			
			conn.start();
		    
			//System.out.println("checkpoint - 7 -");
			
		    msg = (TextMessage) consumer.receive();
	        
		    //System.out.println("checkpoint - 8 -");
		    
	        producer.send(msg,
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
