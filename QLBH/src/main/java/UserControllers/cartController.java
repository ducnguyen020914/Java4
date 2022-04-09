package UserControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.orderDAO;
import DAO.orderDetailDAO;
import DAO.productDAO;
import entity.Hoadon;
import entity.ListCart;
import entity.Orderdetail;
import entity.Product;
import entity.User;
import entity.cart;

/**
 * Servlet implementation class cartController
 */
@MultipartConfig
@WebServlet({"/users/cart","/users/formcart","/users/xoaitem","/users/thanhtoan"})
public class cartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	productDAO dao;
	orderDAO orderDAO;
	orderDetailDAO odDAO;
    
	public cartController() {
		super();
		// TODO Auto-generated constructor stub
		this.dao = new productDAO();
		this.orderDAO = new orderDAO();
		this.odDAO = new orderDetailDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 String uri = request.getRequestURI();
		 if(uri.contains("xoaitem")) {
			 HttpSession session = request.getSession();
				ListCart listcart = (ListCart) session.getAttribute("cart");
				int id = Integer.parseInt(request.getParameter("id"));
				List<cart> list = listcart.getOrder();
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getProduct().getId() ==id) {
						list.remove(i);
					}
				}
				listcart.setOrder(list);
				session.setAttribute("cart", listcart);
				response.sendRedirect("/QLBH/users/formcart");
		 }else if(uri.contains("formcart")) {
			 this.formcart(request,response);
		 }
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("thanhtoan")) {
			this.thanhtoan(request,response);
		}else if(uri.contains("cart")) {
			this.create(request, response);
		}
	}
	protected void formcart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.setAttribute("view", "/View/User/orderDetail.jsp");
	request.getRequestDispatcher("/View/User/Home.jsp").forward(request, response);
	}
	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("taikhoan");
			int id;
			int quantity = 1;
				id = Integer.parseInt(request.getParameter("id"));
				if ( request.getParameter("quantity") != null) {
					quantity = Integer.parseInt(request.getParameter("quantity"));
				}
				Product p = this.dao.find(id);

				if (session.getAttribute("cart") == null) {
					ListCart listcarr = new ListCart();
					List<cart> list = new ArrayList<cart>();
					cart c = new cart();
					c.setId(u.getId());
					c.setPrice(p.getDonGia());
					c.setProduct(p);
					c.setQuantity(quantity);
					list.add(c);
					listcarr.setOrder(list);
					session.setAttribute("cart", listcarr);
				} else {
					ListCart listcart = (ListCart) session.getAttribute("cart");
					List<cart> list = listcart.getOrder();
					boolean check = false;
					for (cart cart : list) {
						if (cart.getProduct().getId() == p.getId()) {
							check = true;
							cart.setQuantity(cart.getQuantity() + quantity);
						}
					}
					if (check == false) {
						cart cart = new cart();
						cart.setId(u.getId());
						cart.setPrice(p.getDonGia());
						cart.setProduct(p);
						cart.setQuantity(quantity);
						list.add(cart);
						listcart.setOrder(list);

					}
					session.setAttribute("cart", listcart);
				}
			int total = 0;
			if(session.getAttribute("cart") != null) {
				ListCart listcart = (ListCart) session.getAttribute("cart");
				for (cart cart : listcart.getOrder()) {
					if(u.getId() == cart.getId()) {
						total += (cart.getPrice() * cart.getQuantity());
					}
				}
			}
			System.out.println(total);
			session.setAttribute("price", total);
			
			response.sendRedirect("/QLBH/users/formcart");
		
		
	}
	protected void thanhtoan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ListCart listcart = (ListCart) session.getAttribute("cart");
		if(listcart == null) {
			session.setAttribute("message", "Không có sản phẩm nào trong giỏ hàng");
			response.sendRedirect("/QLBH/users/formcart");
		}else {
		try {
			Hoadon o = new Hoadon();
			User u = (User) session.getAttribute("taikhoan");
			o.setUser(u);
			o.setMoTa("NORMail");
			o.setStatus(0);
			o.setThanhToan(0);
			o.setTotal((int)session.getAttribute("price"));
			this.orderDAO.insert(o);
			List<cart> list = listcart.getOrder();
			System.out.println(o.getId());
			for (cart cart : list) {
				Orderdetail od = new Orderdetail();
				od.setHoadon(o);
				od.setPrice(cart.getProduct().getDonGia());
				od.setProduct(cart.getProduct());
				od.setQuantity(cart.getQuantity());
				this.odDAO.insert(od);
			}
			session.setAttribute("message", "Thanh toán thành công");
			session.removeAttribute("cart");
			session.removeAttribute("price");
			response.sendRedirect("/QLBH/users/formcart");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		}
		
	}
}
