package com.db;

import com.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class SurveyDDL {

	 private static Connection connection;
	 static {
	  try {
		connection = DBUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static List<SurveyBean> getAllSurveyBeans() {
		  List<SurveyBean> SurveyBeans = new ArrayList<SurveyBean>();
		  try {
			   Statement statement = connection.createStatement();
			   ResultSet rs = statement.executeQuery("select * from survey");
			   while (rs.next()) 
			   {
			    SurveyBean surveyBean = new SurveyBean();
			    surveyBean.setId(rs.getInt("ID"));
			    surveyBean.setTitle(rs.getString("TITLE"));
			    surveyBean.setDescription(rs.getString("DESCRIPTION"));
			    JSONObject ch = new JSONObject(rs.getString("CHOICE"));
			    Map<String, Integer> m = new HashMap<>();
			    for(String k: ch.keySet()){
			    	m.put(k, 0);
			    }
			    surveyBean.setChoices(m);
			    SurveyBeans.add(surveyBean);
			   }
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }
		  return SurveyBeans;
		 }
	 
	 public static SurveyBean getSurveyById(int id) {
		 SurveyBean SurveyBean = new SurveyBean();
		 try {
			   PreparedStatement preparedStatement = connection.
			     prepareStatement("select id, title, description, choice, result, total from survey where id=?");
			   preparedStatement.setInt(1, id);
			   ResultSet rs = preparedStatement.executeQuery();
			   if (rs.next()) {
				   
				    SurveyBean.setId(rs.getInt("ID"));
				    SurveyBean.setTitle(rs.getString("TITLE"));
				    SurveyBean.setDescription(rs.getString("DESCRIPTION"));
				    
				    JSONObject ch = new JSONObject(rs.getString("CHOICE"));
				    Map<String, Integer> m = new HashMap<>();
				    for(String k: ch.keySet()){
				    	m.put(k, 0);
				    }
				    
				    JSONObject ch1 = new JSONObject(rs.getString("RESULT"));
				    Map<String, Integer> m1 = new HashMap<>();
				    for(String k: ch1.keySet()){
				    	m1.put(k, ch1.getInt(k));
				    }
				    
				    SurveyBean.setChoices(m);
				    SurveyBean.setResult(m1);
				    SurveyBean.setTotal(rs.getInt("TOTAL"));
			   }
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }
		  return SurveyBean;
		 }
	 
	 public static List<SurveyBean> getSurveyResult() {
		  List<SurveyBean> SurveyBeans = new ArrayList<SurveyBean>();
		  try {
			   Statement statement = connection.createStatement();
			   ResultSet rs = statement.executeQuery("select * from survey");
			   while (rs.next()) {
			    SurveyBean SurveyBean = new SurveyBean();
			    SurveyBean.setId(rs.getInt("ID"));
			    SurveyBean.setTitle(rs.getString("TITLE"));
			    SurveyBean.setDescription(rs.getString("DESCRIPTION"));
			    
			    JSONObject ch = new JSONObject(rs.getString("CHOICE"));
			    Map<String, Integer> m = new HashMap<>();
			    for(String k: ch.keySet()){
			    	m.put(k, 0);
			    }
			    SurveyBean.setChoices(m);
			    
			    JSONObject ch1 = new JSONObject(rs.getString("RESULT"));
			    Map<String, Integer> m1 = new HashMap<>();
			    for(String k: ch1.keySet()){
			    	m1.put(k, ch1.getInt(k));
			    }
			    SurveyBean.setResult(m1);
			    SurveyBean.setTotal(rs.getInt("TOTAL"));
			    SurveyBeans.add(SurveyBean);
			   }
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }
		  return SurveyBeans;
		 }

}
