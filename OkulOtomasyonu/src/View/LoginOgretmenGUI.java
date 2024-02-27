package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Ogretmen;

public class LoginOgretmenGUI extends JFrame {

	private JPanel w_pane;
	/**
	 * @wbp.nonvisual location=147,-16
	 */
	
	private final JScrollPane scrollPane = new JScrollPane();
	private JPasswordField fld_OgrtPass;
	private JTextField fld_OgrtTC;
	private DBConnection conn = new DBConnection();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOgretmenGUI frame = new LoginOgretmenGUI();
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
	public LoginOgretmenGUI() {
		setResizable(false);
		setTitle("Okul Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl = new JLabel("Okul Bilgi Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lbl.setBounds(37, 45, 421, 30);
		lbl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		w_pane.add(lbl);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 325, 447, -160);
		w_pane.add(tabbedPane);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(25, 105, 447, 225);
		w_pane.add(w_tabpane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Öðretmen Giriþi", null, panel, null);
		panel.setLayout(null);

		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131: ");
		lblKullancAd.setBounds(32, 27, 139, 30);
		lblKullancAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		panel.add(lblKullancAd);

		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(32, 79, 139, 30);
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		panel.add(lblifre);

		JButton btn_OgrtLogin = new JButton(" Giris Yap");
		btn_OgrtLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = conn.connDB();
				if (fld_OgrtTC.getText().length() == 0 || fld_OgrtPass.getText().length() == 0) {
					Helper.showMsg("fill");

				}
				else {
			
					try {

						st = con.createStatement();
						rs = st.executeQuery("SELECT * FROM Ogretmen");
						while (rs.next()) {
							if (fld_OgrtTC.getText().equals(rs.getString("OgrtTC"))&& fld_OgrtPass.getText().equals(rs.getString("OgrtPass"))) {

								Ogretmen ogret = new Ogretmen();
								ogret.setID(rs.getInt("ID"));
								ogret.setOgrtPass("ogrtPass");
								ogret.setOgrtAdSoyad(rs.getString("ogrtAdSoyad"));
								ogret.setOgrtTC(rs.getString("ogrtTC"));
								ogret.setUnvan(rs.getString("unvan"));
								OgretmenGUI oGUI = new OgretmenGUI(ogret);
								oGUI.setVisible(true);
								dispose();
							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
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
		btn_OgrtLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_OgrtLogin.setBounds(32, 144, 372, 44);
		panel.add(btn_OgrtLogin);

		fld_OgrtPass = new JPasswordField();
		fld_OgrtPass.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		fld_OgrtPass.setBounds(182, 79, 222, 30);
		panel.add(fld_OgrtPass);

		fld_OgrtTC = new JTextField();
		fld_OgrtTC.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_OgrtTC.setBounds(185, 26, 219, 31);
		panel.add(fld_OgrtTC);
		fld_OgrtTC.setColumns(10);
	}
}
