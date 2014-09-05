package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.College;

public class CollegeComparator implements Comparator<College> {
	@Override
	public int compare(College o1, College o2) {
		Integer ponder1 = (int) Math.round(o1.getPonder()*this.getMultiplier(o1.getPriority()));
		Integer ponder2 = (int) Math.round(o2.getPonder()*this.getMultiplier(o2.getPriority()));
		return ponder1.compareTo(ponder2);
	}
	
	private double getMultiplier(int i){
		switch(i){
		case 1 : return 0.5;
		case 2 : return 0.48;
		case 3 : return 0.46;
		default : return 0.0;
		}
	}

}
