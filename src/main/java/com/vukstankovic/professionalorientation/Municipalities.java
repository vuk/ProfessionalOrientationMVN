package com.vukstankovic.professionalorientation;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.classes.Municipality;
import com.vukstankovic.professionalorientation.config.DBConnect;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("municipalities")
public class Municipalities {
	public String response = "Nothing to respond";
	public ArrayList<Municipality> municipalities = new ArrayList<Municipality>();
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public String getIt() {
    	Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DBConnect db = new DBConnect();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM municipalities ORDER BY title asc");

            while (rs.next()) {
            	Municipality q = new Municipality();
            	q.setId(rs.getInt(1));
            	q.setArea_id(rs.getInt(3));
            	q.setTitle(rs.getString(2));
            	q.setInfo(rs.getString(4));
            	
            	municipalities.add(q);
            }
            String json = new Gson().toJson(municipalities);
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