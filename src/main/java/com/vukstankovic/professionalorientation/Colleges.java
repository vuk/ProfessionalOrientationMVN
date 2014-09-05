package com.vukstankovic.professionalorientation;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.classes.College;
import com.vukstankovic.professionalorientation.helpers.CollegeComparator;
import com.vukstankovic.professionalorientation.helpers.CollegePriorityComparator;
import com.vukstankovic.professionalorientation.helpers.ValueComparator;
import com.vukstankovic.professionalorientation.config.DBConnect;

@Path("colleges")
public class Colleges {
	public String response = "Nothing to respond";
	public ArrayList<College> colleges = new ArrayList<College>();
	HashMap<String,Double> interests = new HashMap<String,Double>();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public String getIt(@QueryParam("id") String id) {
		System.out.println(id + "Test 0");
    	Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        DBConnect db = new DBConnect();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM users WHERE id = "+id);
            rs.next();
            
            double poljoprivreda = rs.getDouble(10);
            interests.put("1", poljoprivreda);
            double sumarstvo = rs.getDouble(11);
            interests.put("2", sumarstvo);
            double geodezija = rs.getDouble(12);
            interests.put("3", geodezija);
            double masinstvo = rs.getDouble(13);
            interests.put("4", masinstvo);
            double elektrotehnika = rs.getDouble(14);
            interests.put("5", elektrotehnika);
            double hemija = rs.getDouble(15);
            interests.put("6", hemija);
            double tekstil = rs.getDouble(16);
            interests.put("7", tekstil);
            double saobracaj = rs.getDouble(17);
            interests.put("8", saobracaj);
            double trgovinaugostiteljstvo = rs.getDouble(18);
            interests.put("9", trgovinaugostiteljstvo);
            double ekonomijapravo = rs.getDouble(19);
            interests.put("10", ekonomijapravo);
            double hidrometeorologija = rs.getDouble(20);
            interests.put("11", hidrometeorologija);
            double kultura = rs.getDouble(21);
            interests.put("12", kultura);
            double zdravstvo = rs.getDouble(22);
            interests.put("13", zdravstvo);
            double usluge = rs.getDouble(23);
            interests.put("14", usluge);
            
            ValueComparator bvc =  new ValueComparator(interests);
            NavigableMap<String,Double> sorted_interests = new TreeMap<String,Double>(bvc);
            
            sorted_interests.putAll(interests);
            
            System.out.println(interests.toString()+ "Test2");
            System.out.println(sorted_interests.toString()+ "Test");
            int i = 1;
            String first = "";
            String second = "";
            String third = "";
            for(Iterator<Entry<String,Double>>it=sorted_interests.entrySet().iterator();it.hasNext();){
                Entry<String, Double> entry = it.next();
                if(i == 1) first = entry.getKey();
                if(i == 2) second = entry.getKey();
                if(i == 3) third = entry.getKey();
                i++;
            }
               
            System.out.println(first + " " + second + " " +third + "Test 3");
            
            rs.close();
            /*rs = st.executeQuery("SELECT * FROM colleges WHERE interest LIKE '%"+first+",%'"
            		+ " AND interest LIKE '%"+second+",%'"
            				+ " AND interest LIKE '%"+third+",%'");*/
            rs = st.executeQuery("SELECT * FROM colleges WHERE interest LIKE '%"+first+",%' ORDER BY ponder DESC");
            while (rs.next()) {
            	College c = new College();
            	c.setId(rs.getInt(1));
            	c.setTitle(rs.getString(2));
            	c.setProgramme(rs.getString(3));
            	c.setAbbrevation(rs.getString(4));
            	c.setArea(rs.getString(5));
            	c.setDescription(rs.getString(6));
            	c.setInterest(rs.getString(7));
            	c.setPonder(rs.getInt(8));
            	c.setPriority(1);
            	if(colleges.contains(c)){
            		continue;
            	}
            	colleges.add(c);
            }
            
            rs.close();
/*            rs = st.executeQuery("SELECT * FROM colleges WHERE (interest LIKE '%"+first+",%'"
            		+ " AND interest LIKE '%"+second+",%') "
            				+ " OR (interest LIKE '%"+first+",%' AND interest LIKE '%"+third+",%')"
            						+ " OR (interest LIKE '%"+second+",%' AND interest LIKE '%"+third+",%')");*/
            rs = st.executeQuery("SELECT * FROM colleges WHERE interest LIKE '%"+second+",%' ORDER BY ponder DESC");
            while (rs.next()) {
            	College c = new College();
            	c.setId(rs.getInt(1));
            	c.setTitle(rs.getString(2));
            	c.setProgramme(rs.getString(3));
            	c.setAbbrevation(rs.getString(4));
            	c.setArea(rs.getString(5));
            	c.setDescription(rs.getString(6));
            	c.setInterest(rs.getString(7));
            	c.setPonder(rs.getInt(8));
            	c.setPriority(2);
            	
            	if(colleges.contains(c)){
            		continue;
            	}
            	colleges.add(c);
            }

            rs.close();
            /*rs = st.executeQuery("SELECT * FROM colleges WHERE interest LIKE '%"+first+",%'"
            				+ " OR interest LIKE '%"+third+",%'"
            						+ " OR interest LIKE '%"+second+",%'");*/
            rs = st.executeQuery("SELECT * FROM colleges WHERE interest LIKE '%"+third+",%' ORDER BY ponder DESC");
            while (rs.next()) {
            	College c = new College();
            	c.setId(rs.getInt(1));
            	c.setTitle(rs.getString(2));
            	c.setProgramme(rs.getString(3));
            	c.setAbbrevation(rs.getString(4));
            	c.setArea(rs.getString(5));
            	c.setDescription(rs.getString(6));
            	c.setInterest(rs.getString(7));
            	c.setPonder(rs.getInt(8));
            	c.setPriority(3);
            	
            	if(colleges.contains(c)){
            		continue;
            	}
            	colleges.add(c);
            }
            ArrayList<College> top15colleges = new ArrayList<College>();
            Collections.sort(colleges, new CollegeComparator());
            Collections.reverse(colleges);
            int firstPick = 0;
            int secondPick = 0;
            int thirdPick = 0;
            for(int j = 0; j < 50; j++){
            	if(firstPick < 5 && colleges.get(j).getPriority() == 1){
            		top15colleges.add(colleges.get(j));
            		firstPick ++;
            	}
            	if(secondPick < 5 && colleges.get(j).getPriority() == 2){
            		top15colleges.add(colleges.get(j));
            		secondPick ++;
            	}
            	if(thirdPick < 5 && colleges.get(j).getPriority() == 3){
            		top15colleges.add(colleges.get(j));
            		thirdPick ++;
            	}
            }
            //Collections.sort(top10colleges, new CollegePriorityComparator());
            String json = new Gson().toJson(top15colleges);
            return json;

        } catch (SQLException ex) {
        	response = ex.getMessage();
            Logger lgr = Logger.getLogger(Personalities.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
        	response = e.getMessage();
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        
        return response;
    }
}
