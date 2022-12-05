package com.richard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
}
