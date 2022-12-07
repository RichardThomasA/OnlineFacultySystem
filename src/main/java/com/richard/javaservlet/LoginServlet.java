package com.richard.javaservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.richard.dao.User;
import com.richard.database.UserOperations;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			
			HttpSession session = request.getSession();
			session.setAttribute("usernameSession", user.getUserName());
			session.setAttribute("usertypeSession", user.getUserType());
			
			Cookie loginCookie = new Cookie("usernameCookie",username);
			loginCookie.setMaxAge(30*60);//cookie set for 30 minutes
			response.addCookie(loginCookie);
			
			if(user.getUserType().equals("Admin")) {
				System.out.println(user.getUserName());
				//redirect to admin dashboard
				//dispatcher = request.getRequestDispatcher("/pages/home.html");
				//dispatcher = request.getRequestDispatcher("/AdminHome");
				
				//dispatcher.forward(request, response);
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/home.html");
						
			}
			else if(user.getUserType().equals("Faculty")){
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/facultyHome.html");
			}
			else if(user.getUserType().equals("Student")){
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/studentHome.html");
			}
			else {
				System.out.println("user doesnt exist");
				dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.include(request, response);
			}
			
		}
		else {
			System.out.println("user doesnt exist");
			dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.include(request, response);
		}
	}
}
