package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.College;

public class CollegeComparator implements Comparator<College> {
	@Override
	public int compare(College o1, College o2) {
		Integer ponder1 = o1.getPonder();
		Integer ponder2 = o2.getPonder();
		return ponder1.compareTo(ponder2);
	}

}
