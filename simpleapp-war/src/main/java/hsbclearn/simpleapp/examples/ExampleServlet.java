package hsbclearn.simpleapp.examples;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.jms.JmsDataOutput;
import hsbclearn.simpleapp.jms.JmsDataProcessor;
import hsbclearn.simpleapp.xmlparsers.XMLJaxbParser;

@WebServlet("/ExampleServlet")
public class ExampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ExampleServlet() {
        super();
    }

	@Inject
	JmsDataOutput jmsdo;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inputXML2 = "<?xml version=\"1.0\" ?><IntegerWrapperElements><Lista><IntegerWrapper><value>1</value></IntegerWrapper><IntegerWrapper><value>2</value></IntegerWrapper><IntegerWrapper><value>3</value></IntegerWrapper><IntegerWrapper><value>4</value></IntegerWrapper></Lista></IntegerWrapperElements>";
		//JmsDataOutput jmsdo = new JmsDataOutput();		
		List<IntegerWrapper> listToPrint = null;
		
		XMLJaxbParser xmljaxb = new XMLJaxbParser();
		JmsDataProcessor jmsdtp = new JmsDataProcessor();
		
		try {
			
			jmsdo.listout(jmsdtp.Execute(xmljaxb.readXML(inputXML2)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//jmsdo.listout(listToPrint);
		
		response.getWriter()
			.append("Served at: bum szakalaka (XX-111-YY)")
			.append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	
}
