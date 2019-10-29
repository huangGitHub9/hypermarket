package com.haiwan.admin.filter;

import com.haiwan.admin.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminLoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		StringBuffer url = request.getRequestURL();
		String path = url.toString();
		if(path.endsWith("login.do")){
			chain.doFilter(request, res);
		}else{
			Admin admin = (Admin) request.getSession().getAttribute("sessionAdmin");
			if(admin==null){
				request.setAttribute("msg", "您还未登录！");
				request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, res);;
			}else{
				chain.doFilter(request, res);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
