package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.DBConnection;

public interface InterEkleSilGuncelle {
	
	// MADDE 1 ÝNTERFACE
	
	DBConnection conn = new DBConnection();

	public default boolean addOgretmen(String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan) throws SQLException {
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO Ogretmen" + "(OgrtAdSoyad,OgrtTC,OgrtPass,Unvan) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, ogrtAdSoyad);
			ps.setString(2, ogrtTC);
			ps.setString(3, ogrtPass);
			ps.setString(4, unvan);
			ps.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (st != null) {
		        try {
		            st.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}
		if (key)
			return true;
		else
			return false;
	}
	
	
	
}
