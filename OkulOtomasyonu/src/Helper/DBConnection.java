package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	Connection c= null; 
	public DBConnection() {	
	}
	public Connection connDB() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/okulveri?user=root");
			return c;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return c;
	}


}
