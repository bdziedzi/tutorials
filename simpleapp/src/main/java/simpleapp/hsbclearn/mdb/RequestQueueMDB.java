package simpleapp.hsbclearn.mdb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(                                     // Message-driven bean (MDB)
		  activationConfig = { @ActivationConfigProperty(   // activation configuration (more)
		    propertyName = "destinationType", 
		    propertyValue = "javax.jms.Queue"
		    ),
		    @ActivationConfigProperty(
		    propertyName = "destination", 
		    propertyValue = "jms/simpleAppRequestQueue"         // destination's JNDI name
		  ) })
@TransactionManagement(TransactionManagementType.BEAN)
public class RequestQueueMDB implements MessageListener {

	@Inject
	ConnectionFactory connFactory;
	
  public void onMessage(Message message) {          // process message here
    TextMessage textMsg = (TextMessage) message;
    
    Session session = null;
    MessageProducer producer = null;
    MessageConsumer consumer = null;		
    Connection conn = null;	    
    Queue responseQ = null;
    TextMessage msg = null;
    
    try {
    	System.out.println("Got message: --------------------- " + textMsg.getText());
      
      	Context ctx = new InitialContext();			
	  	Queue responseQueue = (Queue) ctx.lookup("jms/simpleAppResponseQueue");
	  
	  	conn = connFactory.createConnection();
		
		session = conn.createSession(true, Session.SESSION_TRANSACTED);
				
		producer = session.createProducer(responseQueue);
		
		producer.send(textMsg,
                    Message.DEFAULT_DELIVERY_MODE,
                    Message.DEFAULT_PRIORITY,
                    Message.DEFAULT_TIME_TO_LIVE);
      	        
		session.commit();    
		
		conn.close();
    }
    catch (JMSException | NamingException e) {
      System.out.println("Error retrieving message content");
    }
  }
}