package com.semi.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// doFilter를 기준으로 위쪽에 작성
		System.out.println("======================== 인코딩 필터 동작 start ======================");
		
		if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("post")) {
			System.out.println("============= post 방식이 요청됨 ============");
			request.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response);// 얘가 기준 위에는 서블릿 전, 밑에는 서블릿 실행 후
		
		System.out.println("======================== 서블릿 동작 하고 나서 실행 ======================");
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
