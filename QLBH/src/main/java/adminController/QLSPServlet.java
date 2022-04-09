package adminController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.categoryDAO;
import DAO.productDAO;
import entity.Category;
import entity.Product;

@MultipartConfig
@WebServlet({ "/QLSP/index", "/QLSP/store", "/QLSP/update", "/QLSP/delete" })
public class QLSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	productDAO dao;
	categoryDAO cdao;
	String message = "";

	public QLSPServlet() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new productDAO();
		this.cdao = new categoryDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		}
		request.setAttribute("message", message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
			System.out.println(12);
		} else if (uri.contains("update")) {
			this.update(request, response);
		}
		request.setAttribute("message", message);
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = "";
		if (request.getParameter("name") != null) {
			name = request.getParameter("name");
		}
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Product> list = this.dao.findProductByname(name, page, 5);
		List<Category> clist = this.cdao.findAll();
		int total = (int) Math.ceil((double) this.dao.getcount(name) / 5);
		request.setAttribute("pagecount", total);
		request.setAttribute("items", list);
		request.setAttribute("name", name);
		request.setAttribute("categories", clist);
		request.setAttribute("view", "/View/Amin/QLSP.jsp");
		request.getRequestDispatcher("/View/Amin/index.jsp").forward(request, response);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Product p = new Product();
			BeanUtils.populate(p, request.getParameterMap());
			int categoryID = Integer.parseInt(request.getParameter("categoryID"));
			Category c = this.cdao.find(categoryID);
			p.setCategory(c);
			String realpart = request.getServletContext().getRealPath("/image");
			if (!Files.exists(Path.of(realpart))) {
				Files.createDirectories(Path.of(realpart));
			}
			
			Part part = request.getPart("image");
			String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
			part.write(realpart + "/" + filename);
			p.setImg(filename);
			this.dao.insert(p);
			session.setAttribute("message", "Thêm mới thành công");
			response.sendRedirect("/QLBH/QLSP/index");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới Thất bại");
			response.sendRedirect("/QLBH/QLSP/index");
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Product p = new Product();
			BeanUtils.populate(p, request.getParameterMap());
			int categoryID = Integer.parseInt(request.getParameter("categoryID"));
			Category c = this.cdao.find(categoryID);
			p.setCategory(c);
			String realpart = request.getServletContext().getRealPath("/image");
			Part part = request.getPart("img");
			String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
			System.out.println(part);
			if (!filename.equals("") && filename != null) {
				part.write(realpart + "/" + filename); 
				p.setImg(filename);
			} else {
				Product oldproduct = this.dao.find(p.getId());
				p.setImg(oldproduct.getImg());
			}

			System.out.println(p.getId() + "  " + p.getTen() + "  " + p.getImg());
			this.dao.update(p);
			session.setAttribute("message", "Cập nhật thành công");
			response.sendRedirect("/QLBH/QLSP/index");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Cập nhật thất bại");
			response.sendRedirect("/QLBH/QLSP/index");
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Product p = this.dao.find(id);
			this.dao.delete(p);
			session.setAttribute("message", "Xóa hành công");
			response.sendRedirect("/QLBH/QLSP/index");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Xóa thất bại");
			response.sendRedirect("/QLBH/QLSP/index");
		}
	}
}
