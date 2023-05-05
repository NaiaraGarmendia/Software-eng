package managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public void addUser(String name, String surname, String userName, String mail, String pwd,String degree) {
		String query = "INSERT INTO users (usr,mail,pwd) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, surname);
			statement.setString(3,userName);
			statement.setString(4,mail);
			statement.setString(5,pwd);
			statement.setString(6,degree);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getName()) &&
	    	   hasValue(user.getSurname()) &&
	    	   hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) &&
	           hasValue(user.getPwd2())&&
	           hasValue(user.getDegree()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods 

}
