package com.snack.filter;

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


public class loginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest req = (HttpServletRequest)request;
	        HttpServletResponse resp =(HttpServletResponse) response;
	        HttpSession session = req.getSession();
	           // 获得用户请求的URI
	        String path = req.getRequestURI();
	        // 从session取得已经登录验证的凭证 我这里的demo用的是password来作为登录凭证
	        String adminName = (String) session.getAttribute("adminName");
	        // login.jsp页面无需过滤(根据自己项目的要求来)
	        if(path.indexOf("adminGoMain") == -1) {//注意：登录页面千万不能过滤  不然过滤器就。。。。。自行调试不要偷懒！这样记忆深刻
	        	chain.doFilter(req, resp);
	            return;
	        } else if(path.indexOf("adminGoMain") > -1){//如果不是login.jsp进行过滤
	            if (adminName == null || "".equals(adminName)) {
	            	resp.sendRedirect("adminLogin");
	            } else {
	                // 已经登陆,继续此次请求
	            	chain.doFilter(req, resp);
	            }
	        }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
