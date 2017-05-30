package hsbclearn.simpleapp.xmlparsers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class XMLJaxpParserFactory {
	
	@Produces @JaxpReady
	public XMLJaxpParser createDomParser()
	{
		XMLJaxpParser jaxpparser = new XMLJaxpParser();
		return jaxpparser;		
	}

}
