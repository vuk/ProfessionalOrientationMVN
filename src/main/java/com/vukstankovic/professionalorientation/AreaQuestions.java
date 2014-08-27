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
import com.vukstankovic.professionalorientation.classes.Question;
import com.vukstankovic.professionalorientation.config.DBConnect;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Root resource (exposed at "home" path)
 */
@Path("areas")
public class AreaQuestions {
	public String response = "Nothing to respond";
	public ArrayList<Question> questions = new ArrayList<Question>();
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
            rs = st.executeQuery("SELECT * FROM questions WHERE question_type_id != 'NULL' ORDER BY priority asc");

            while (rs.next()) {
            	Question q = new Question();
            	q.setId(rs.getInt(1));
            	q.setPersonality_type_id(rs.getInt(3));
            	q.setQuestion_type_id(rs.getInt(4));
            	q.setPriority(rs.getInt(5));
            	q.setQuestion(rs.getString(2));
            	questions.add(q);
                response = rs.getString(1);
            }
            String json = new Gson().toJson(questions);
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