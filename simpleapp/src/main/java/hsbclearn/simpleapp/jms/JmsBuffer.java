package hsbclearn.simpleapp.jms;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;

@Stateless
//@SessionScoped
public class JmsBuffer {
	
	private String correlation = null;
	
	public String getCorrelation()
	{		
		System.out.println("Got correlation: " + correlation);
		
		return "JMSCorrelationID='" + this.correlation + "'";
	}
	
	public void setCorrelation (String newCorellation)
	{
		System.out.println("Set up correlation as: " + newCorellation);
		
		this.correlation = newCorellation;
	}

}
