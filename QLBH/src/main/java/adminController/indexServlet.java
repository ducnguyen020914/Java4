package adminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/admin/trangchu","/admin/QLNV","/admin/QLSP","/admin/QLDH"})
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String uri = request.getRequestURI();
	if(uri.contains("trangchu")) {
		request.setAttribute("view", "/View/Amin/trangchu.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}else if(uri.contains("QLNV")) {
		response.sendRedirect("/QLBH/QLUS/index");
	}else if(uri.contains("QLSP")) {
		response.sendRedirect("/QLBH/QLSP/index");
	}else if(uri.contains("QLTL")) {
		response.sendRedirect("/QLBH/QLTL/index");
	}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
