package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.DBConnection;

public class Ogretmen implements OgretmenImp {

	//MADDE 2: ÝNTERFACE'DEN CLASS'A ÝMPLEMENT ÝÞLEMÝ
	
	DBConnection conn = new DBConnection();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	private int ID;
	String OgrtAdSoyad, OgrtTC, OgrtPass, Unvan;

	public Ogretmen(int iD, String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan) {
		
		// MADDE 6: YAPICI METHOD: KULLANILAN DEÐÝÞKENLERE ÝLK DEÐER ATAMASINI YAPAN YAPICI METHODUMUZ.
		
		super();
		ID = iD;
		OgrtAdSoyad = ogrtAdSoyad;
		OgrtTC = ogrtTC;
		OgrtPass = ogrtPass;
		Unvan = unvan;
		
	}
	
	public Ogretmen(){
		
		// MADDE 8: METHOD OVERLOADING
		
	}

		// MADDE 4: PRIVATE TANIMLI DEÐÝÞKENLER
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getOgrtAdSoyad() {
		return OgrtAdSoyad;
	}

	public void setOgrtAdSoyad(String ogrtAdSoyad) {
		OgrtAdSoyad = ogrtAdSoyad;
	}

	public String getOgrtTC() {
		return OgrtTC;
	}

	public void setOgrtTC(String ogrtTC) {
		OgrtTC = ogrtTC;
	}

	public String getOgrtPass() {
		return OgrtPass;
	}

	public void setOgrtPass(String ogrtPass) {
		OgrtPass = ogrtPass;
	}

	public String getUnvan() {
		return Unvan;
	}

	public void setUnvan(String unvan) {
		Unvan = unvan;
	}

	//MADDE 2: LÝSTELEME
	public ArrayList<Ogretmen> getOgrtList() throws SQLException {
		
		//MADDE 2: LÝSTELEME
		ArrayList<Ogretmen> list = new ArrayList<>();
		Ogretmen obj;
		Connection con = conn.connDB();
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

	public ArrayList<Ogretmen> getDersOgrtList(int DersID) throws SQLException {
		
		//MADDE 2: LÝSTELEME
		ArrayList<Ogretmen> list = new ArrayList<>();
		Ogretmen obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.iD,u.ogrtAdSoyad,u.ogrtTC,u.ogrtPass,u.unvan FROM ogretmenders o LEFT JOIN Ogretmen u ON o.OgretmenID = u.iD WHERE DerslerID = " + DersID);
			while (rs.next()) {
				obj = new Ogretmen(rs.getInt("u.iD"), rs.getString("u.ogrtAdSoyad"), rs.getString("u.ogrtTC"),rs.getString("u.ogrtPass"), rs.getString("u.unvan"));
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

	public boolean addOgrt(String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan) throws SQLException {
		
		//MADDE 2: EKLEME
		Connection con = conn.connDB();
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

	public boolean deleteOgrt(int iD) throws SQLException {
		
		//MADDE 2: SÝLME
		Connection con = conn.connDB();
		String query = "DELETE FROM Ogretmen WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, iD);
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

	public boolean updateOgrt(int ID, String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan)
			throws SQLException {
		
		//MADDE 2: GÜNCELLEME
		Connection con = conn.connDB();
		String query = "UPDATE Ogretmen SET ogrtAdSoyad = ?, ogrtTC = ?, ogrtPass = ?, unvan = ? WHERE ID = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, ogrtAdSoyad);
			ps.setString(2, ogrtTC);
			ps.setString(3, ogrtPass);
			ps.setString(4, unvan);
			ps.setInt(5, ID);
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

	public boolean addDersOgrt(int OgretmenID, int DerslerID) throws SQLException {
		
		//MADDE 2: EKLEME
		Connection con = conn.connDB();
		String query = "INSERT INTO ogretmenders" + "(OgretmenID,DerslerID) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM ogretmenders WHERE DerslerID=" + DerslerID + " AND OgretmenID=" + OgretmenID);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, OgretmenID);
				ps.setInt(2, DerslerID);
				ps.executeUpdate();
			}

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
