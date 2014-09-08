package com.vukstankovic.recommender;

import com.vukstankovic.professionalorientation.classes.User;

public interface RecommenderInterface {
	
	public double calculateSimilarity(User u1, User u2);
	
	public double sumPrefs(User u);
	
	public double sumPrefsSquare(User u);
	

}
