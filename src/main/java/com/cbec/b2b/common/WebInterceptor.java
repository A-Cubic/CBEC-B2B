package com.cbec.b2b.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.cbec.b2b.service.IUserService;

public class WebInterceptor implements HandlerInterceptor{

	private static final Logger logger = LogManager.getLogger(WebInterceptor.class);
	
	@Autowired
    private RedisUtil redisUtil;
	
	@Autowired
    IUserService service;
	 
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
//		Map<String, String> map = new HashMap<String, String>();
//		if(response.getHeader("code") == null) {
//			response.setCharacterEncoding("UTF-8");
//	        response.setHeader("Content-type", "application/json;charset=UTF-8");
//	        response.setHeader("code", "0");
//	        response.setHeader("msg", "success");
//	        response.setStatus(200);
//		}
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
        logger.info(String.format("请求参数：url: %s, method: %s, uri: %s", url, method, uri));
        
        if("GET".equals(method)) {
        	return false;
        }
        
        if(uri.contains("validate")) {
        	return true;
        }
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        //1. token校验
        String userId = map.get("userid");
        String token = map.get("token");
        
        Boolean isUser = redisUtil.isExistKey(userId);
        if(!isUser || !token.equals(redisUtil.get(userId))) {
        	Util.responseResult(response,"1","无效的身份，请重新登录");
        	return false;
        }
        
        //2. 权限校验
        if(!service.isAuth(userId, uri)) {
        	Util.responseResult(response,"2","非法的访问请求，无此权限！");
        	return false;
        }
        
		return true;
	}
	
	
	

}
