package com.mrGrapher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JColorChooser;

public class Home extends JPanel implements ActionListener {

	JButton encryption, decryption;
	JLabel label1;
	static Image img = Toolkit.getDefaultToolkit().getImage("/images/code1.jpg");

	static JFrame f = new JFrame("Mr.GRAPHER");

	public Home() {

		ImageIcon icon;
		Image image;
		{
			icon = new ImageIcon(getClass().getResource("/images/p13.png"));
			image = icon.getImage();
		}
		f.setIconImage(image);

		setBackground(Color.BLACK);

		setLayout(null);

		label1 = new JLabel("Choose OPERATION : ");
		label1.setBounds(120, 25, 150, 25);
		add(label1);

		
		label1.setForeground(new Color(120, 90, 40));
		label1.setBackground(new Color(100, 20, 70));

		encryption = new JButton("ENCRYPTION");
		encryption.setMnemonic('E');
		encryption.addActionListener(this);
		encryption.setBackground(new Color(120, 90, 40));
		encryption.setBounds(120, 50, 120, 25);
		add(encryption);

		decryption = new JButton("DECRYPTION");
		decryption.setMnemonic('D');
		decryption.addActionListener(this);
		decryption.setBackground(new Color(120, 90, 40));
		decryption.setBounds(120, 100, 120, 25);
		add(decryption);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if (str.equals("ENCRYPTION")) {
			f.dispose();
			EncryptionGui eg = new EncryptionGui();
			eg.main(null);
			encryption.setEnabled(false);
			decryption.setEnabled(true);

		}
		if (str.equals("DECRYPTION")) {
			f.dispose();
			DecryptionGui eg = new DecryptionGui();
			eg.main(null);
			encryption.setEnabled(true);
			decryption.setEnabled(false);
		}
	}

	public static void main(String[] args) {

		f.setContentPane(new Home());
		f.setBounds(10, 10, 400, 200);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
