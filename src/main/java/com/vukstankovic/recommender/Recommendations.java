package com.vukstankovic.recommender;

import java.util.ArrayList;
import java.util.Iterator;

import com.vukstankovic.professionalorientation.classes.Choice;
import com.vukstankovic.professionalorientation.classes.College;
import com.vukstankovic.professionalorientation.classes.User;

public class Recommendations {
	private ArrayList<Choice> choices;
	RecommendationCalc rc = new RecommendationCalc();
	
	public ArrayList<Choice> getChoices(ArrayList<User> users) {
		generateChoicesList(users);
		return choices;
	}

	public void setChoices(ArrayList<Choice> choices) {
		this.choices = choices;
	}

	public void generateChoicesList(ArrayList<User> users){
		choices = new ArrayList<Choice>();
		Iterator<User> it = users.iterator();
		while(it.hasNext()){
			User u = it.next();
			rc.calcMarkEstimation(u);
			Iterator<Choice> iterator = u.getChoices().iterator();
			while(iterator.hasNext()){
				choices.add(iterator.next());
			}
		}
	}

	public ArrayList<Choice> addSimilarityScoresToChoices(ArrayList<Choice> choices2,
			ArrayList<User> similarUsers) {
		Iterator<Choice> itc = choices2.iterator();
		Iterator<User> itu = similarUsers.iterator();
		ArrayList<Choice> linkedChoices = new ArrayList<Choice>();
		while(itc.hasNext()){
			Choice c = itc.next();
			while(itu.hasNext()){
				User u = itu.next();
				if(c.getUser_id() == u.getId()){
					c.setSimilarityScore(u.getSimilarity());
				}
			}
			linkedChoices.add(c);
		}
		return linkedChoices;
	}
}
