package com.richard.javaservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.richard.dao.User;
import com.richard.database.UserOperations;

public class LoginServlet extends HttpServlet{
	
	private UserOperations userOperations;
	private RequestDispatcher dispatcher;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String username = request.getParameter("login-username");
		String password = request.getParameter("login-password");
		
		userOperations = new UserOperations();
		
		List<User> userList = userOperations.getUserByUsernameAndPassword(username, password);
		User user = null;
		if(userList!=null && !userList.isEmpty()) {
			user = userList.get(0);
		}
		System.out.println("checkpoint 1"+user);
		if(user!= null) {
			System.out.println("login successfull for user "+username);
			
			if(user.getUserType().equals("Admin")) {
				System.out.println(user.getUserName());
				//redirect to admin dashboard
				dispatcher = request.getRequestDispatcher("/pages/home.html");
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				dispatcher.forward(request, response);
						
			}
			else {
				System.out.println("user doesnt exist");
				dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.include(request, response);
			}
			
		}
	}
}
