package hsbclearn.simpleapp.cdi;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GUIContoller implements Serializable {

	@Inject
	@Dom
	IParserBean domparser;
	
	@Inject
	IParserBean jaxpparser;
	
	public void execute() {
		
		String inputXML = "<?xml version=\"1.0\" ?><IntegerWrapperElements><IntegerWrapper><value>100</value></IntegerWrapper><IntegerWrapper><value>-30</value></IntegerWrapper><IntegerWrapper><value>99</value></IntegerWrapper><IntegerWrapper><value>68</value></IntegerWrapper></IntegerWrapperElements>";
		domparser.executeOperation(inputXML);
		System.out.println("po domparser.executeOperation");
		
		jaxpparser.executeOperation(inputXML);		
		System.out.println("po jaxpparser.executeOperation");
	
	}
}
