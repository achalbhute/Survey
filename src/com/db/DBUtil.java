package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	 private static Connection connection = null;
	    public static Connection getConnection() throws Exception {
	        if (connection != null)
	        {
	            return connection;
	        }
	        else
	        {
	        Class.forName("com.mysql.jdbc.Driver");
	        connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/surveydb","root","");  
	        return connection;
	        }
	    }
}
