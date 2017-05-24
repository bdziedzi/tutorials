package hsbclearn.simpleapp.resources;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSResources {

	private ConnectionFactory connFactory;
	private Queue defaultQueue;

	public void init() {
		try {
			Context ctx = new InitialContext();
			connFactory = (ConnectionFactory) ctx.lookup("jms/simpleAppConnectionFactory");
			String admDestName = "jms/SimpleAppDefaultQueue";
			defaultQueue = (Queue) ctx.lookup(admDestName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConnectionFactory getConnFactory() {
		return connFactory;
	}

	public Queue getDefaultQueue() {
		return defaultQueue;
	}		

}
