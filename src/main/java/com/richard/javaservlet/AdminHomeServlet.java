package com.richard.javaservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.richard.dao.User;

public class AdminHomeServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("reached admin home");
		HttpSession session = request.getSession(false);
		if(session!=null) {
			System.out.println("active session");
			User user = (User)session.getAttribute("user");
		}
			
	}
}
