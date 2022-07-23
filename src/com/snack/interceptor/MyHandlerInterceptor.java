package com.snack.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.snack.model.Permission;


public class MyHandlerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		int k=0;
		int p=0;
		String uri=request.getRequestURI();
		if(uri.indexOf("login")!=-1){
			return true;
		}
		HttpSession session = request.getSession();
		List<Permission> list = (List<Permission>)session.getAttribute("allPer");
		List<Permission> list2 = (List<Permission>)session.getAttribute("userPer");
		for (int i = 0; i < list.size(); i++) {
			if(uri.equals((list.get(i).getUrl()))){
			for (int j = 0; j < list2.size(); j++) {
				k++;
				if(uri.contains(list2.get(j).getUrl())){
					return true;
				}
			}
			}
			p++;
		}
		if(p>0 && k==list2.size()){
			response.getWriter().write("Sorry");
			return false;
		}
		if(list.size()==p){
			return true;
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
