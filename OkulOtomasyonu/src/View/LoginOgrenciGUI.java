package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Ogrenci;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginOgrenciGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_ogrtc;
	private DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	private JPasswordField fld_ogrpass;
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOgrenciGUI frame = new LoginOgrenciGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public LoginOgrenciGUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 399);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("T.C. Numaran\u0131z:");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		label.setBounds(30, 150, 235, 39);
		w_pane.add(label);
		
		fld_ogrtc = new JTextField();
		fld_ogrtc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_ogrtc.setColumns(10);
		fld_ogrtc.setBounds(249, 157, 166, 26);
		w_pane.add(fld_ogrtc);
		
		JLabel label_1 = new JLabel("\u015Eifre:");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		label_1.setBounds(30, 218, 79, 29);
		w_pane.add(label_1);
		
		JLabel label_2 = new JLabel((Icon) null);
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		label_2.setBounds(196, 12, 155, 43);
		w_pane.add(label_2);
		
		JLabel lblOkulBilgiYnetim = new JLabel("Okul Bilgi Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblOkulBilgiYnetim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblOkulBilgiYnetim.setBounds(57, 43, 381, 43);
		w_pane.add(lblOkulBilgiYnetim);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(12, 117, 478, 236);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Öðrenci Giriþ Sistemi", null, panel, null);
		panel.setLayout(null);
		
		fld_ogrpass = new JPasswordField();
		fld_ogrpass.setBounds(233, 78, 171, 33);
		panel.add(fld_ogrpass);
		
		JButton btn_ogrgiris = new JButton("Giri\u015F Yap");
		btn_ogrgiris.setBounds(31, 145, 362, 45);
		panel.add(btn_ogrgiris);
		btn_ogrgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_ogrtc.getText().length()==0 || fld_ogrpass.getText().length()==0) {
		
					Helper.showMsg("fill");
			}else {
				Connection con = conn.connDB();
	           try {
					st = con.createStatement();
					rs = st.executeQuery("SELECT * FROM ogrenci");
	        	   while(rs.next())
	        	{
					if(fld_ogrtc.getText().equals(rs.getString("tcno"))&& fld_ogrpass.getText().equals(rs.getString("password"))) {
					Ogrenci ogrenci=new Ogrenci();
					ogrenci.setId(rs.getInt("id"));
					ogrenci.setTcno("tcno");
					ogrenci.setIsim(rs.getString("isim"));
					ogrenci.setType(rs.getString("type"));
					ogrenci.setPassword(rs.getString("password"));
				  OgrenciGUI oGUI=new OgrenciGUI(ogrenci);
				  oGUI.setVisible(true);
				   dispose();
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//Girilen deðerin db varolup olmadýðýný kontrol edicem
	           finally {
				    if (rs != null) {
				        try {
				            rs.close();
				        } catch (SQLException ex) { /* ignored */}
				    }
				    if (st != null) {
				        try {
				            st.close();
				        } catch (SQLException ex) { /* ignored */}
				    }
				    if (ps != null) {
				        try {
				            ps.close();
				        } catch (SQLException ex) { /* ignored */}
				    }
				    if (con != null) {
				        try {
				            con.close();
				        } catch (SQLException ex) { /* ignored */}
				    }
				}
			}
			}
		});
		btn_ogrgiris.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
	}
}
