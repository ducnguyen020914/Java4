package adminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import DAO.userDAO;
import Utils.enCryptUtil;
import entity.User;

/**
 * Servlet implementation class QLUserServlet
 */
@WebServlet({"/QLUS/index","/QLUS/create","/QLUS/store","/QLUS/update","/QLUS/edit","/QLUS/delete"})
public class QLUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       userDAO dao;
    public QLUserServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.dao = new userDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("index")) {
			this.index(request, response);
		}else if(uri.contains("delete")) {
			this.delete(request, response);
		}else if(uri.contains("edit")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("store")) {
			this.store(request, response);
		}else if (uri.contains("update")) {
			this.update(request, response);
		}
	}
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		if(request.getParameter("name") != null) {
			name = request.getParameter("name");
		}
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
	     List<User> listus = this.dao.findUserbyName(name,page,5); 
	     int pagecount = (int) Math.ceil((double) this.dao.getcount(name)/5);
	     System.out.println(listus.size());
	     request.setAttribute("name", name);
	     
	     request.setAttribute("pagecount", pagecount);
	     request.setAttribute("users", listus);
		request.setAttribute("view", "/View/Amin/QLUser.jsp");
	      request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
		
	}

	protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     HttpSession session = request.getSession(); 
		try {
			User u = new User();
			BeanUtils.populate(u, request.getParameterMap());
			String password = enCryptUtil.encrypt(u.getPassword());
			u.setPassword(password);
			this.dao.insert(u);
			session.setAttribute("message", "Thêm mới thành công");
			response.sendRedirect("/QLBH/QLUS/index");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại");
			response.sendRedirect("/QLBH/QLUS/index");
		}
		
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession(); 
		try {
		User u = new User();
		BeanUtils.populate(u, request.getParameterMap());
		User passold = this.dao.find(u.getId());
		u.setPassword(passold.getPassword());
		this.dao.merge(u);
		session.setAttribute("message", "Cập nhật thành công");
		response.sendRedirect("/QLBH/QLUS/index");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		session.setAttribute("error", "Cập nhật thất bại");
		response.sendRedirect("/QLBH/QLUS/index");
	}
		
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(); 
		try {
		int id = Integer.parseInt( request.getParameter("id"));
		User  u = this.dao.find(id);
		this.dao.delete(u);
		session.setAttribute("message", "Xóa thành công");
		response.sendRedirect("/QLBH/QLUS/index");
	} catch (Exception e) {
		// TODO: handle exception
	   e.printStackTrace();
		session.setAttribute("error", "Xóa thất bại");
		response.sendRedirect("/QLBH/QLUS/index");
	}
		
	}

}
