package com.richard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.richard.dao.Feedback;

public class FeedbackOperations {

	private Connection conn = null;
	private PreparedStatement stmt =null;
	private ResultSet res =null;
	
	public FeedbackOperations() {
		conn = MySqlConnector.getConnection();
	}
	
	public void addFeedback(Feedback feedback) {
		
		if(conn!=null) {
			try {
				
				String sqlQuery = "insert into feedback_question "
						+ "(fq_uid,fq_date,fq_question) "
						+ "values(?,?,?)";
				stmt = conn.prepareStatement(sqlQuery);
				
				stmt.setInt(1, feedback.getFacultyId());
				stmt.setString(2, feedback.getDate());
				stmt.setString(3, feedback.getQuestion());
				
				int rowsAffected = stmt.executeUpdate();
				if(rowsAffected>0) {
					System.out.println("feedback entered successfully");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Feedback> getAllFeedbackWithAnswers() {
		
		List<Feedback> feedbackList = new ArrayList<>();
		//hola
		if(conn!=null) {
			try {
				
				String sqlQuery ="select "
						+ "    feedback_question.fq_id,"
						+ "    feedback_question.fq_uid,"
						+ "    faculty.username,"
						+ "    feedback_question.fq_date,"
						+ "    feedback_question.fq_question,"
						+ "    feedback_answers.fa_id,"
						+ "    feedback_answers.fa_uid,"
						+ "    student.username,"
						+ "    feedback_answers.fa_answer "
						+ "from feedback_question "
						+ "inner join user faculty "
						+ "on faculty.id=feedback_question.fq_uid "
						+ "left join feedback_answers "
						+ "on feedback_question.fq_id = feedback_answers.fq_id "
						+ "left join user student "
						+ "on feedback_answers.fa_uid=student.id ";
				
				//System.out.println(sqlQuery);
				
				stmt = conn.prepareStatement(sqlQuery);
				res = stmt.executeQuery();
				while(res.next()) {
					Feedback feedback = new Feedback();
					
					feedback.setFbId(res.getInt(1));
					feedback.setFacultyId(res.getInt(2));
					feedback.setFacultyName(res.getString(3));
					feedback.setDate(res.getString(4));
					feedback.setQuestion(res.getString(5));
					feedback.setAnswerId(res.getInt(6));
					feedback.setStudentId(res.getInt(7));
					feedback.setStudentName(res.getString(8));
					feedback.setAnswer(res.getString(9));
					
					feedbackList.add(feedback);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return feedbackList;
	}
}
