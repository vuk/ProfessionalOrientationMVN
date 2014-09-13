package com.vukstankovic.recommender;

import java.util.Iterator;

import com.vukstankovic.professionalorientation.classes.Choice;
import com.vukstankovic.professionalorientation.classes.User;

public class RecommendationCalc {
	
	public void calcMarkEstimation(User u){
		Iterator<Choice> it = u.getChoices().iterator();
		while(it.hasNext()){
			Choice c = it.next();
			double numerator = numerator(c, u);
			double denominator = denominator(c, u);
			c.setMarkEstimation(numerator/denominator);
		}
	}
	
	public double numerator(Choice c, User u){
		return 0;
	}
	
	public double denominator(Choice c, User u){
		return 1;
	}
}
