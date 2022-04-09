package UserControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.orderDAO;
import DAO.orderDetailDAO;
import entity.Hoadon;
import entity.Orderdetail;
import entity.User;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet({"/users/history","/users/chitiet","/users/huy"})
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	orderDAO hdDAO;
	orderDetailDAO hdctDAO;
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.hdDAO = new orderDAO();
        this.hdctDAO = new orderDetailDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("history")) {
			this.history(request,response);
		}else if(uri.contains("chitiet")) {
			this.chitiet(request, response);
		}else if(uri.contains("huy")) {
			this.huy(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       User u = (User) session.getAttribute("taikhoan");
		List<Hoadon> list  =this.hdDAO.findHoaDonByUser(u.getId());
		System.out.println(list.size());
		request.setAttribute("list", list);
		request.setAttribute("view", "/View/User/history.jsp");
		request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);
	}
	protected void chitiet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      int id=  Integer.parseInt(request.getParameter("id"));
			List<Orderdetail> list  =this.hdctDAO.findHDCTbyHD(id);
			request.setAttribute("list", list);
			request.setAttribute("view", "/View/User/chitiet.jsp");
			request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);
		}
	protected void huy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      try {
	    	    int id=  Integer.parseInt(request.getParameter("id"));
	    		Hoadon h = this.hdDAO.find(id);
	    		h.setStatus(2);
	    		this.hdDAO.update(h);
	    		response.sendRedirect("/QLBH/users/history");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		}

}
