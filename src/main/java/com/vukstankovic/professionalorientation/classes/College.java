package com.vukstankovic.professionalorientation.classes;

public class College {
	private int id;
	private String title;
	private String programme;
	private String abbrevation;
	private String area;
	private String description;
	private String interest;
	
	public College(){
		
	}
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getProgramme() {
		return programme;
	}
	public String getAbbrevation() {
		return abbrevation;
	}
	public String getArea() {
		return area;
	}
	public String getDescription() {
		return description;
	}
	public String getInterest() {
		return interest;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	
}
