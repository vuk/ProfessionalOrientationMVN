package com.vukstankovic.professionalorientation.config;

public class DBConnect {
	String url = "jdbc:mysql://82.192.92.55/vukstank_professional";
    String user = "vukstank_vukad";
    String password = "partizan89";
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
