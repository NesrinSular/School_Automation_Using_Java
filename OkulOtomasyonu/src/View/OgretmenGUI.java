package View;

import java.awt.*;
import java.awt.event.*;

import java.awt.Graphics2D;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import Helper.*;

public class OgretmenGUI extends JFrame {

	static Ogretmen ogretmen = new Ogretmen();
	Dersler dersler = new Dersler();
	private JPanel w_pane;
	private JTextField fld_dOgrtAdSoyad;
	private JTextField fld_dOgrtTC;
	private JPasswordField fld_dOgrtPass;
	private JTextField fld_dOgrtId;
	private JTable table_Ogrt;
	private DefaultTableModel ogrtModel = null;
	private Object[] ogrtData = null;
	private JTextField fld_dOgrtUnv;
	private JTable table_Dersler;
	private JTextField fld_DersAd;
	private DefaultTableModel dersModel = null;
	private Object[] derslerData = null;
	private JPopupMenu derslerMenu;
	private JTable table_ogretmen;
	private JTextField fld_ara;

	/**
	 * Launch the application.DefaultTableModel
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OgretmenGUI frame = new OgretmenGUI(ogretmen);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public OgretmenGUI(Ogretmen ogretmen) throws SQLException {
		setTitle("Öðretmen Yönetim Sistemi");
		// ogretmen model
		ogrtModel = new DefaultTableModel();
		Object[] cologrtName = new Object[5];
		cologrtName[0] = "ID";
		cologrtName[1] = "AD SOYAD";
		cologrtName[2] = "TC NO";
		cologrtName[3] = "ÞÝFRE";
		cologrtName[4] = "UNVAN";
		ogrtModel.setColumnIdentifiers(cologrtName);
		ogrtData = new Object[5];
		for (int i = 0; i < ogretmen.getOgrtList().size(); i++) {

			ogrtData[0] = ogretmen.getOgrtList().get(i).getID();
			ogrtData[1] = ogretmen.getOgrtList().get(i).getOgrtAdSoyad();
			ogrtData[2] = ogretmen.getOgrtList().get(i).getOgrtTC();
			ogrtData[3] = ogretmen.getOgrtList().get(i).getOgrtPass();
			ogrtData[4] = ogretmen.getOgrtList().get(i).getUnvan();
			ogrtModel.addRow(ogrtData);

		}
		// Dersler Data
		dersModel = new DefaultTableModel();
		Object[] colDersler = new Object[2];
		colDersler[0] = "ID";
		colDersler[1] = "Ders Adý";
		dersModel.setColumnIdentifiers(colDersler);
		derslerData = new Object[2];
		for (int i = 0; i < dersler.getList().size(); i++) {
			derslerData[0] = dersler.getList().get(i).getID();
			derslerData[1] = dersler.getList().get(i).getDersAd();
			dersModel.addRow(derslerData);
		}
		
		//ogretmen Model
		DefaultTableModel ogrenciModel = new DefaultTableModel();
		Object[] colOgrenci = new Object[2];
		colOgrenci[0]="ID";
		colOgrenci[1]="Ad Soyad";
		ogrenciModel.setColumnIdentifiers(colOgrenci);
		Object[] ogrenciData = new Object[2];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + ogretmen.getOgrtAdSoyad());
		lblNewLabel.setBounds(20, 18, 448, 30);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginGUI GUI = new LoginGUI();
				GUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(606, 22, 108, 29);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_pane.add(btnNewButton);

		JTabbedPane btn_ara = new JTabbedPane(JTabbedPane.TOP);
		btn_ara.setBounds(10, 77, 716, 376);
		w_pane.add(btn_ara);

		JPanel w_ogrtpanel = new JPanel();
		w_ogrtpanel.setBackground(Color.WHITE);
		btn_ara.addTab("Öðretmen Paneli", null, w_ogrtpanel, null);
		w_ogrtpanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(530, 10, 171, 26);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		w_ogrtpanel.add(lblNewLabel_1);

		fld_dOgrtAdSoyad = new JTextField();
		fld_dOgrtAdSoyad.setBounds(530, 38, 171, 26);
		fld_dOgrtAdSoyad.setForeground(Color.BLACK);
		fld_dOgrtAdSoyad.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_dOgrtAdSoyad.setBackground(new Color(255, 255, 255));
		w_ogrtpanel.add(fld_dOgrtAdSoyad);
		fld_dOgrtAdSoyad.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setBounds(530, 61, 171, 26);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		w_ogrtpanel.add(lblNewLabel_1_1);

		fld_dOgrtTC = new JTextField();
		fld_dOgrtTC.setBounds(530, 85, 171, 26);
		fld_dOgrtTC.setForeground(Color.BLACK);
		fld_dOgrtTC.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_dOgrtTC.setColumns(10);
		fld_dOgrtTC.setBackground(Color.WHITE);
		w_ogrtpanel.add(fld_dOgrtTC);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setBounds(530, 108, 171, 26);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		w_ogrtpanel.add(lblNewLabel_1_1_1);

		fld_dOgrtPass = new JPasswordField();
		fld_dOgrtPass.setBounds(530, 131, 171, 26);
		fld_dOgrtPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		w_ogrtpanel.add(fld_dOgrtPass);

		JButton btn_addogrenci = new JButton("Ekle");
		btn_addogrenci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fld_dOgrtAdSoyad.getText().length() == 0 || fld_dOgrtTC.getText().length() == 0 || fld_dOgrtPass.getText().length() == 0 || fld_dOgrtUnv.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = ogretmen.addOgrt(fld_dOgrtAdSoyad.getText(), fld_dOgrtTC.getText(),fld_dOgrtPass.getText(), fld_dOgrtUnv.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dOgrtAdSoyad.setText(null);
							fld_dOgrtTC.setText(null);
							fld_dOgrtPass.setText(null);
							fld_dOgrtUnv.setText(null);
							updateProfModel();

						}
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				}

			}
		});
		btn_addogrenci.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addogrenci.setBounds(530, 208, 171, 32);
		w_ogrtpanel.add(btn_addogrenci);

		JLabel lblNewLabel_2 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_2.setBounds(530, 250, 171, 21);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_ogrtpanel.add(lblNewLabel_2);

		fld_dOgrtId = new JTextField();
		fld_dOgrtId.setBounds(530, 275, 171, 26);
		w_ogrtpanel.add(fld_dOgrtId);
		fld_dOgrtId.setColumns(10);

		JButton btn_delete = new JButton("Sil");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fld_dOgrtId.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir  Öðretmen Seçiniz !");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_dOgrtId.getText());
						try {
							boolean control = ogretmen.deleteOgrt(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_dOgrtId.setText(null);
								updateProfModel();

							}
						} catch (SQLException e) {
			
							e.printStackTrace();
						}
					}
				}
			}
		});
		btn_delete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_delete.setBounds(530, 306, 171, 33);
		w_ogrtpanel.add(btn_delete);

		JScrollPane w_scroolOgrt = new JScrollPane();
		w_scroolOgrt.setBounds(10, 10, 512, 329);
		w_ogrtpanel.add(w_scroolOgrt);

		table_Ogrt = new JTable(ogrtModel);
		w_scroolOgrt.setViewportView(table_Ogrt);
		table_Ogrt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			// MADDE 10: Method Overriding:  Ýnterface de bulunan metodlarýn class ýcerýsýnde yenýden yaratýlmasý olayýdýr.
			@Override
			
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					fld_dOgrtId.setText(table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}

		});

		table_Ogrt.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 0).toString());
					String selectName = table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 1).toString();
					String selectTcno = table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 2).toString();
					String selectPass = table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 3).toString();
					String selectUnv = table_Ogrt.getValueAt(table_Ogrt.getSelectedRow(), 4).toString();

					try {
						boolean control = ogretmen.updateOgrt(selectID, selectName, selectTcno, selectPass, selectUnv);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_ogretmendersler = new JPanel();
		w_ogretmendersler.setBackground(Color.WHITE);
		btn_ara.addTab("Dersler", null, w_ogretmendersler, null);
		w_ogretmendersler.setLayout(null);

		JScrollPane w_scrollDersler = new JScrollPane();
		w_scrollDersler.setBounds(10, 10, 260, 329);
		w_ogretmendersler.add(w_scrollDersler);

		derslerMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		derslerMenu.add(updateMenu);
		derslerMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			
			// MADDE 10: Method Overriding:  Ýnterface de bulunan metodlarýn class ýcerýsýnde yenýden yaratýlmasý olayýdýr.
			@Override
			
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_Dersler.getValueAt(table_Dersler.getSelectedRow(), 0).toString());
				Dersler selectDersler = dersler.getFetch(selID);
				UpdateDerslerGUI updateGUI = new UpdateDerslerGUI(selectDersler);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateDerslerModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {
			
			// MADDE 10: Method Overriding:  Ýnterface de bulunan metodlarýn class ýcerýsýnde yenýden yaratýlmasý olayýdýr.
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_Dersler.getValueAt(table_Dersler.getSelectedRow(), 0).toString());
					try {
						if (dersler.deleteDersler(selID)) {
							Helper.showMsg("success");
							updateDerslerModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		table_Dersler = new JTable(dersModel);
		table_Dersler.setComponentPopupMenu(derslerMenu);
		table_Dersler.addMouseListener((MouseListener) new MouseAdapter() {
			
			// MADDE 10: Method Overriding:  Ýnterface de bulunan metodlarýn class ýcerýsýnde yenýden yaratýlmasý olayýdýr.
			@Override
			
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_Dersler.rowAtPoint(point);
				table_Dersler.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		w_scrollDersler.setViewportView(table_Dersler);

		JLabel lblNewLabel_1_2 = new JLabel("Ders Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(280, 10, 171, 26);
		w_ogretmendersler.add(lblNewLabel_1_2);

		fld_DersAd = new JTextField();
		fld_DersAd.setForeground(Color.BLACK);
		fld_DersAd.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_DersAd.setColumns(10);
		fld_DersAd.setBackground(Color.WHITE);
		fld_DersAd.setBounds(280, 38, 159, 26);
		w_ogretmendersler.add(fld_DersAd);

		JButton btnNewButton_1_1 = new JButton("Ekle");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_DersAd.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (dersler.addDersler(fld_DersAd.getText())) {
							Helper.showMsg("success");
							fld_DersAd.setText(null);
							updateDerslerModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(280, 74, 159, 32);
		w_ogretmendersler.add(btnNewButton_1_1);

		JScrollPane w_scroologretmen = new JScrollPane();
		w_scroologretmen.setBounds(445, 10, 256, 329);
		w_ogretmendersler.add(w_scroologretmen);
		
		table_ogretmen = new JTable();
		w_scroologretmen.setViewportView(table_ogretmen);

		JComboBox select_dersler = new JComboBox();
		select_dersler.setBounds(280, 258, 159, 39);

		for (int i = 0; i < ogretmen.getOgrtList().size(); i++) {
			select_dersler.addItem(new Item(ogretmen.getOgrtList().get(i).getID(), ogretmen.getOgrtList().get(i).getOgrtAdSoyad()));
		}
		select_dersler.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_ogretmendersler.add(select_dersler);

		JButton btn_addOgretmen = new JButton("Ekle");
		btn_addOgretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow = table_Dersler.getSelectedRow();
				if (selRow >= 0) {
					String selDersler = table_Dersler.getModel().getValueAt(selRow, 0).toString();
					int selDerslerID = Integer.parseInt(selDersler);
					Item ogrtItem = (Item) select_dersler.getSelectedItem();
					try {
						boolean control = ogretmen.addDersOgrt(ogrtItem.getKey(), selDerslerID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_ogretmen.getModel();
							clearModel.setRowCount(0);
							for(int i = 0 ; i < ogretmen.getDersOgrtList(selDerslerID).size();i++) {
								ogrenciData[0]=ogretmen.getDersOgrtList(selDerslerID).get(i).getID();
								ogrenciData[1]=ogretmen.getDersOgrtList(selDerslerID).get(i).getOgrtAdSoyad();
								ogrenciModel.addRow(ogrenciData);
								}
							table_ogretmen.setModel(ogrenciModel);
							
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir ders seçiniz ! ");
				}
			}
		});
		btn_addOgretmen.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addOgretmen.setBounds(280, 307, 159, 32);
		w_ogretmendersler.add(btn_addOgretmen);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Ders Ad\u0131");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(280, 132, 159, 26);
		w_ogretmendersler.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Seç");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow = table_Dersler.getSelectedRow();
				if(selRow >= 0) {
					String selDersler = table_Dersler.getModel().getValueAt(selRow, 0).toString();
					int selDerslerID = Integer.parseInt(selDersler);
					DefaultTableModel clearModel = (DefaultTableModel) table_ogretmen.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0 ; i < ogretmen.getDersOgrtList(selDerslerID).size();i++) {
						ogrenciData[0]=ogretmen.getDersOgrtList(selDerslerID).get(i).getID();
						ogrenciData[1]=ogretmen.getDersOgrtList(selDerslerID).get(i).getOgrtAdSoyad();
						ogrenciModel.addRow(ogrenciData);
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_ogretmen.setModel(ogrenciModel);
				}else {
					Helper.showMsg("Lütfen bir ders seçiniz.");
				}
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton_1_1_1.setBounds(280, 159, 159, 32);
		w_ogretmendersler.add(btnNewButton_1_1_1);

		JLabel lblNewLabel_3 = new JLabel("Unvan");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(530, 156, 171, 21);
		w_ogrtpanel.add(lblNewLabel_3);

		fld_dOgrtUnv = new JTextField();
		fld_dOgrtUnv.setBounds(530, 177, 171, 26);
		w_ogrtpanel.add(fld_dOgrtUnv);
		fld_dOgrtUnv.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Ara");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// MADDE 2: ARAMA
				DBConnection conn = new DBConnection();
				Connection con = conn.connDB();
				Statement st = null;
				ResultSet rs = null;
				PreparedStatement ps = null;
			
				if (fld_ara.getText().length() == 0 || fld_ara.getText().length() == 0) {
					Helper.showMsg("fill");

				}
				else {
			
					try {

						st = con.createStatement();
						rs = st.executeQuery("SELECT * FROM Ogretmen");
						while (rs.next()) {
							if (fld_ara.getText().equals(rs.getString("OgrtAdSoyad"))) {
								Helper.showMsg("Kullanici bulundu !");
								
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
				
				
		
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnNewButton_1.setBounds(606, 61, 108, 29);
		w_pane.add(btnNewButton_1);
		
		fld_ara = new JTextField();
		fld_ara.setForeground(Color.BLACK);
		fld_ara.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_ara.setColumns(10);
		fld_ara.setBackground(Color.WHITE);
		fld_ara.setBounds(424, 63, 171, 27);
		w_pane.add(fld_ara);

	}

	public void updateProfModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_Ogrt.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < ogretmen.getOgrtList().size(); i++) {

			ogrtData[0] = ogretmen.getOgrtList().get(i).getID();
			ogrtData[1] = ogretmen.getOgrtList().get(i).getOgrtAdSoyad();
			ogrtData[2] = ogretmen.getOgrtList().get(i).getOgrtTC();
			ogrtData[3] = ogretmen.getOgrtList().get(i).getOgrtPass();
			ogrtData[4] = ogretmen.getOgrtList().get(i).getUnvan();
			ogrtModel.addRow(ogrtData);

		}
	}

	public void updateDerslerModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_Dersler.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < dersler.getList().size(); i++) {
			derslerData[0] = dersler.getList().get(i).getID();
			derslerData[1] = dersler.getList().get(i).getDersAd();
			dersModel.addRow(derslerData);
		}
	}
}
