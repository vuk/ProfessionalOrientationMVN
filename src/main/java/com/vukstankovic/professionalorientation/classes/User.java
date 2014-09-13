package com.vukstankovic.professionalorientation.classes;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.Personalities;
import com.vukstankovic.professionalorientation.config.DBConnect;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private int schoolType;
	private String highSchool;
	private int yearOfBirth;
	private int gender;
	private int currentTown;
	private double poljoprivreda;
	private double sumarstvo;
	private double geodezija;
	private double masinstvo;
	private double elektrotehnika;
	private double hemija;
	private double tekstil;
	private double saobracaj;
	private double trgovinaugostiteljstvo;
	private double ekonomijapravo;
	private double hidrometeorologija;
	private double kultura;
	private double zdravstvo;
	private double usluge;
	private double similarity;
	private ArrayList<Choice> choices; 
	private Choice currentChoice;
	
	public Choice getCurrentChoice() {
		return currentChoice;
	}
	public void setCurrentChoice(Choice currentChoice) {
		this.currentChoice = currentChoice;
	}
	public ArrayList<Choice> getChoices() {
		return choices;
	}
	public void setChoices(ArrayList<Choice> choices) {
		this.choices = choices;
	}
	public void addChoice(Choice c){
		this.choices.add(c);
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(int schoolType) {
		this.schoolType = schoolType;
	}
	public String getHighSchool() {
		return highSchool;
	}
	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getCurrentTown() {
		return currentTown;
	}
	public void setCurrentTown(int currentTown) {
		this.currentTown = currentTown;
	}
	public double getPoljoprivreda() {
		return poljoprivreda;
	}
	public double getSumarstvo() {
		return sumarstvo;
	}
	public double getGeodezija() {
		return geodezija;
	}
	public double getMasinstvo() {
		return masinstvo;
	}
	public double getElektrotehnika() {
		return elektrotehnika;
	}
	public double getHemija() {
		return hemija;
	}
	public double getTekstil() {
		return tekstil;
	}
	public double getSaobracaj() {
		return saobracaj;
	}
	public double getTrgovinaugostiteljstvo() {
		return trgovinaugostiteljstvo;
	}
	public double getEkonomijapravo() {
		return ekonomijapravo;
	}
	public double getHidrometeorologija() {
		return hidrometeorologija;
	}
	public double getKultura() {
		return kultura;
	}
	public double getZdravstvo() {
		return zdravstvo;
	}
	public double getUsluge() {
		return usluge;
	}
	public void setPoljoprivreda(double poljoprivreda) {
		this.poljoprivreda = poljoprivreda;
	}
	public void setSumarstvo(double sumarstvo) {
		this.sumarstvo = sumarstvo;
	}
	public void setGeodezija(double geodezija) {
		this.geodezija = geodezija;
	}
	public void setMasinstvo(double masinstvo) {
		this.masinstvo = masinstvo;
	}
	public void setElektrotehnika(double elektrotehnika) {
		this.elektrotehnika = elektrotehnika;
	}
	public void setHemija(double hemija) {
		this.hemija = hemija;
	}
	public void setTekstil(double tekstil) {
		this.tekstil = tekstil;
	}
	public void setSaobracaj(double saobracaj) {
		this.saobracaj = saobracaj;
	}
	public void setTrgovinaugostiteljstvo(double trgovinaugostiteljstvo) {
		this.trgovinaugostiteljstvo = trgovinaugostiteljstvo;
	}
	public void setEkonomijapravo(double ekonomijapravo) {
		this.ekonomijapravo = ekonomijapravo;
	}
	public void setHidrometeorologija(double hidrometeorologija) {
		this.hidrometeorologija = hidrometeorologija;
	}
	public void setKultura(double kultura) {
		this.kultura = kultura;
	}
	public void setZdravstvo(double zdravstvo) {
		this.zdravstvo = zdravstvo;
	}
	public void setUsluge(double usluge) {
		this.usluge = usluge;
	}
	
	public void getDBChoices(){
		DBConnect db = new DBConnect();
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        setChoices(new ArrayList<Choice>());
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
			st = (Statement) con.createStatement();
	        rs = st.executeQuery("SELECT * FROM choices WHERE user_id = "+getId());
	        while(rs.next()){
	        	Choice c = new Choice();
	        	c.setId(rs.getInt(1));
	        	c.setUser_id(rs.getInt(2));
	        	c.setCollege_id(rs.getInt(3));
	        	c.setMark(rs.getInt(4));
	        	addChoice(c);
	        }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	
	        } catch (SQLException ex) {
	            Logger lgr = Logger.getLogger(Personalities.class.getName());
	            lgr.log(Level.WARNING, ex.getMessage(), ex);
	        }
	    }
        
	}
	
	
}
