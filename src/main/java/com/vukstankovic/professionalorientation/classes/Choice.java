package com.vukstankovic.professionalorientation.classes;

public class Choice {
	private int id;
	private int user_id;
	private int college_id;
	private int mark;
	private double markEstimation;
	
	public double getMarkEstimation() {
		return markEstimation;
	}
	public void setMarkEstimation(double markEstimation) {
		this.markEstimation = markEstimation;
	}
	public int getId() {
		return id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getCollege_id() {
		return college_id;
	}
	public int getMark() {
		return mark;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	
}
