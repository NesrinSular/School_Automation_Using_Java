package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public interface InterAraList {

	// MADDE 1 ÝNTERFACE

	DBConnection conn = new DBConnection();
	public default ArrayList<Ogretmen> getOgretmenList() throws SQLException {
		ArrayList<Ogretmen> list = new ArrayList<>();
		Ogretmen obj;
		Connection con = conn.connDB();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM Ogretmen WHERE Unvan = 'Docent' || Unvan = 'Ogretim Gorevlisi' || Unvan = 'Profesor'");
			while (rs.next()) {
				obj = new Ogretmen(rs.getInt("iD"), rs.getString("ogrtAdSoyad"), rs.getString("ogrtTC"),rs.getString("ogrtPass"), rs.getString("unvan"));
				list.add(obj);
			}
		} catch (SQLException e) {
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
		return list;

	}
	
	
}
