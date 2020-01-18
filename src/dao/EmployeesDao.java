package dao;

import java.sql.*;
import main.Controller;

public class EmployeesDao {
	
	private Controller ctrl;
	Connection conn = ConnectionFactory.getConnection();
	
	public EmployeesDao(Controller c) {
		ctrl = c;
	}
	
	//returns true if user is in database, false if not
	public boolean CheckLoginProps(String Username, String Password) {
	 PreparedStatement st;
	try {
		st = conn.prepareStatement(
				    "SELECT * from employees WHERE username = ? and password = ?");
			 st.setString(1, Username);
			 st.setString(2, Password);
		     ResultSet rs = st.executeQuery(); 
		     // if this account exist returns true else returns false 
		     if ( rs.next() ) {    	
		    	 return true;
		    	}
		     else 
		    	 return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	 

}
