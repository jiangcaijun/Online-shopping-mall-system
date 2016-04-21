package com.eshop.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.model.Impl.UserDAO;

/**
 * 检测普通用户是否登录
 * @author tx40102
 *
 */
public class CheckAdminSessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//如果获取session的某个属性为非空，则认为会话已失效，那让用户登录去
		HttpSession session = ((HttpServletRequest)request).getSession();
		HttpServletResponse response = (HttpServletResponse)resp;
		Object name = session.getAttribute("username");
		if(name==null){
			response.sendRedirect("/login.jsp");
			return;
		}
		UserDAO userDao = new UserDAO();
		boolean check = userDao.checkAdmin(String.valueOf(name));
		if(!check){
			response.sendRedirect("/homePage.jsp");
			return;
		}
		chain.doFilter(request, resp);//将请求继续转发下去，转给下一个filter
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
