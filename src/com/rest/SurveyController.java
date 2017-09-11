package com.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.ServeyDML;
import com.db.SurveyDDL;
import com.model.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/surveys")
public class SurveyController {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllSurveys() { 
	   return Response.ok(SurveyDDL.getAllSurveyBeans()).build();
	  }
	
	@GET
	@Path("/result")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSurveyResult() {  	   
		return Response.ok(SurveyDDL.getSurveyResult()).build();
	  }

	@GET
	@Path("/{id}")
	public SurveyBean getSurveyById(@PathParam("id") int id) {  	   
	   return SurveyDDL.getSurveyById(id);
	  }

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
    public Response createSurvey(
    		@FormParam("title") String title, 
    		@FormParam("description") String desc, 
    		@FormParam("choices") String choice)
    {
		SurveyBean survey = new SurveyBean();
		survey.setTitle(title);
		survey.setDescription(desc);
		String[] s = choice.split("\n");
		Map<String, Integer> m = new HashMap<>();
		for(String s1: s){
			m.put(s1, 0);
		}
		survey.setChoices(m);
		survey.setResult(m);
		ServeyDML.addSurvey(survey);
		Map<String,Boolean> map= new HashMap<>();
		map.put("success", true);
		return Response.ok(map).build();
    }
	
	@PUT
   @Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	//@Produces( "application/json" )
	//@Consumes("application/x-www-form-urlencoded")
   public Response updateSurveyResult(@PathParam("id") String id,@FormParam("value") String value){
	ServeyDML.updateSurveyResult(id, value);
	Map<String,Boolean> map= new HashMap<>();
	map.put("success", true);
	return Response.ok(map).build();
   }
}
