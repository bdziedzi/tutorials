package hsbclearn.simpleapp.examples;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsbclearn.simpleapp.cdiex.XMLController;

/**
 * Servlet implementation class ExampleServletCdi
 */
@WebServlet("/ExampleServletCdi")
public class ExampleServletCdi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//@Inject
	//GUIContoller executor;
	
	@Inject 
	XMLController xmlctrl;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExampleServletCdi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//executor.execute();
		xmlctrl.execute();
		response.getWriter().append("Served at: GUIContoller!!").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
