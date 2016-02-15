package edu.devernul.project.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class FilterStatusInterceptor extends HandlerInterceptorAdapter { // implements HandlerInterceptor{

	private static final Logger logger = LoggerFactory.getLogger(FilterStatusInterceptor.class);


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		logger.debug("Request param: {}",request.getParameterNames());

	}
}
