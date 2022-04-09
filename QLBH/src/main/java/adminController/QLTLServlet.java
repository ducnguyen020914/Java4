package adminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import DAO.categoryDAO;
import entity.Category;

/**
 * Servlet implementation class QLTLServlet
 */
@WebServlet({ "/QLTL/index", "/QLTL/store", "/QLTL/update", "/QLTL/delete"})
public class QLTLServlet extends HttpServlet {
    private  final Logger logger = Logger.getLogger(QLTLServlet.class);  

	private static final long serialVersionUID = 1L;

	categoryDAO dao;
	
	public QLTLServlet() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new categoryDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		}else if(uri.contains("delete")) {
			this.delete(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		}
	}
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name= "";
		if(request.getParameter("name") !=null) {
			name = request.getParameter("name");
		}
		List<Category> list = this.dao.getCategorybyName(name);
		request.setAttribute("categories", list);
		request.setAttribute("view", "/View/Amin/QLTL.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  BasicConfigurator.configure();  
		 HttpSession session = request.getSession();
		try {
			Category c = new Category();
			String ten = request.getParameter("ten");
			c.setTen(ten);
		     this.dao.insert(c);
		     session.setAttribute("message", "Thêm thành công");
		     logger.info("Thêm thể loại : ID = "+c.getId() +"   Tên : "+c.getTen());
		     response.sendRedirect("/QLBH/QLTL/index");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			  session.setAttribute("error", "Thêm thất bại");
			     response.sendRedirect("/QLBH/QLTL/index");
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 BasicConfigurator.configure();  
		 HttpSession session = request.getSession();
			try {
				Category c = new Category();
				String ten = request.getParameter("ten");
				int id =Integer.parseInt( request.getParameter("id"));
				c.setTen(ten);
				c.setId(id);
			     this.dao.update(c);
			     session.setAttribute("message", "Cập nhật thành công");  
			     response.sendRedirect("/QLBH/QLTL/index");
			     logger.info("Cập nhật thể loại : ID = "+c.getId() +"   Tên : "+c.getTen());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				  session.setAttribute("error", "Cập nhật thất bại");
				     response.sendRedirect("/QLBH/QLTL/index");
			}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 BasicConfigurator.configure();
		 HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Category c = this.dao.find(id);
			this.dao.delete(c);
			 session.setAttribute("message", "Xóa thành công");
		     response.sendRedirect("/QLBH/QLTL/index");
		     logger.info("Xóa thể loại : ID = "+c.getId() +"   Tên : "+c.getTen());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 session.setAttribute("error", "Xóa thất bại");
		     response.sendRedirect("/QLBH/QLTL/index");
		}
	}

}
