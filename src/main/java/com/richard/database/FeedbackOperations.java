package com.richard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				
				stmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
