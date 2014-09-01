package com.vukstankovic.professionalorientation;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.config.DBConnect;

@Path("/choices")
public class SaveChoices {

	@Context
	ServletContext ctx;

	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + "; charset=UTF-8")
	public Response insertUser(MultivaluedMap<String, String> formData) {
		Connection con = null;
		Statement st = null;
		DBConnect db = new DBConnect();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(db.getUrl(),
					db.getUser(), db.getPassword());
			st = (Statement) con.createStatement();
			Iterator<String> it = formData.keySet().iterator();

			while (it.hasNext()) {
				String theKey = (String) it.next();
				if(theKey == "id") continue;
				st.executeUpdate(
						"INSERT INTO choices VALUES " + "(NULL, '"
								+ formData.getFirst("id") + "', '"
								+ theKey + "', '"
								+ formData.getFirst(theKey) + "')",
						Statement.RETURN_GENERATED_KEYS);
				ResultSet newID = st.getGeneratedKeys();
				newID.next();
				newID.getInt(1);
			}

			URI targetURIForRedirection;
			targetURIForRedirection = new URI(
					"http://po.puskice.org/thank-you");

			return Response.seeOther(targetURIForRedirection).build();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Personalities.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		URI targetURIForRedirection = null;
		try {
			targetURIForRedirection = new URI("http://po.puskice.org/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Response.seeOther(targetURIForRedirection).build();
	}
}
