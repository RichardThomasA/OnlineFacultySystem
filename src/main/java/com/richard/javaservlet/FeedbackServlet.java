package com.richard.javaservlet;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richard.dao.Feedback;
import com.richard.database.FeedbackOperations;
import com.richard.database.UserOperations;

public class FeedbackServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FeedbackOperations fbOperations = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			if(fbOperations==null) {
				fbOperations = new FeedbackOperations();
			}
			//if the data is from form submission
			if(request.getContentType().equals("application/x-www-form-urlencoded")) {
				int facultyId = Integer.parseInt(request.getParameter("feedback-faculty-select"));
				String date = request.getParameter("feedback-date");
				String question = request.getParameter("feedback-question");
				
				
				Feedback feedback = new Feedback();
				feedback.setFacultyId(facultyId);
				feedback.setDate(date);
				feedback.setQuestion(question);
				
				//System.out.println(request.getContentType());
				fbOperations.addFeedback(feedback);
				
				response.sendRedirect("http://localhost:8080/OnlineFacultySystem/pages/home.html");
			}else{
				
				Gson json = new Gson();
				
				StringBuffer strBuff = new StringBuffer();
				String line = null;
				
				BufferedReader br = request.getReader();
				while((line=br.readLine())!=null) {
					strBuff.append(line);
				}
				
				if(strBuff.toString()!=null) {
					
					TypeToken<Map<String, String>> mapType = new TypeToken<Map<String, String>>(){};
					Map<String,String> jsonMap = json.fromJson(strBuff.toString(), mapType);
					
					System.out.println("json map created");
					
					String methodName = jsonMap.get("method");
					System.out.println(methodName);
					
					if(methodName!=null) {
						if(methodName.equals("getAllFeedbackWithAnswers")) {
							List<Feedback> feedbackList =fbOperations.getAllFeedbackWithAnswers();
							
							String jsonString = json.toJson(feedbackList);
							
							PrintWriter out = response.getWriter();
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							out.print(jsonString);
							out.flush();
							out.close();
						}
					}
				}
			}
			
			
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
