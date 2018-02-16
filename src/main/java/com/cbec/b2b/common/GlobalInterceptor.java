package com.cbec.b2b.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class GlobalInterceptor implements HandlerInterceptor{

	private static final Logger logger = LogManager.getLogger(GlobalInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
		String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
		if(response.getStatus()==404){  
			logger.info(String.format("请求参数：url: %s, method: %s, uri: %s,不存在的地址,跳转至b2b.llwell.net", url, method, uri));
            modelAndView.setViewName("redirect:http://b2b.llwell.net");  
        }
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 return true;
	}

}
