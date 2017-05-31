package hsbclearn.simpleapp.xmlparsers;

//TODO: remove file
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

//@ApplicationScoped
public class XMLDomParserFactory {
	
	//@Inject Instance<IXMLMessageParser> availableParsers;
	
	//@Produces @DomReady
	public XMLDomParser createDomParser()
	{
		XMLDomParser domparser = new XMLDomParser();
		return domparser;		
	}

}
