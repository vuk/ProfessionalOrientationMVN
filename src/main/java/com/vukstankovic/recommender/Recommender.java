package com.vukstankovic.recommender;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vukstankovic.professionalorientation.Personalities;
import com.vukstankovic.professionalorientation.classes.User;
import com.vukstankovic.professionalorientation.config.DBConnect;
import com.vukstankovic.professionalorientation.helpers.UserComparator;

public class Recommender {
	
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs;
    private SimilarityCalc sc = new SimilarityCalc();
    private DBConnect db = new DBConnect();
	private ArrayList<User> similarUsers;
	private ArrayList<User> allUsers;
	private User currentUser = new User();
	
	public Connection getCon() {
		return con;
	}

	public Statement getSt() {
		return st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public SimilarityCalc getSc() {
		return sc;
	}

	public DBConnect getDb() {
		return db;
	}

	public ArrayList<User> getSimilarUsers() {
		return similarUsers;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setSc(SimilarityCalc sc) {
		this.sc = sc;
	}

	public void setDb(DBConnect db) {
		this.db = db;
	}

	public void setSimilarUsers(ArrayList<User> similarUsers) {
		this.similarUsers = similarUsers;
	}

	public void setAllUsers(ArrayList<User> allUsers) {
		this.allUsers = allUsers;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public void getAllUsers(){
		try {
			allUsers = new ArrayList<User>();
        	Class.forName("com.mysql.jdbc.Driver");
        	con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM users WHERE poljoprivreda != 'NULL'");
            
            while (rs.next()) {
            	User u = new User();
            	u.setId(rs.getInt(1));
            	u.setFirstName(rs.getString(2));
            	u.setLastName(rs.getString(3));
            	//u.setEmail(rs.getString(4));
            	u.setSchoolType(rs.getInt(5));
            	u.setHighSchool(rs.getString(6));
            	u.setYearOfBirth(rs.getInt(7));
            	u.setGender(rs.getInt(8));
            	u.setCurrentTown(rs.getInt(9));
            	u.setPoljoprivreda(rs.getDouble(10));
            	u.setSumarstvo(rs.getDouble(11));
            	u.setGeodezija(rs.getDouble(12));
            	u.setMasinstvo(rs.getDouble(13));
            	u.setElektrotehnika(rs.getDouble(14));
            	u.setHemija(rs.getDouble(15));
            	u.setTekstil(rs.getDouble(16));
            	u.setSaobracaj(rs.getDouble(17));
            	u.setTrgovinaugostiteljstvo(rs.getDouble(18));
            	u.setEkonomijapravo(rs.getDouble(19));
            	u.setHidrometeorologija(rs.getDouble(20));
            	u.setKultura(rs.getDouble(21));
            	u.setZdravstvo(rs.getDouble(22));
            	u.setUsluge(rs.getDouble(23));
            	// don't allow current user in all users list
            	if(currentUser.getId() != u.getId())
            		allUsers.add(u);
            }
            rs.close();

        } catch (SQLException ex) {
        	Logger lgr = Logger.getLogger(Personalities.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
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
	}
	
	public void getCurrentUser(int id){
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE id = "+id);
			rs.next();
			User u = new User();
        	u.setId(rs.getInt(1));
        	u.setFirstName(rs.getString(2));
        	u.setLastName(rs.getString(3));
        	u.setEmail(rs.getString(4));
        	u.setSchoolType(rs.getInt(5));
        	u.setHighSchool(rs.getString(6));
        	u.setYearOfBirth(rs.getInt(7));
        	u.setGender(rs.getInt(8));
        	u.setCurrentTown(rs.getInt(9));
        	u.setPoljoprivreda(rs.getDouble(10));
        	u.setSumarstvo(rs.getDouble(11));
        	u.setGeodezija(rs.getDouble(12));
        	u.setMasinstvo(rs.getDouble(13));
        	u.setElektrotehnika(rs.getDouble(14));
        	u.setHemija(rs.getDouble(15));
        	u.setTekstil(rs.getDouble(16));
        	u.setSaobracaj(rs.getDouble(17));
        	u.setTrgovinaugostiteljstvo(rs.getDouble(18));
        	u.setEkonomijapravo(rs.getDouble(19));
        	u.setHidrometeorologija(rs.getDouble(20));
        	u.setKultura(rs.getDouble(21));
        	u.setZdravstvo(rs.getDouble(22));
        	u.setUsluge(rs.getDouble(23));
        	currentUser = u;
        	rs.close();
        	
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
        
	}
	
	public void calculateSimilarity(int id){
		similarUsers = new ArrayList<User>();
		ArrayList<User> helper = new ArrayList<User>();
		getCurrentUser(id);
		getAllUsers();
		Iterator<User> it = allUsers.iterator();
    	while(it.hasNext())
    	{
    	    User user = it.next();
    	    user.setSimilarity(sc.similarity(currentUser, user));
    	    helper.add(user);
    	}
    	Collections.sort(helper, new UserComparator());
    	Collections.reverse(helper);

    	Iterator<User> iterator = helper.iterator();
    	int i = 0;
    	while(iterator.hasNext()){
    		User curr = iterator.next();
    		if(i < 20 || curr.getSimilarity() >= 0.7){
    			curr.getDBChoices();
    			similarUsers.add(curr);
    			i++;
    		}
    	}
	}

}
