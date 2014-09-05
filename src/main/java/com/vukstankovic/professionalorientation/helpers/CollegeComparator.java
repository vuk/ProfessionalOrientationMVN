package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.College;

public class CollegeComparator implements Comparator<College> {
	@Override
	public int compare(College o1, College o2) {
		Integer ponder1 = calculatePonder(o1);
		Integer ponder2 = calculatePonder(o2);
		return ponder1.compareTo(ponder2);
	}
	
	
	public int calculatePonder(College o1){
		return (int) ((int) o1.getPonder()*getMultiplier(o1.getPriority()));
	}
	
	public double getMultiplier(int i){
		switch (i) {
		case 1:
			return 0.5;
		case 2: 
			return 0.49;
		case 3: 
			return 0.48;
		default: 
			return 0.0;
		}
	}
}
