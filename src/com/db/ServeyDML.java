package com.db;

import com.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;

public class ServeyDML {

	 private static Connection connection;
	 static {
	  try {
		connection = DBUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void addSurvey(SurveyBean survey) {
		  try {
		   PreparedStatement preparedStatement = connection
		     .prepareStatement("insert into survey(title, description,choice,result,total) values (?, ?, ?,?,0)");
		   preparedStatement.setString(1, survey.getTitle());
		   preparedStatement.setString(2, survey.getDescription());   
		   JSONObject ch = new JSONObject(survey.getChoices());
		   preparedStatement.setString(3, ch.toString());
		   preparedStatement.setString(4, ch.toString());
		   preparedStatement.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }
	 
	 public static void updateSurveyResult(String id,String res) {
		  try {
		   PreparedStatement preparedStatement = connection
		     .prepareStatement("update survey set result=?, total=? where id=?");
		   SurveyBean prevSurvey=SurveyDDL.getSurveyById(Integer.parseInt(id));
		   JSONObject rs = new JSONObject(prevSurvey.getResult());
		   rs.put(res, rs.getInt(res)+1);
		   preparedStatement.setString(1, rs.toString());
		   preparedStatement.setInt(2, 1+prevSurvey.getTotal());
		   preparedStatement.setString(3,id);
		   preparedStatement.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }

}
