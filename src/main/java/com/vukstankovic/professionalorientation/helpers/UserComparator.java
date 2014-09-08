package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.User;

public class UserComparator implements Comparator<User> {
	@Override
	public int compare(User x, User y) {
		Double simx = x.getSimilarity();
		Double simy = y.getSimilarity();
		return simx.compareTo(simy);
	}
}
