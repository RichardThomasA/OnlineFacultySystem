package com.richard.javaservlet;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.richard.dao.User;
import com.richard.database.UserOperations;

public class FeedbackServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			int facultyId = Integer.parseInt(request.getParameter("faculty-id"));
			String date = request.getParameter("date");
			String question = request.getParameter("question");
			
			System.out.println(date);
			LocalDate localdate = LocalDate.parse(date);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYY-MM-dd");
			date = formatter.format(localdate);
			System.out.println(date);
			
		} catch(Exception e) {
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
					
					UserOperations userOperations = new UserOperations();
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
