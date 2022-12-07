package com.richard.javaservlet;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.richard.dao.User;
import com.richard.database.UserOperations;

public class SignupServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserOperations userOperations;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String username = request.getParameter("signup-username");
			String password = request.getParameter("signup-password");
			int course_id = Integer.parseInt(request.getParameter("signup-course-select"));
			String userType = request.getParameter("signup-usertype-select");
			
			userOperations = new UserOperations();
			
			User user =new User();
			user.setUserName(username);
			user.setPassword(password);
			user.setUserType(userType);
			
			//System.out.println(username+","+password+","+course_id+","+userType);
			
			userOperations.addUser(user, course_id);
			
			HttpSession session = request.getSession();
			session.setAttribute("usernameSession", user.getUserName());
			session.setAttribute("usertypeSession", user.getUserType());
			
			if(userType.equals("Admin")) {
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/home.html");
			}else if(userType.equals("Faculty")) {
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/facultyHome.html");
			}else if(userType.equals("Student")) {
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/studentHome.html");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("signup.html");
				dispatcher.include(request, response);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("reached signup get");
			String method = request.getParameter("method");
			if(method!=null) {
				if(method.equals("getAllCourses")) {
					//get all courses
					System.out.println("getallcourse reacehd");
					
					userOperations = new UserOperations();
					Map<Integer,String> coursesMap = userOperations.getAllCourses();
					
					Gson json = new Gson();
					String jsonString = json.toJson(coursesMap);
					
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
