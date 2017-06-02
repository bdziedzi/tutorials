package hsbclearn.simpleapp.resources;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@ApplicationScoped
public class JMSResources {

	@Produces
	@Resource(lookup="jms/simpleAppConnectionFactory")
	private ConnectionFactory connFactory;
	
	@Produces
	@Resource(lookup="jms/SimpleAppDefaultQueue")
	private Queue defaultQueue;

}
