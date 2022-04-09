package UserControllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.categoryDAO;
import DAO.commentDAO;
import DAO.productDAO;
import entity.Category;
import entity.Comment;
import entity.Product;
import entity.User;

@MultipartConfig
@WebServlet({"/users/index","/users/productDetail","/users/categories","/users/comment"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    productDAO dao;
    categoryDAO cdao;
    commentDAO cmtdao;
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.dao = new productDAO();
        this.cdao = new categoryDAO();
        this.cmtdao = new commentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("index")) {
			this.index(request,response);
		}else if(uri.contains("productDetail")) {
			this.productDetail(request,response);
		}else if(uri.contains("categories")) {
			this.categories(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("comment")) {
			this.comment(request, response);
		}
		
	}
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> list = this.dao.getnewProduct();
		request.setAttribute("items", list);
		List<Category> categories= this.cdao.findAll();
		request.setAttribute("categories", categories);
		  request.setAttribute("view", "/View/User/Trangchu.jsp");
		request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);
	}
	protected void productDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories= this.cdao.findAll();
		request.setAttribute("categories", categories);
		int id = Integer.parseInt(request.getParameter("id"));
	    Product p = this.dao.find(id);
	  List<Product> list = this.dao.findSanPhamlienquan(p.getCategory(),p.getId());
	  List<Comment> listcmt = this.cmtdao.findcommentByproduct(id);
	  request.setAttribute("comments", listcmt);
	  request.setAttribute("splienquan", list);
	  request.setAttribute("item", p);
		request.setAttribute("view", "/View/User/product_detail.jsp");
		request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);
	}
	protected void  categories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories= this.cdao.findAll();
		request.setAttribute("categories", categories);
		int id = Integer.parseInt(request.getParameter("id"));
		Category c = this.cdao.find(id);
		List<Product> list = this.dao.findBycategori(id);
		System.out.println(list.size());
		request.setAttribute("products", list);
		request.setAttribute("c", c);
		request.setAttribute("view", "/View/User/SanPhamTheloai.jsp");
		request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);	
	}
	protected void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		String noidung = request.getParameter("noiDung");
		System.out.println(noidung);
	     int productId = Integer.parseInt(request.getParameter("productID"));
	     Product p = this.dao.find(productId);
	     HttpSession session = request.getSession();
	     User u = (User) session.getAttribute("taikhoan");
	     Comment cmt =  new Comment();
	     cmt.setDate(new Date());
	     cmt.setNoiDung(noidung);
	     cmt.setProduct(p);
	     cmt.setUser(u);
		
			this.cmtdao.create(cmt);
			response.sendRedirect("/QLBH/users/productDetail?id="+productId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
