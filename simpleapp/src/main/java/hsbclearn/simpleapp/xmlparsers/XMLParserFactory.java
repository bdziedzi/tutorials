package hsbclearn.simpleapp.xmlparsers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class XMLParserFactory {

	@Produces
	@ParserProducer
	public IXMLMessageParser createParser(@Any Instance<IXMLMessageParser> instance, 
										  InjectionPoint injectionPoint)
	{
		Annotated annotated = injectionPoint.getAnnotated();
		ParserType parserTypeAnnotation = annotated.getAnnotation(ParserType.class);
        Class<? extends IXMLMessageParser> tmpParserType = parserTypeAnnotation.value().getParserType();
        
        //return instance.select(tmpParserType).get();
        
        if (tmpParserType == XMLDomParser.class) {            
            return new XMLDomParser();
        } else if (tmpParserType == XMLJaxpParser.class) {           
            return new XMLJaxpParser();
        } else if (tmpParserType == XMLJaxbParser.class) {
        	return new XMLJaxbParser();
        } else if (tmpParserType == XMLStaxParser.class) {
        	return new XMLStaxParser();
        }
        else{
        	return null;
        }
	}
}
