package com.vukstankovic.professionalorientation;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.vukstankovic.professionalorientation.classes.SchoolType;
import com.vukstankovic.professionalorientation.config.DBConnect;

@Path("colleges")
public class Colleges {
	public String response = "Nothing to respond";
	public ArrayList<College> colleges = new ArrayList<College>();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public String getIt(@QueryParam("id") String id) {
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
            double sumarstvo = rs.getDouble(11);
            double geodezija = rs.getDouble(12);
            double masinstvo = rs.getDouble(13);
            double elektrotehnika = rs.getDouble(14);
            double hemija = rs.getDouble(15);
            double tekstil = rs.getDouble(16);
            double saobracaj = rs.getDouble(17);
            double trgovinaugostiteljstvo = rs.getDouble(18);
            double ekonomijapravo = rs.getDouble(19);
            double hidrometeorologija = rs.getDouble(20);
            double kultura = rs.getDouble(21);
            double zdravstvo = rs.getDouble(22);
            double usluge = rs.getDouble(23);
            rs.close();
            rs = st.executeQuery("SELECT * FROM colleges");
            while (rs.next()) {
            	College c = new College();
            	c.setId(rs.getInt(1));
            	c.setTitle(rs.getString(2));
            	c.setProgramme(rs.getString(3));
            	c.setAbbrevation(rs.getString(4));
            	c.setArea(rs.getString(5));
            	c.setDescription(rs.getString(6));
            	c.setInterest(rs.getString(7));
            	
            	colleges.add(c);
            }
            String json = new Gson().toJson(colleges);
            return json;

        } catch (SQLException ex) {
        	response = ex.getMessage();
            Logger lgr = Logger.getLogger(Personalities.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
