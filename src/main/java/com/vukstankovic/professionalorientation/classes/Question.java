package com.vukstankovic.professionalorientation.classes;

public class Question {
	private int id;
	private String question;
	private int personality_type_id;
	private int question_type_id;
	private int priority;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getPersonality_type_id() {
		return personality_type_id;
	}
	public void setPersonality_type_id(int personality_type_id) {
		this.personality_type_id = personality_type_id;
	}
	public int getQuestion_type_id() {
		return question_type_id;
	}
	public void setQuestion_type_id(int question_type_id) {
		this.question_type_id = question_type_id;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
