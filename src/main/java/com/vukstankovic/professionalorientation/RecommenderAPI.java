package com.vukstankovic.professionalorientation;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.vukstankovic.professionalorientation.classes.User;
import com.vukstankovic.recommender.Recommendations;
import com.vukstankovic.recommender.Recommender;

@Path("recommendation")
public class RecommenderAPI {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public String getSimilar(@QueryParam("id") String id){
		Recommender rc = new Recommender();
		rc.calculateSimilarity(Integer.parseInt(id));
		Recommendations rcm = new Recommendations();
		ArrayList<User> similar = rc.getSimilarUsers();
		rcm.generateChoicesList(similar);
		String json = new Gson().toJson(similar);
		return json;
	}
}
