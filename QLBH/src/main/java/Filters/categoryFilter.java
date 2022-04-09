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



/**
 * Servlet Filter implementation class CreateUserFilter
 */
@WebFilter(
		urlPatterns = {"/QLTL/store","/QLTL/update"},
		filterName = "category_filter"
		)
public class categoryFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public categoryFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session  = req.getSession();
		String ten = req.getParameter("ten");
		    if(ten.equals("")) {
		    	session.setAttribute("error", "Nhập đầy đủ thông tin");
				res.sendRedirect("/QLBH/QLTL/index");
				return;
		    }
		    chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
