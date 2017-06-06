package hsbclearn.simpleapp.examples;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsbclearn.simpleapp.jms.JmsDataOutput;
import hsbclearn.simpleapp.resources.JMSResources;
import hsbclearn.simpleapp.resources.Request;
import hsbclearn.simpleapp.resources.Response;

/**
 * Servlet implementation class ExampleServletMdb
 */
@WebServlet("/ExampleServletMdb")
public class ExampleServletMdb extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Inject @Response
	private Queue responseQueue;
	
	@Inject @Request
	private Queue requestQueue;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExampleServletMdb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		jmsrsc.
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
