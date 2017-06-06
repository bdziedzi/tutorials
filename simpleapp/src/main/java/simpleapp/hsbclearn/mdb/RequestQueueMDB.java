package simpleapp.hsbclearn.mdb;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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

  public void onMessage(Message message) {          // process message here
    TextMessage textMsg = (TextMessage) message;
    try {
      System.out.println("Got message: --------------------- " + textMsg.getText());
    }
    catch (JMSException e) {
      System.out.println("Error retrieving message content");
    }
  }
}