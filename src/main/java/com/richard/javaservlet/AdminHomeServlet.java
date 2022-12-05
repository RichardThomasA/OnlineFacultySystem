package com.richard.javaservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.richard.dao.User;

public class AdminHomeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("reached admin home");
		HttpSession session = request.getSession(false);
		if(session!=null) {
			System.out.println("active session");
			User user = (User)session.getAttribute("user");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.html");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
