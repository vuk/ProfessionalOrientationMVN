package com.vukstankovic.professionalorientation.config;

public class DBConnect {
	String url = "jdbc:mysql://85.17.187.29/puskice_proor?useUnicode=yes&characterEncoding=UTF-8";
    String user = "puskice_proor";
    String password = "20proor14";
    
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
