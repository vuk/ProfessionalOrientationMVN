package com.vukstankovic.recommender;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.Personalities;
import com.vukstankovic.professionalorientation.classes.Choice;
import com.vukstankovic.professionalorientation.classes.College;
import com.vukstankovic.professionalorientation.classes.User;
import com.vukstankovic.professionalorientation.config.DBConnect;

public class RecommendationCalc {
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs;
    private DBConnect db = new DBConnect();
	
	public College calcMarkEstimation(ArrayList<Choice> choices){
		Iterator<Choice> it = choices.iterator();
		Choice ch = it.next();
		double mark = numerator(choices)/denominator(choices);
		//College c = getCollegeByID(ch.getCollege_id());
		College c = new College();
		c.setId(ch.getCollege_id());
		c.setMarkEstimation(mark);
		return c;
	}
	
	public double numerator(ArrayList<Choice> choices){
		Iterator<Choice> it = choices.iterator();
		double numerator = 0;
		while (it.hasNext()) {
			Choice c = it.next();
			numerator += c.getSimilarityScore()*c.getMark();
		}
		return numerator;
	}
	
	public double denominator(ArrayList<Choice> choices){
		Iterator<Choice> it = choices.iterator();
		double denominator = 0;
		while(it.hasNext()){
			Choice c = it.next();
			denominator += Math.abs(c.getSimilarityScore());
		}
		return denominator;
	}
	
	public College getCollegeByID(int id){
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM colleges WHERE id = "+id);
			rs.next();
			College c = new College();
        	c.setId(rs.getInt(1));
        	c.setTitle(rs.getString(2));
        	c.setProgramme(rs.getString(3));
        	c.setAbbrevation(rs.getString(4));
        	c.setArea(rs.getString(5));
        	c.setDescription(rs.getString(6));
        	c.setInterest(rs.getString(7));
        	c.setPonder(rs.getInt(8));
        	rs.close();
        	return c;
        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return new College();
        
	}
}
