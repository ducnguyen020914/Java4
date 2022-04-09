package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		 urlPatterns = {"/users/formcart","/users/cart","/admin/*"},
		 filterName = "login_filter"
		)
public class LoginFilter extends HttpFilter implements Filter {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginFilter() {
        super();
        // TOteDO Auto-generated constructor stub
    }
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse res =(HttpServletResponse) response;
		HttpSession session  = req.getSession();
		User u = (User) session.getAttribute("taikhoan");
		if(u == null) {
			res.sendRedirect("/QLBH/login");
			return;
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
