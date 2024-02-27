package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.DBConnection;

public class Ogrenci implements OgrenciImp{

	//MADDE 2: ÝNTERFACE'DEN CLASS'A ÝMPLEMENT ÝÞLEMÝ
	
	DBConnection conn = new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement ps=null; 

	 private int Id;
	 private String Tcno,Isim,Password,Type;
	  
		public Ogrenci(int id, String tcno, String isim, String password, String type) {
			
			// MADDE 6: YAPICI METHOD: KULLANILAN DEÐÝÞKENLERE ÝLK DEÐER ATAMASINI YAPAN YAPICI METHODUMUZ.

			super();
			Id = id;
			Tcno = tcno;
			Isim = isim;
			Password = password;
			Type = type;
		}
		
		public Ogrenci()
		{
			
			// MADDE 8: METHOD OVERLOADING
			
		}
		
		// MADDE 4: PRIVATE TANIMLI DEÐÝÞKENLER GET-SET ÝÞLEMLERÝ
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			this.Id = id;
		}
		public String getTcno() {
			return Tcno;
		}
		public void setTcno(String tcno) {
			this.Tcno = tcno;
		}
		public String getIsim() {
			return Isim;
		}
		public void setIsim(String isim) {
			this.Isim = isim;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			this.Password = password;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			this.Type = type;
		}


public ArrayList<Ogrenci> getOgrenciList() throws SQLException
{
	
	//MADDE 2: LÝSTELEME
	
	ArrayList<Ogrenci> list=new ArrayList<>();
	Ogrenci obj;
	Connection con = conn.connDB();
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM ogrenci WHERE type='normalogr'|| type = 'ikinciogr'");
		while(rs.next()) {
			obj=new Ogrenci(rs.getInt("id"),rs.getString("tcno"),rs.getString("isim"),rs.getString("type"),rs.getString("password"));
			list.add(obj);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		finally {
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

public ArrayList<Ogrenci> getDersOgrncList(int DersID) throws SQLException {
	
	//MADDE 2: LÝSTELEME
	
	ArrayList<Ogrenci> list = new ArrayList<>();
	Ogrenci obj;
	Connection con = conn.connDB();
	try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT u.id,u.tcno,u.isim,u.type,u.password FROM ogrenciders o LEFT JOIN Ogrenci u ON o.OgrenciID = u.iD WHERE DerslerID = " + DersID);
		while (rs.next()) {
			obj = new Ogrenci(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.isim"),rs.getString("u.type"), rs.getString("u.password"));
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



//öðrenci ekleme methodu
public boolean addogrenci(String tcno, String isim , String type ,String password) throws SQLException
{  
	//MADDE 2: EKLEME
	
	Connection con = conn.connDB();
	String query="INSERT INTO ogrenci "+"(tcno,isim,type,password) VALUES " +"(?,?,?,?)";
	 boolean key=false;
	try {
		 st=con.createStatement();
		 ps =con.prepareStatement(query);
		 ps.setString(1, tcno);
		 ps.setString(2, isim);
		 ps.setString(3, type);
		 ps.setString(4, password);
		 ps.executeUpdate();
			key=true;
	} catch (Exception e) {
		e.printStackTrace();
	}		finally {
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
	if(key)
	return true;
	else
	return false;
 }

public boolean deleteogrenci(int id) throws SQLException
{   
	//MADDE 2: SÝLME
	
	Connection con = conn.connDB();
	String query="DELETE FROM ogrenci WHERE id=?";
	 boolean key=false;
	try {
		 st=con.createStatement();
		 ps =con.prepareStatement(query);
		 ps.setInt(1,id);
		 ps.executeUpdate();
			key=true;
	} catch (Exception e) {
		e.printStackTrace();
	}		finally {
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
	if(key)
	return true;
	else
	return false;
 }
public boolean updateogrenci(int id,String tcno,String isim,String type,String password) throws SQLException
{   
	//MADDE 2: GÜNCELLEME
	
	Connection con = conn.connDB();
	String query="UPDATE ogrenci SET tcno =?,isim=?,type=?,password=? WHERE id=?";
	 boolean key=false;
	try {
		 st=con.createStatement();
			ps =con.prepareStatement(query);
			ps.setString(1, isim);
			ps.setString(2, tcno);
			ps.setString(3, password);
			ps.setString(4, type);
			ps.setInt(5, id);
			ps.executeUpdate();
			key=true;
	} catch (Exception e) {
		e.printStackTrace();
	}		finally {
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
	if(key)
	return true;
	else
	return false;
 }

public boolean addDersOgrnc(int OgrenciID, int DerslerID) throws SQLException {
	
	//MADDE 2: EKLEME
	
	Connection con = conn.connDB();
	String query = "INSERT INTO ogrenciders" + "(OgrenciID,DerslerID) VALUES" + "(?,?)";
	boolean key = false;
	int count = 0;
	try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM ogrenciders WHERE DerslerID=" + DerslerID + " AND OgrenciID=" + OgrenciID);
		while (rs.next()) {
			count++;
		}
		if (count == 0) {
			ps = con.prepareStatement(query);
			ps.setInt(1, OgrenciID);
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
