package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OgretmenImp {


	// MADDE 1 �NTERFACE
	// MADDE 3 EKLE,L�STELE, S�L, G�NCELLE VE ARAMA MOTHODLARININ G�VDELER� OLU�TURULDU 
	
	public ArrayList<Ogretmen> getOgrtList() throws SQLException;
	public ArrayList<Ogretmen> getDersOgrtList(int DersID) throws SQLException;
	public boolean addOgrt(String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan) throws SQLException;
	public boolean deleteOgrt(int iD) throws SQLException;
	public boolean updateOgrt(int ID, String ogrtAdSoyad, String ogrtTC, String ogrtPass, String unvan) throws SQLException;
	public boolean addDersOgrt(int UserID, int DerslerID) throws SQLException;
	
}
