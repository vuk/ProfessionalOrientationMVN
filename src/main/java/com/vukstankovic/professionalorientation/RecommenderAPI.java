package com.vukstankovic.professionalorientation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.vukstankovic.recommender.Recommender;

@Path("recommendation")
public class RecommenderAPI {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public String getSimilar(@QueryParam("id") String id){
		Recommender rc = new Recommender();
		rc.calculateSimilarity(Integer.parseInt(id));
		String json = new Gson().toJson(rc.getSimilarUsers());
		return json;
	}
}
