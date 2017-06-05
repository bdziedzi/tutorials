package hsbclearn.simpleapp.xmlparsers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@ApplicationScoped
public class XMLParserFactory {

	@Inject 
	XMLDomParser xmldom;
	
	@Inject 
	XMLJaxpParser xmljaxp;
	
	@Inject 
	XMLJaxbParser xmljaxb;
	
	@Inject 
	XMLJaxbParser xmlstax;
	
	@Produces
	@ParserProducer
	public IXMLMessageParser createParser(/*@Any Instance<IXMLMessageParser> instance, */
										  InjectionPoint injectionPoint)
	{
		Annotated annotated = injectionPoint.getAnnotated();
		ParserType parserTypeAnnotation = annotated.getAnnotation(ParserType.class);
        Class<? extends IXMLMessageParser> tmpParserType = parserTypeAnnotation.value().getParserType();
        
        //return instance.select(tmpParserType).get();
        
        if (tmpParserType == XMLDomParser.class) {            
            return xmldom;
        } else if (tmpParserType == XMLJaxpParser.class) {           
            return xmljaxp;
        } else if (tmpParserType == XMLJaxbParser.class) {
            return xmljaxb;
        } else if (tmpParserType == XMLStaxParser.class) {
            return xmlstax;
        }
        else {
            return null;
        }


	}
}
