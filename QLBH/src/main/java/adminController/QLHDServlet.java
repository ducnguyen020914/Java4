package adminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.orderDAO;
import DAO.orderDetailDAO;
import entity.Hoadon;
import entity.Orderdetail;

/**
 * Servlet implementation class QLHDServlet
 */
@WebServlet({"/admin/QLHD/dahuy","/admin/QLHD/xacnhan","/admin/QLHD/danggiao","/admin/QLHD/dagiao","/admin/QLHD/update","/admin/QLHD/chitiet"})
public class QLHDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    orderDAO dao;
    orderDetailDAO coDAO;
    public QLHDServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.dao = new orderDAO();
        this.coDAO = new orderDetailDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("xacnhan")) {
			this.xacnhan(request,response);
		}else if(uri.contains("danggiao")) {
			this.danggiao(request, response);
		}
		else if(uri.contains("dagiao")) {
			this.dagiao(request, response);
		}
		else if(uri.contains("dahuy")) {
			this.dahuy(request, response);
		}
		else if(uri.contains("chitiet")) {
			this.chitiet(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String uri= request.getRequestURI();
		   if(uri.contains("update")) {
			   this.update(request,response);
		   }
	}
	protected void xacnhan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Hoadon> list =  this.dao.findHoaDonbystatus(0);
		request.setAttribute("list", list);
		request.setAttribute("view", "/View/Amin/QLDonHang.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}
	protected void danggiao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Hoadon> list =  this.dao.findHoaDonbystatus(1);
		request.setAttribute("list", list);
		request.setAttribute("view", "/View/Amin/QLDonHang.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}
	protected void dahuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Hoadon> list =  this.dao.findHoaDonbystatus(2);
		request.setAttribute("list", list);
		request.setAttribute("view", "/View/Amin/QLDonHang.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}
	protected void dagiao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Hoadon> list =  this.dao.findHoaDonbystatus(3);
		request.setAttribute("list", list);
		request.setAttribute("view", "/View/Amin/QLDonHang.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}
	protected void chitiet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Orderdetail> list =  this.coDAO.findHDCTbyHD(id);
		Hoadon h = this.dao.find(id);
		request.setAttribute("hoadon", h);
		request.setAttribute("list", list);
		System.out.println(list.get(0).getId() +" "+list.get(0).getProduct().getId());
		request.setAttribute("view", "/View/Amin/Detail.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		int id =Integer.parseInt( request.getParameter("id"));
		Hoadon h = this.dao.find(id);
		int status = Integer.parseInt(request.getParameter("status"));
		int redirect = h.getStatus();
		h.setStatus(status);
		this.dao.update(h);
		if(redirect == 0) {
			response.sendRedirect("/QLBH//admin/QLHD/xacnhan");
		}else if(redirect == 1) {
			response.sendRedirect("/QLBH//admin/QLHD/danggiao");
		}
		else if(redirect == 2) {
			response.sendRedirect("/QLBH//admin/QLHD/dahuy");
		}else if(redirect == 3) {
			response.sendRedirect("/QLBH//admin/QLHD/dagiao");
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	}
}
