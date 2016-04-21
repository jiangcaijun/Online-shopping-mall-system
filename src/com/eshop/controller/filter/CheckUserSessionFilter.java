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

/**
 * 检测网站后台管理员是否登录
 * @author tx40102
 *
 */
public class CheckUserSessionFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//如果获取session的某个属性为非空，则认为会话已失效，那让用户登录去
		HttpSession session = ((HttpServletRequest)request).getSession();
		HttpServletResponse response = (HttpServletResponse)resp;
		if(session.getAttribute("username")==null){//System.out.println("会话过期");
			response.sendRedirect("/login.jsp");
			return;
		}
		chain.doFilter(request, resp);//将请求继续转发下去，转给下一个filter
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}