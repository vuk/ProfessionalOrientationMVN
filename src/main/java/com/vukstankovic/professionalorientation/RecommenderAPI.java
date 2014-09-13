package com.vukstankovic.professionalorientation;

import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.vukstankovic.professionalorientation.classes.Choice;
import com.vukstankovic.professionalorientation.classes.College;
import com.vukstankovic.professionalorientation.classes.User;
import com.vukstankovic.professionalorientation.helpers.EstimationComparator;
import com.vukstankovic.recommender.Recommendations;
import com.vukstankovic.recommender.Recommender;

@Path("recommendation")
public class RecommenderAPI {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public String getSimilar(@QueryParam("id") String id){
		Recommender rc = new Recommender();
		Recommendations rcm = new Recommendations();
		rc.calculateSimilarity(Integer.parseInt(id));
		ArrayList<User> similarUsers = rc.getSimilarUsers();
		ArrayList<Choice> choices = rcm.getChoices(similarUsers);
		ArrayList<Choice> linkedChoices = rcm.addSimilarityScoresToChoices(choices, similarUsers);
		ArrayList<College> collegeRecommendations = rcm.collegeEstimation(linkedChoices);
		Collections.sort(collegeRecommendations, new EstimationComparator());
		String json = new Gson().toJson(collegeRecommendations);
		return json;
	}
}
