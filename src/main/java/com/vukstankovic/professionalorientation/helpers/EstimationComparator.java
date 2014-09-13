package com.vukstankovic.professionalorientation.helpers;

import java.util.Comparator;

import com.vukstankovic.professionalorientation.classes.College;

public class EstimationComparator implements Comparator<College> {
	@Override
	public int compare(College o1, College o2) {
		Double ponder1 = o1.getMarkEstimation();
		Double ponder2 = o2.getMarkEstimation();
		return ponder1.compareTo(ponder2);
	}
}
