package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Helper.Helper;
import Model.Dersler;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateDerslerGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fld_derslerName;
	private static Dersler dersler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDerslerGUI frame = new UpdateDerslerGUI(dersler);
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
	public UpdateDerslerGUI(Dersler dersler) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable((TableModel) null);
		table.setBounds(-253, 21, 258, 0);
		contentPane.add(table);

		JLabel lblNewLabel_1_2 = new JLabel("Ad Soyad");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(15, 12, 171, 26);
		contentPane.add(lblNewLabel_1_2);

		fld_derslerName = new JTextField();
		fld_derslerName.setForeground(Color.BLACK);
		fld_derslerName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		fld_derslerName.setColumns(10);
		fld_derslerName.setBackground(Color.WHITE);
		fld_derslerName.setBounds(15, 41, 159, 26);
		fld_derslerName.setText(dersler.getDersAd());
		contentPane.add(fld_derslerName);

		JButton btn_updateDersler = new JButton("D\u00FCzenle");
		btn_updateDersler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.confirm("sure")) {
					try {
						dersler.updateDersler(dersler.getID(), fld_derslerName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btn_updateDersler.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_updateDersler.setBounds(15, 71, 159, 32);
		contentPane.add(btn_updateDersler);
	}
}
