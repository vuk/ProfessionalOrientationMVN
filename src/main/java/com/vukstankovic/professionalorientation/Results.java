package com.vukstankovic.professionalorientation;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.rule.Rule;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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


@Path("/request")
public class Results {
	@Context ServletContext ctx;
	@Path("/post")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + "; charset=UTF-8")
	public Response insertUser(MultivaluedMap<String, String> formData) {
		Connection con = null;
        Statement st = null;
        int rs = -9;
        DBConnect db = new DBConnect();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
            rs = st.executeUpdate("INSERT INTO users VALUES "
            		+ "(NULL, '"+formData.getFirst("first_name")+"', '"
            				   +formData.getFirst("last_name")+"', '"
            				   +formData.getFirst("email")+"', '"
            				   +formData.getFirst("school")+"', '"
            				   +formData.getFirst("high_school")+"', '"
            				   +formData.getFirst("year_of_birth")+"', '"
            				   +formData.getFirst("gender")+"', '"
            				   +formData.getFirst("current_town")+"', "
            				   +"NULL, NULL, NULL, NULL, NULL, NULL, "
            				   +"NULL, NULL, NULL, NULL, NULL, NULL,"
            				   +"NULL, NULL, NULL)", Statement.RETURN_GENERATED_KEYS);
            ResultSet newID = st.getGeneratedKeys();
            newID.next();
            rs = newID.getInt(1);
            URI targetURIForRedirection;
			targetURIForRedirection = new URI("http://localhost:8000/personality-questions?id="+rs);
			
            return Response.seeOther(targetURIForRedirection).build();

        } catch (SQLException ex) {
        	Logger lgr = Logger.getLogger(Personalities.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			targetURIForRedirection = new URI("http://localhost:8000/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		rs = -1;
        return Response.seeOther(targetURIForRedirection).build();
	}
	
	
	@Path("/calculate")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + "; charset=UTF-8")
	public Response calculation(MultivaluedMap<String, String> formData) {
		Connection con = null;
        Statement st = null;
        String url = "jdbc:mysql://localhost:3306/professional_orientation?useUnicode=yes&characterEncoding=UTF-8";
        String user = "root";
        String password = "root";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
            st = (Statement) con.createStatement();
            for(int i = 1; i <= 170; i++){
            	st.executeUpdate("INSERT INTO question_answers VALUES "
                		+ "(NULL, '"+formData.getFirst("id")+"', '"
                				   +i+"', '"
                				   +formData.getFirst(Integer.toString(i))+"')", Statement.RETURN_GENERATED_KEYS);
                ResultSet newID = st.getGeneratedKeys();
                newID.next();
                newID.getInt(1);
            }
            FIS fis = FIS.load(ctx.getRealPath("rules/rules.fcl"));
    		
    		if (fis == null){
    			System.err.println("Greska");
    		}
    		
    		fis.setVariable("administracija", Double.parseDouble(formData.getFirst("administration")));
    		fis.setVariable("bezbednost", Double.parseDouble(formData.getFirst("security")));
    		fis.setVariable("tehnika", Double.parseDouble(formData.getFirst("technology")));
    		fis.setVariable("kreativnost", Double.parseDouble(formData.getFirst("creativity")));
    		fis.setVariable("kultura", Double.parseDouble(formData.getFirst("culture")));
    		fis.setVariable("literatura", Double.parseDouble(formData.getFirst("literature")));
    		fis.setVariable("nauka", Double.parseDouble(formData.getFirst("science")));
    		fis.setVariable("pomaganje", Double.parseDouble(formData.getFirst("helping")));
    		fis.setVariable("poljoprivreda", Double.parseDouble(formData.getFirst("agriculture")));
    		fis.setVariable("zanati", Double.parseDouble(formData.getFirst("craftsmenship")));
    		fis.setVariable("sport", Double.parseDouble(formData.getFirst("sports")));
    		fis.setVariable("usluge", Double.parseDouble(formData.getFirst("services")));
    		fis.setVariable("upravljanje", Double.parseDouble(formData.getFirst("management")));
    		
    		fis.setVariable("realisticni", Double.parseDouble(formData.getFirst("realistic")));
    		fis.setVariable("konvencionalni", Double.parseDouble(formData.getFirst("conventional")));
    		fis.setVariable("preduzetnicki", Double.parseDouble(formData.getFirst("entrepreneur")));
    		fis.setVariable("istrazivacki", Double.parseDouble(formData.getFirst("explorer")));
    		fis.setVariable("umetnicki", Double.parseDouble(formData.getFirst("artistic")));
    		fis.setVariable("drustveni", Double.parseDouble(formData.getFirst("social")));
    		
    		fis.evaluate();
    		for( Rule r : fis.getFunctionBlock("block1").getFuzzyRuleBlock("porules").getRules() )
    		      System.out.println(r);
    		double poljoprivreda = fis.getVariable("opoljoprivreda").defuzzify();
    		double sumarstvo = fis.getVariable("osumarstvo").defuzzify();
    		double geodezija = fis.getVariable("ogeodezija").defuzzify();
    		double masinstvo = fis.getVariable("omasinstvo").defuzzify();
    		double elektrotehnika = fis.getVariable("oelektrotehnika").defuzzify();
    		double hemija = fis.getVariable("ohemija").defuzzify();
    		double tekstil = fis.getVariable("otekstil").defuzzify();
    		double saobracaj = fis.getVariable("osaobracaj").defuzzify();
    		double trgovinaugostiteljstvo = fis.getVariable("otrgovinaugostiteljstvo").defuzzify();
    		double ekonomijapravo = fis.getVariable("oekonomijapravo").defuzzify();
    		double hidmet = fis.getVariable("ohidmet").defuzzify();
    		double kultura = fis.getVariable("okultura").defuzzify();
    		double zdravstvo = fis.getVariable("ozdravstvo").defuzzify();
    		double licneusluge = fis.getVariable("olicneusluge").defuzzify();
    		st.executeUpdate("UPDATE users SET poljoprivreda ='"+poljoprivreda+"', "
    				+ "sumarstvo = '"+sumarstvo+"', "
    				+ "geodezija = '"+geodezija+"', "
    				+ "masinstvo = '"+masinstvo+"',"
    				+ "elektrotehnika = '"+elektrotehnika+"',"
    				+ "hemija = '"+hemija+"', "
    				+ "tekstil = '"+tekstil+"', "
    				+ "saobracaj = '"+saobracaj+"', "
    				+ "trgovinaugostiteljstvo = '"+trgovinaugostiteljstvo+"', "
    				+ "ekonomijapravo = '"+ekonomijapravo+"', "
    				+ "hidrometeorologija = '"+hidmet+"', "	
    				+ "kultura = '"+kultura+"', "
    				+ "zdravstvo = '"+zdravstvo+"', "
    				+ "usluge = '"+licneusluge+"' "
    				+ "WHERE id = '"+formData.getFirst("id")+"'");
            URI targetURIForRedirection;
			targetURIForRedirection = new URI(
					"http://localhost:8000/finish?pol="+poljoprivreda+"&sum="+sumarstvo+"&geo="+geodezija
					+"&mas="+masinstvo+"&eth="+elektrotehnika+"&hem="+hemija+"&tek="+tekstil
					+"&sao="+saobracaj+"&tru="+trgovinaugostiteljstvo+"&ekp="+ekonomijapravo
					+"&hid="+hidmet+"&kul="+kultura+"&zdr="+zdravstvo+"&usl="+licneusluge);
			
            return Response.seeOther(targetURIForRedirection).build();

        } catch (SQLException ex) {
        	Logger lgr = Logger.getLogger(Personalities.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			targetURIForRedirection = new URI("http://localhost:8000/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return Response.seeOther(targetURIForRedirection).build();
	}
}
