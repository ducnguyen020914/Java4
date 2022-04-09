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

import org.apache.commons.beanutils.BeanUtils;

import entity.User;

/**
 * Servlet Filter implementation class CreateUserFilter
 */
@WebFilter(
		urlPatterns = {"/QLUS/store","/QLUS/update"},
		filterName = "storeUser_filter"
		)
public class CreateUserFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public CreateUserFilter() {
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
		
		User u = new User();
		try {
			BeanUtils.populate(u, req.getParameterMap());
			if(u.getHoTen().equals("") || u.getEmail().equals("") || u.getSdt().equals("")|| u.getDiaChi().equals("")) {
				session.setAttribute("error", "Nhập đầy đủ thông tin");
				res.sendRedirect("/QLBH/QLUS/index");
				return;
			}
				chain.doFilter(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
