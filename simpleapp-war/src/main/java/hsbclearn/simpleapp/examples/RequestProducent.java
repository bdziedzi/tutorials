package hsbclearn.simpleapp.examples;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsbclearn.simpleapp.DataInput;
import hsbclearn.simpleapp.jms.RequestCreator;
import hsbclearn.simpleapp.xmlparsers.IXMLMessageParser;
import hsbclearn.simpleapp.xmlparsers.ParserName;
import hsbclearn.simpleapp.xmlparsers.ParserProducer;
import hsbclearn.simpleapp.xmlparsers.ParserType;

/**
 * Servlet implementation class RequestProducent
 */
@WebServlet("/RequestProducent")
public class RequestProducent extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Inject
	RequestCreator reqProducent;
	
	@Inject 
	DataInput dti;
	
	@Inject
	@ParserType(ParserName.XMLJaxbParser)
	@ParserProducer
	IXMLMessageParser msgparser;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RequestProducent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		dti.InsertInput(new int[] {11, 22, 33, 44, 55});
		
		reqProducent.makeRequest(msgparser.saveAsXML(dti.GetData()));
		
		response.getWriter().append("Made request.").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
