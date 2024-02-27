package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class LoginGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOkulBilgiYnetim = new JLabel("Okul Bilgi Y\u00F6netim Sistemi");
		lblOkulBilgiYnetim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblOkulBilgiYnetim.setBounds(103, 26, 230, 26);
		contentPane.add(lblOkulBilgiYnetim);
		
		JButton btn_OgrtLogin = new JButton("\u00D6\u011Frenci Giri\u015F");
		btn_OgrtLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginOgrenciGUI oGUI = new LoginOgrenciGUI();
				oGUI.setVisible(true);
				dispose();
			}
		});
		btn_OgrtLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_OgrtLogin.setBounds(43, 128, 372, 44);
		contentPane.add(btn_OgrtLogin);
		
		JLabel lblNewLabel = new JLabel("Giri\u015F Y\u00F6ntemini Se\u00E7iniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		lblNewLabel.setBounds(142, 62, 145, 25);
		contentPane.add(lblNewLabel);
		
		JButton btn_OgrtLogin_1 = new JButton("\u00D6\u011Fretmen Giri\u015F");
		btn_OgrtLogin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				LoginOgretmenGUI oGUI = new LoginOgretmenGUI();
				oGUI.setVisible(true);
				dispose();
			}
		});
		btn_OgrtLogin_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_OgrtLogin_1.setBounds(43, 193, 372, 44);
		contentPane.add(btn_OgrtLogin_1);
	}
}
