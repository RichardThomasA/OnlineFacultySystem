package com.richard.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnector {

	private static final String connectionString ="com.mysql.cj.jdbc.Driver";
	private static String username;
	private static String password;
	private static String url;
	
	private static Connection conn = null;
	
	private MySqlConnector() {}
	
	public static synchronized Connection getConnection(){
		
		if(conn!=null) {
			return conn;
		}
		else {
			
			try(InputStream ins =new FileInputStream("src/main/resources/config.properties")){
				
				Properties prop = new Properties();
				prop.load(ins);
				
				username = prop.getProperty("db.mysql.username");
				password = prop.getProperty("db.mysql.password");
				url = prop.getProperty("db.mysql.url");
				
				Class.forName(connectionString);
				conn = DriverManager.getConnection(url, username, password);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return conn;
		}
		
	}
}
