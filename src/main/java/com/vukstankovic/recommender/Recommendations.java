package com.vukstankovic.recommender;

import java.util.ArrayList;
import java.util.Iterator;

import com.vukstankovic.professionalorientation.Colleges;
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
			Iterator<Choice> iterator = u.getChoices().iterator();
			while(iterator.hasNext()){
				choices.add(iterator.next());
			}
		}
	}

	public ArrayList<Choice> addSimilarityScoresToChoices(ArrayList<Choice> choices,
			ArrayList<User> similarUsers) {
		Iterator<Choice> itc = choices.iterator();
		ArrayList<Choice> linkedChoices = new ArrayList<Choice>();
		while(itc.hasNext()){
			Choice c = itc.next();
			Iterator<User> itu = similarUsers.iterator();
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

	public ArrayList<College> collegeEstimation(ArrayList<Choice> linkedChoices) {
		Iterator<Choice> it = linkedChoices.iterator();
		Iterator<Choice> helper = linkedChoices.iterator();
		ArrayList<Integer> usedColleges = new ArrayList<Integer>();
		ArrayList<College> colleges = new ArrayList<College>();
		RecommendationCalc rcc = new RecommendationCalc();
		while(it.hasNext()){
			Choice c = it.next();
			if(usedColleges.contains(c.getCollege_id()))
				continue;
			usedColleges.add(c.getCollege_id());
			ArrayList<Choice> sublist = new ArrayList<Choice>();
			sublist.add(c);
			while(helper.hasNext()){
				Choice c1 = helper.next();
				if(c.getCollege_id() == c1.getCollege_id()){
					sublist.add(c1);
				}
			}
			colleges.add(rcc.calcMarkEstimation(sublist));
			if(colleges.size() > 1)
				break;
		}
		return colleges;
	}
}
