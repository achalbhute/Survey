package com.model;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="survey")
public class SurveyBean {

	
	
	public SurveyBean() {
		super();
	}
	public SurveyBean(String input) {
		//
	}
	@Column(name="ID")
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CHOICE")
	private Map<String,Integer> choices;
	
	@Column(name="TOTAL")
	private int total;
	
	@Column(name="RESULT")
	private Map<String,Integer> result;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Map<String, Integer> getChoices() {
		return choices;
	}
	public void setChoices(Map<String, Integer> choices) {
		this.choices = choices;
	}
	public Map<String, Integer> getResult() {
		return result;
	}
	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}
	
	
}
