package com.richard.javaservlet;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
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
			String course = request.getParameter("signup-course-select");
			String userType = request.getParameter("signup-usertype-select");
			
			userOperations = new UserOperations();
			
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
