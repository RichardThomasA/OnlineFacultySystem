package com.richard.javaservlet;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
			
			String facultyIdString = request.getParameter("feedback-faculty-select");
			System.out.println(facultyIdString);
			
			//int facultyId = Integer.parseInt(request.getParameter("feedback-faculty-select"));
			String date = request.getParameter("feedback-date");
			String question = request.getParameter("feedback-question");
			
			System.out.println(date);
			System.out.println(question);
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
				if(method.equals("getAllFaculty")) {
					//get all users
					System.out.println("getAllFaculty method reached");
					
					UserOperations userOperations = new UserOperations();
					Map<Integer,String> facultyMap = userOperations.getAllFaculty();
					
					Gson json = new Gson();
					String jsonString = json.toJson(facultyMap);
					
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
