package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.College;

public class CollegePriorityComparator implements Comparator<College>{
	@Override
	public int compare(College o1, College o2) {
		Integer priority1 = o1.getPriority();
		Integer priority2 = o2.getPriority();
		return priority1.compareTo(priority2);
	}
}
