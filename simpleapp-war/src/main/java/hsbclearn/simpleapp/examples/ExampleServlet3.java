package hsbclearn.simpleapp.examples;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsbclearn.simpleapp.DataInput;
import hsbclearn.simpleapp.IntegerWrapper;
import hsbclearn.simpleapp.jms.JmsDataInput;
import hsbclearn.simpleapp.jms.JmsDataOutput;
import hsbclearn.simpleapp.jms.JmsDataProcessor;

/**
 * Servlet implementation class ExampleServlet3
 */
@WebServlet("/ExampleServlet3")
public class ExampleServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Inject
	JmsDataOutput jmsdo;
	
	@Inject
	JmsDataProcessor jmsdtp;	
	
	@Inject
	JmsDataInput jmsdi;
	
	@Inject
	DataInput dti;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExampleServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<IntegerWrapper> listFromResponseQueue = null;
		
		dti.InsertInput(new int[] {1,2,3,4,5,6});
		
		try {
			System.out.println(">>> przed processorem i wysłaniem do Request");
			//jmsdo.listout(jmsdtp.Execute(dti.GetData()),"GROUP1","marker");
			
			//System.out.println(">>> po wysłaniu do Request. msgid = " + jmsdo.getMessageId());		
			
			//jmsdo.listout(jmsdtp.Execute(dti.GetData()),"GROUP1","marker");
			
			//System.out.println(">>> po wysłaniu do Request. msgid = " + jmsdo.getMessageId());	
			
			jmsdo.listout(jmsdtp.Execute(dti.GetData()),"-1","test");
			
			System.out.println(">>> po wysłaniu do Request. msgid = " + jmsdo.getMessageId());		
			
			//jmsdi.setCorrelation(jmsdo.getMessageId());		
			
			//System.out.println(">>> po set corelationid");
			
			listFromResponseQueue = jmsdi.GetData();
			
			System.out.println(">>>po getdata");			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
