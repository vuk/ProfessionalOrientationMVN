package com.vukstankovic.recommender;

import java.util.ArrayList;
import java.util.Iterator;

import com.vukstankovic.professionalorientation.classes.Choice;
import com.vukstankovic.professionalorientation.classes.User;

public class Recommendations {
	private ArrayList<Choice> choices;
	RecommendationCalc rc = new RecommendationCalc();
	
	public ArrayList<Choice> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<Choice> choices) {
		this.choices = choices;
	}


	public void generateChoicesList(ArrayList<User> users){
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
}