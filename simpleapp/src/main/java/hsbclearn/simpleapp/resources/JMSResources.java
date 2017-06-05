package hsbclearn.simpleapp.resources;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;

@ApplicationScoped
public class JMSResources {

	@Produces
	@Resource(lookup="jms/simpleAppConnectionFactory")
	private ConnectionFactory connFactory;
	
	@Produces
	@Resource(lookup="jms/SimpleAppDefaultQueue")
	private Queue defaultQueue;
	/*
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
	*/

}
