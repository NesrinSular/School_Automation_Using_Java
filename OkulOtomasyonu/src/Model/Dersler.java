package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Dersler {
	private int ID;
	private String DersAd;
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDersAd() {
		return DersAd;
	}

	public void setDersAd(String dersAd) {
		DersAd = dersAd;
	}
	DBConnection conn = new DBConnection();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Dersler() {
	}

	public Dersler(int iD, String dersAd) {
		super();
		ID = iD;
		DersAd = dersAd;
	}

	public ArrayList<Dersler> getList() throws SQLException {
		ArrayList<Dersler> list = new ArrayList<>();
		Dersler obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM dersler");

			while (rs.next()) {

				obj = new Dersler();
				obj.setID(rs.getInt("ID"));
				obj.setDersAd(rs.getString("DersAd"));
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

	public Dersler getFetch(int id) {
		Dersler d = new Dersler();
		Connection con = conn.connDB();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM dersler WHERE ID=" + id);
			while (rs.next()) {
				d.setID(rs.getInt("ID"));
				d.setDersAd(rs.getString("DersAd"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		return d;
	}

	public boolean addDersler(String DersAd) throws SQLException {
		Connection con = conn.connDB();
		String query = "INSERT INTO dersler" + "(DersAd) VALUES" + "(?)";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, DersAd);
			ps.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
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

	public boolean deleteDersler(int iD) throws SQLException {
		Connection con = conn.connDB();
		String query = "DELETE FROM dersler WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, iD);
			ps.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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

	public boolean updateDersler(int ID, String DersAd) throws SQLException {
		Connection con = conn.connDB();
		String query = "UPDATE dersler SET DersAd = ? WHERE ID = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, DersAd);
			ps.setInt(2, ID);
			ps.executeUpdate();
			key = true;

		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
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
