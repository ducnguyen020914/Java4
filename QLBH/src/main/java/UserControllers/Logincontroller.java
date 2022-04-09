 package UserControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.userDAO;
import Utils.enCryptUtil;
import entity.User;

/**
 * Servlet implementation class Logincontroller
 */

@WebServlet({ "/login", "/checklogin", "/logout" })
public class Logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	userDAO dao;

	public Logincontroller() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new userDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("login")) {
			this.index(request, response);
		} else if (uri.contains("logout")) {
			this.logout(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("checklogin")) {
			this.checklogin(request, response);
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/View/Login/loginform.jsp").forward(request, response);
	}

	protected void checklogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email"); 
			String password = request.getParameter("password");
			User u = this.dao.checklogin(email);
		
			if (u != null) {
				boolean check = enCryptUtil.check(password, u.getPassword());
				if (check) {
					session.setAttribute("taikhoan", u);
					if (u.getRole()) {
						response.sendRedirect("/QLBH/admin/trangchu");
					} else {
						response.sendRedirect("/QLBH/users/index");
					}
				} else {
					session.setAttribute("message", "Password không chính xác");
					request.getRequestDispatcher("/View/Login/loginform.jsp").forward(request, response);
				}

			} else {
				session.setAttribute("message", "Email không tồn tại");
				request.getRequestDispatcher("/View/Login/loginform.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("message", "Email không tồn tại");
			request.getRequestDispatcher("/View/Login/loginform.jsp").forward(request, response);
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("taikhoan");
		response.sendRedirect("/QLBH/users/index");
	}

}
