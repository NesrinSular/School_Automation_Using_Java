package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Dersler;
import Model.Ogrenci;
import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

import Helper.*;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;


public class OgrenciGUI extends JFrame {
	static Ogrenci ogrenci = new Ogrenci();
  	Dersler dersler = new Dersler();
	private JPanel w_panel;
	private DefaultTableModel ogrenciModel=null;
	private Object[] ogrenciData=null;
	private JTextField fld_ogrenci_ad;
	private JTextField fld_ogrenci_tc;
	private JTextField fld_ogrenci_pass;
	private JTextField fld_ogrenci_id;
	private JTable table_ogrenci;
	private JTextField fld_ogrenci_type;
	private JTextField fld_dersad;
	private JTable table_dersler;
	private DefaultTableModel dersModel = null;
	private Object[] derslerData = null;
	private JPopupMenu derslerMenu;
	private JTable table_ogrnc;
	private JTextField fld_ara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OgrenciGUI frame = new OgrenciGUI(ogrenci);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public OgrenciGUI(Ogrenci ogrenci) throws SQLException {
		setResizable(false); 
		ogrenciModel =new DefaultTableModel();
		Object[] colOgrenciName= new Object[5];
		colOgrenciName[0]="ID";
		colOgrenciName[1]="Ad Soyad";
		colOgrenciName[2]="TC No";
		colOgrenciName[3]="Þifre";
		colOgrenciName[4]="Type";
		ogrenciModel.setColumnIdentifiers(colOgrenciName);
		ogrenciData=new Object[5];
		for(int i=0;i<ogrenci.getOgrenciList().size();i++)
		{
			ogrenciData[0]=ogrenci.getOgrenciList().get(i).getId();
			ogrenciData[1]=ogrenci.getOgrenciList().get(i).getIsim();
			ogrenciData[2]=ogrenci.getOgrenciList().get(i).getTcno();
			ogrenciData[3]=ogrenci.getOgrenciList().get(i).getType();
			ogrenciData[4]=ogrenci.getOgrenciList().get(i).getPassword();
			ogrenciModel.addRow(ogrenciData);

			
		}
		//dersler data
		
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
		// ogrenci 
		
		DefaultTableModel ogretmenModel = new DefaultTableModel();
		Object[] colOgrenci = new Object[2];
		colOgrenci[0]="ID";
		colOgrenci[1]="Ad Soyad";
		ogretmenModel.setColumnIdentifiers(colOgrenci);
		Object[] ogrenciData = new Object[2];
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 503);
		w_panel = new JPanel();
		w_panel.setBackground(Color.WHITE);
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 62, 709, 403);
		w_panel.add(tabbedPane);
		
		JPanel ogrpanel = new JPanel();
		ogrpanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Öðrenci Paneli", null, ogrpanel, null);
		ogrpanel.setLayout(null);
		
		JScrollPane w_scrollogrenci = new JScrollPane();
		w_scrollogrenci.setBounds(10, 10, 503, 358);
		ogrpanel.add(w_scrollogrenci);
		
		table_ogrenci = new JTable(ogrenciModel);
		w_scrollogrenci.setViewportView(table_ogrenci);
		table_ogrenci.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					fld_ogrenci_id.setText(table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}

		});

		table_ogrenci.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 0).toString());
					String selectTcno = table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 1).toString();
					String selectName = table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 2).toString();
					String selectType = table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 3).toString();
					String selectPass = table_ogrenci.getValueAt(table_ogrenci.getSelectedRow(), 4).toString();

					try {
						boolean control = ogrenci.updateogrenci(selectID, selectTcno, selectName, selectType, selectPass);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label.setBounds(523, 10, 169, 29);
		ogrpanel.add(label);
		
		fld_ogrenci_ad = new JTextField();
		fld_ogrenci_ad.setColumns(10);
		fld_ogrenci_ad.setBounds(523, 37, 169, 22);
		ogrpanel.add(fld_ogrenci_ad);
		
		JLabel label_1 = new JLabel("T.C. No");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label_1.setBounds(523, 69, 169, 14);
		ogrpanel.add(label_1);
		
		fld_ogrenci_tc = new JTextField();
		fld_ogrenci_tc.setColumns(10);
		fld_ogrenci_tc.setBounds(523, 86, 169, 22);
		ogrpanel.add(fld_ogrenci_tc);
		
		JLabel label_3 = new JLabel("\u015Eifre");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label_3.setBounds(523, 118, 169, 22);
		ogrpanel.add(label_3);
		
		fld_ogrenci_pass = new JTextField();
		fld_ogrenci_pass.setColumns(10);
		fld_ogrenci_pass.setBounds(521, 139, 171, 22);
		ogrpanel.add(fld_ogrenci_pass);
		
		JButton btn_addogrenci = new JButton("Ekle");
		btn_addogrenci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (fld_ogrenci_ad.getText().length() == 0 || fld_ogrenci_tc.getText().length() == 0 || fld_ogrenci_ad.getText().length() == 0 || fld_ogrenci_type.getText().length() == 0 || fld_ogrenci_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = ogrenci.addogrenci(fld_ogrenci_tc.getText(),fld_ogrenci_ad.getText(), fld_ogrenci_type.getText(), fld_ogrenci_pass.getText());
						if (control) {
							Helper.showMsg("success");
							fld_ogrenci_tc.setText(null);
							fld_ogrenci_ad.setText(null);
							fld_ogrenci_pass.setText(null);
							fld_ogrenci_type.setText(null);
							updateOgrenciModel();

						}
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
				}

				
				
				
			}
		});
		btn_addogrenci.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addogrenci.setBounds(523, 227, 169, 29);
		ogrpanel.add(btn_addogrenci);
		
		JLabel label_2 = new JLabel("Kullan\u0131c\u0131 ID");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label_2.setBounds(523, 266, 169, 22);
		ogrpanel.add(label_2);
		
		fld_ogrenci_id = new JTextField();
		fld_ogrenci_id.setColumns(10);
		fld_ogrenci_id.setBounds(523, 294, 169, 22);
		ogrpanel.add(fld_ogrenci_id);
		
		JButton btn_delete = new JButton("Sil");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fld_ogrenci_id.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir  Öðretmen Seçiniz !");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_ogrenci_id.getText());
						try {
							boolean control = ogrenci.deleteogrenci(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_ogrenci_id.setText(null);
								updateOgrenciModel();

							}
						} catch (SQLException e) {
			
							e.printStackTrace();
						}
					}
				}
			}
		});
		btn_delete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_delete.setBounds(523, 325, 169, 29);
		ogrpanel.add(btn_delete);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblType.setBounds(521, 171, 169, 29);
		ogrpanel.add(lblType);
		
		fld_ogrenci_type = new JTextField();
		fld_ogrenci_type.setColumns(10);
		fld_ogrenci_type.setBounds(523, 195, 169, 22);
		ogrpanel.add(fld_ogrenci_type);
		
		JPanel w_ogrencidersler = new JPanel();
		w_ogrencidersler.setBackground(Color.WHITE);
		tabbedPane.addTab("Dersler", null, w_ogrencidersler, null);
		w_ogrencidersler.setLayout(null);
		
		JScrollPane w_scrollDersler = new JScrollPane();
		w_scrollDersler.setBounds(10, 10, 260, 356);
		w_ogrencidersler.add(w_scrollDersler);
		
		derslerMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		derslerMenu.add(updateMenu);
		derslerMenu.add(deleteMenu);
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_dersler.getValueAt(table_dersler.getSelectedRow(), 0).toString());
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

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_dersler.getValueAt(table_dersler.getSelectedRow(), 0).toString());
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

		table_dersler = new JTable(dersModel);
		table_dersler.setComponentPopupMenu(derslerMenu);
		table_dersler.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_dersler.rowAtPoint(point);
				table_dersler.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		w_scrollDersler.setViewportView(table_dersler);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ders Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(280, 23, 171, 26);
		w_ogrencidersler.add(lblNewLabel_1_2);
		
		fld_dersad = new JTextField();
		fld_dersad.setForeground(Color.BLACK);
		fld_dersad.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_dersad.setColumns(10);
		fld_dersad.setBackground(Color.WHITE);
		fld_dersad.setBounds(280, 51, 159, 26);
		w_ogrencidersler.add(fld_dersad);
		
		JButton btnNewButton_1_1 = new JButton("Ekle");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fld_dersad.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (dersler.addDersler(fld_dersad.getText())) {
							Helper.showMsg("success");
							fld_dersad.setText(null);
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
		btnNewButton_1_1.setBounds(280, 87, 159, 32);
		w_ogrencidersler.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Ders Ad\u0131");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(280, 145, 159, 26);
		w_ogrencidersler.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Seç");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int selRow = table_dersler.getSelectedRow();
				if(selRow >= 0) {
					String selDersler = table_dersler.getModel().getValueAt(selRow, 0).toString();
					int selDerslerID = Integer.parseInt(selDersler);
					DefaultTableModel clearModel = (DefaultTableModel) table_ogrnc.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0 ; i < ogrenci.getDersOgrncList(selDerslerID).size();i++) {
						ogrenciData[0]=ogrenci.getDersOgrncList(selDerslerID).get(i).getId();
						ogrenciData[1]=ogrenci.getDersOgrncList(selDerslerID).get(i).getIsim();
						ogretmenModel.addRow(ogrenciData);
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_ogrnc.setModel(ogretmenModel);
				}else {
					Helper.showMsg("Lütfen bir ders seçiniz.");
				}
				
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton_1_1_1.setBounds(280, 172, 159, 32);
		w_ogrencidersler.add(btnNewButton_1_1_1);
		
		JComboBox select_dersler = new JComboBox();
		select_dersler.setBounds(280, 271, 159, 39);
		
		for (int i = 0; i < ogrenci.getOgrenciList().size(); i++) {
			select_dersler.addItem(new Item(ogrenci.getOgrenciList().get(i).getId(), ogrenci.getOgrenciList().get(i).getIsim()));
		}
		select_dersler.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_ogrencidersler.add(select_dersler);

		
		
		w_ogrencidersler.add(select_dersler);
		
		JButton btn_addOgretmen = new JButton("Ekle");
		btn_addOgretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_dersler.getSelectedRow();
				if (selRow >= 0) {
					String selDersler = table_dersler.getModel().getValueAt(selRow, 0).toString();
					int selDerslerID = Integer.parseInt(selDersler);
					Item ogrtItem = (Item) select_dersler.getSelectedItem();
					try {
						boolean control = ogrenci.addDersOgrnc(ogrtItem.getKey(), selDerslerID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_ogrenci.getModel();
							clearModel.setRowCount(0);
							for(int i = 0 ; i < ogrenci.getDersOgrncList(selDerslerID).size();i++) {
								ogrenciData[0]=ogrenci.getDersOgrncList(selDerslerID).get(i).getId();
								ogrenciData[1]=ogrenci.getDersOgrncList(selDerslerID).get(i).getIsim();
								ogrenciModel.addRow(ogrenciData);
								}
							table_ogrenci.setModel(ogrenciModel);
							
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
		btn_addOgretmen.setBounds(280, 320, 159, 32);
		w_ogrencidersler.add(btn_addOgretmen);
		
		JScrollPane w_scroologrenci = new JScrollPane();
		w_scroologrenci.setBounds(445, 10, 256, 356);
		w_ogrencidersler.add(w_scroologrenci);
		
		table_ogrnc = new JTable();
		w_scroologrenci.setViewportView(table_ogrnc);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn "+ogrenci.getIsim());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 509, 30);
		w_panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginGUI GUI = new LoginGUI();
				GUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnNewButton.setBounds(596, 14, 108, 29);
		w_panel.add(btnNewButton);
		
		fld_ara = new JTextField();
		fld_ara.setForeground(Color.BLACK);
		fld_ara.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_ara.setColumns(10);
		fld_ara.setBackground(Color.WHITE);
		fld_ara.setBounds(414, 52, 171, 27);
		w_panel.add(fld_ara);
		
		JButton btn_ara = new JButton("Ara");
		btn_ara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		
	
		btn_ara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_ara.setBounds(596, 50, 108, 29);
		w_panel.add(btn_ara);
	}


	public void updateOgrenciModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_ogrenci.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < ogrenci.getOgrenciList().size(); i++) {
			ogrenciData[0] = ogrenci.getOgrenciList().get(i).getId();
			ogrenciData[1] = ogrenci.getOgrenciList().get(i).getIsim();
			ogrenciData[2] = ogrenci.getOgrenciList().get(i).getTcno();
			ogrenciData[3] = ogrenci.getOgrenciList().get(i).getType();
			ogrenciData[4] = ogrenci.getOgrenciList().get(i).getPassword();
			ogrenciModel.addRow(ogrenciData);
		}
	}

	public void updateDerslerModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_dersler.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < dersler.getList().size(); i++) {
			derslerData[0] = dersler.getList().get(i).getID();
			derslerData[1] = dersler.getList().get(i).getDersAd();
			dersModel.addRow(derslerData);
		}
	}
	
}
