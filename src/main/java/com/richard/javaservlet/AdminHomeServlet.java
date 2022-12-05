package com.richard.javaservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.richard.dao.User;
import com.richard.database.UserOperations;

public class AdminHomeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserOperations userOperations = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		User user = null;
		RequestDispatcher dispatcher = null;
		System.out.println("reached admin home");
		HttpSession session = request.getSession(false);
		try {
			if(session!=null) {
				System.out.println("active session");
				user = (User)session.getAttribute("user");
				dispatcher = request.getRequestDispatcher("/pages/home.html?user="+user.getUserName());
			} else {
				dispatcher = request.getRequestDispatcher("/pages/home.html");
			}
			
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("get method reached");
		try {
			String method = request.getParameter("method");
			if(method!=null) {
				if(method.equals("getAllUsers")) {
					//get all users
					System.out.println("getAllUSers mehod reached");
					
					userOperations = new UserOperations();
					List<User> usersList = userOperations.getAllUsers();
					
					Gson json = new Gson();
					String jsonString = json.toJson(usersList);
					
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(jsonString);
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
