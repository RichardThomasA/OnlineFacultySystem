package com.richard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.richard.dao.User;

public class UserOperations {

	private Connection conn = null;
	private PreparedStatement stmt =null;
	private ResultSet res =null;
	
	
	public List<User> getUserByUsernameAndPassword(String username, String password){
		List<User> usersList = new ArrayList<User>();
		conn = MySqlConnector.getConnection();
		if(conn!=null) {
			try {
				String sqlQuery = "select "
						+ "	user.id,"
						+ " user.username,"
						+ " user.password,"
						+ " course.course_name, "
						+ " user.usertype "
						+ "from user "
						+ "inner join course "
						+ "on user.course_id = course.course_id "
						+ "where user.username = ? "
						+ "and user.password = ?";
//				String sqlQueryOld = "select * from user where username = ?"
//						+ " and password =?";
				stmt = conn.prepareStatement(sqlQuery);
				stmt.setString(1, username);
				stmt.setString(2, password);
				
				res = stmt.executeQuery();
				while(res.next()) {
					User user = new User();
					
					user.setId(res.getInt(1));
					user.setUserName(res.getString(2));
					user.setPassword(res.getString(3));
					user.setCourse(res.getString(4));
					user.setUserType(res.getString(5));
					
					usersList.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usersList;
	}
	
	public List<User> getAllUsers(){
		List<User> usersList = new ArrayList<User>();
		conn = MySqlConnector.getConnection();
		if(conn!=null) {
			try {
				String sqlQuery = "select "
						+ "	user.id,"
						+ " user.username,"
						+ " user.password,"
						+ " course.course_name, "
						+ " user.usertype "
						+ "from user "
						+ "inner join course "
						+ "on user.course_id = course.course_id ";
				stmt = conn.prepareStatement(sqlQuery);
				res = stmt.executeQuery();
				while(res.next()) {
					User user = new User();
					
					user.setId(res.getInt(1));
					user.setUserName(res.getString(2));
					user.setPassword(res.getString(3));
					user.setCourse(res.getString(4));
					user.setUserType(res.getString(5));
					
					usersList.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usersList;
	}
	
	public Map<Integer,String> getAllCourses(){
		
		Map<Integer,String> coursesMap = new HashMap<>();
		conn = MySqlConnector.getConnection();
		if(conn!=null) {
			try {
				
				String sqlQuery ="select * from course";
				stmt = conn.prepareStatement(sqlQuery);
				res = stmt.executeQuery();
				while(res.next()) {
					Integer courseId = res.getInt(1);
					String courseName =res.getString(2);
					coursesMap.put(courseId, courseName);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return coursesMap;
	}
	
	public Map<Integer,String> getAllFaculty(){
		
		Map<Integer,String> facultyMap = new HashMap<>();
		conn = MySqlConnector.getConnection();
		if(conn!=null) {
			try {
				String sqlQuery ="select * from user where usertype ='Faculty'";
				stmt = conn.prepareStatement(sqlQuery);
				res = stmt.executeQuery();
				while(res.next()) {
					Integer facultyId = res.getInt(1);
					String facultyName =res.getString(2);
					facultyMap.put(facultyId, facultyName);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return facultyMap;
	}
	
	public void addUser(User user, int courseId) {
		
		conn = MySqlConnector.getConnection();
		if(conn!=null) {
			try {
				String sqlQuery ="insert into user (username,password,course_id,usertype)"
						+ "values(?,?,?,?)";
				stmt = conn.prepareStatement(sqlQuery);
				stmt.setString(1, user.getUserName());
				stmt.setString(2, user.getPassword());
				stmt.setInt(3, courseId);
				stmt.setString(4, user.getUserType());
				
				int rowsAffected = stmt.executeUpdate();
				if(rowsAffected>0) {
					System.out.println("user entered successfully");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
