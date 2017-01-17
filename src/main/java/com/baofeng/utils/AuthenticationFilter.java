package com.baofeng.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能：系统认证过滤器
 * 
 * @author RENLIANGRONG
 * */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = { "/*" }, asyncSupported = true)
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		String root = httpRequest.getContextPath() + "/";
		String home = httpRequest.getContextPath() + "/home.do";
		String logout = httpRequest.getContextPath() + "/user/logout.do";
		String login = httpRequest.getContextPath() + "/login.do";
		String init = httpRequest.getContextPath() + "/menu/init.do";
		String userlogin = httpRequest.getContextPath() + "/user/login.do";
		String branch = httpRequest.getContextPath() + "/branchOff/readAllBranchs.do";
		String jump = httpRequest.getContextPath() + "/jump.do";
		String adLogin = httpRequest.getContextPath() + "/user/adLogin.do";
		String admins = httpRequest.getContextPath() + "/user/admins.do";
		String method = httpRequest.getMethod();
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute(Constants.CURRENT_USER) != null || uri.equals(home) || uri.equals(login) || uri.equals(init) || uri.equals(logout) || uri.endsWith(".js")
				|| uri.endsWith(".css") || uri.equals(userlogin) || uri.contains("images") || uri.equals(root) || uri.equals(branch) || uri.equals(jump) || uri.equals(adLogin)
				|| uri.equals(admins)) {
			chain.doFilter(request, response);
			return;
		} else {
			httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
		}
		if (session.getAttribute(Constants.CURRENT_USER) == null && uri.equals(login)) {
			httpRequest.getRequestDispatcher(login + "?backurl=" + uri).forward(httpRequest, httpResponse);
		}
		if ("GET".equals(method) && uri.equals(login)) {
			httpRequest.getRequestDispatcher(login).forward(httpRequest, httpResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
