package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OgrenciImp {
	
	// MADDE 1 �NTERFACE
	// MADDE 3 EKLE,L�STELE, S�L, G�NCELLE VE ARAMA MOTHODLARININ G�VDELER�
	
	public ArrayList<Ogrenci> getOgrenciList() throws SQLException;
	public boolean addogrenci(String tcno, String isim, String type ,String password ) throws SQLException;
	public boolean deleteogrenci(int id) throws SQLException;
	public boolean updateogrenci(int id,String tcno,String isim,String password,String type) throws SQLException;
	public ArrayList<Ogrenci> getDersOgrncList(int DersID) throws SQLException;

}
