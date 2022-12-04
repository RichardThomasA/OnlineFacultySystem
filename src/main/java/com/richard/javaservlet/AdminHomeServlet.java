package com.richard.javaservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHomeServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("reached admin home");
	}
}
