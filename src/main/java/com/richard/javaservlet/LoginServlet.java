package com.richard.javaservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("login-username");
		String password = request.getParameter("login-password");
	}
}
