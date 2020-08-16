package com.mrGrapher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class EncryptionGui extends JPanel implements ActionListener {

	JLabel label1, label2, label3, label4;
	JTextField actualData, image, destination;
	JButton btnActualData, btnDestination, btnImage, encrypt, home;
	JPasswordField password;

	static JFrame f = new JFrame("Mr.GRAPHER(ENCRYPTION)");

	public EncryptionGui() {

		ImageIcon icon1;
		Image image1;
		{
			icon1 = new ImageIcon(getClass().getResource("/images/p13.png"));
			image1 = icon1.getImage();
		}
		f.setIconImage(image1);
		
		setLayout(null);
		
		setBackground(Color.black);
		
		label1 = new JLabel("ACTUAL DATA FILE");
		label2 = new JLabel("IMAGE");
		label3 = new JLabel("DESTINATION");
		label4 = new JLabel("PASSWORD");
		
		label1.setForeground(new Color(120, 90, 40));
		label1.setBackground(new Color(100, 20, 70));

		label2.setForeground(new Color(120, 90, 40));
		label2.setBackground(new Color(100, 20, 70));

		label3.setForeground(new Color(120, 90, 40));
		label3.setBackground(new Color(100, 20, 70));

		label4.setForeground(new Color(120, 90, 40));
		label4.setBackground(new Color(100, 20, 70));

		actualData = new JTextField(50);
		destination = new JTextField(50);
		image = new JTextField(50);
		password = new JPasswordField(10);

		btnActualData = new JButton("Browse");
		btnActualData.setActionCommand("ActualData");
		btnActualData.addActionListener(this);

		btnImage = new JButton("Browse");
		btnImage.setActionCommand("Image");
		btnImage.addActionListener(this);

		btnDestination = new JButton("Browse");
		btnDestination.setActionCommand("Destination");
		btnDestination.addActionListener(this);

		encrypt = new JButton("ENCRYPT");
		encrypt.setActionCommand("Encrypt");
		encrypt.addActionListener(this);

		Icon icon = new ImageIcon(getClass().getResource("/images/home-24.png"));
		home = new JButton(icon);
		home.setActionCommand("Home");
		home.addActionListener(this);
		home.setBackground(Color.black);
		home.setBorderPainted(false);
		home.setBounds(10, 10, 24, 24);

		label1.setBounds(50, 50, 200, 25);
		actualData.setBounds(200, 50, 300, 25);
		btnActualData.setBounds(500, 50, 100, 25);
		label2.setBounds(50, 100, 100, 25);
		image.setBounds(200, 100, 300, 25);
		btnImage.setBounds(500, 100, 100, 25);
		label3.setBounds(50, 150, 100, 25);
		destination.setBounds(200, 150, 300, 25);
		btnDestination.setBounds(500, 150, 100, 25);
		label4.setBounds(50, 200, 200, 25);
		password.setBounds(200, 200, 300, 25);
		encrypt.setBounds(300, 250, 100, 25);
		
		btnActualData.setBackground(new Color(120, 90, 40));

		btnDestination.setBackground(new Color(120, 90, 40));

		btnImage.setBackground(new Color(120, 90, 40));

		encrypt.setBackground(new Color(120, 90, 40));

		add(home);
		add(label1);
		add(actualData);
		add(btnActualData);
		add(label2);
		add(image);
		add(btnImage);
		add(label3);
		add(destination);
		add(btnDestination);
		add(label4);
		add(password);
		add(encrypt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO

		String str = e.getActionCommand();
		if (str.equals("ActualData")) {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new File("."));
			int result = jFileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jFileChooser.getSelectedFile();
				actualData.setText(selectedFile.getAbsolutePath());
			}
		} else if (str.equals("Destination")) {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new File("."));
			int result = jFileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jFileChooser.getSelectedFile();
				destination.setText(selectedFile.getAbsolutePath());
			}
		} else if (str.equals("Image")) {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new File("."));
			int result = jFileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jFileChooser.getSelectedFile();
				image.setText(selectedFile.getAbsolutePath());
			}
		} else if (str.equals("Encrypt")) {
			String actual = actualData.getText();
			if (actual.equals("")) {
				actual = null;
			}
			String img = image.getText();
			if (img.equals("")) {
				img = null;
			}
			String desti = destination.getText();
			if (desti.equals("")) {
				desti = null;
			}
			String pass = password.getText();
			if (pass.equals("")) {
				pass = null;
			}
			Encryption en = new Encryption();
			if (actual != null && desti != null && pass != null && img != null) {
				if (pass.length() > 7) {
					if (en.encrypt(actual, desti, img, pass)) {
						JOptionPane.showMessageDialog(this, "File Successfully Encrypted.");
						Home h = new Home();
						f.dispose();
						h.main(null);
					}
				} else {
					JOptionPane.showMessageDialog(this, "PassWord must be of 8 or \n more than  8 character!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please fill all the fields!!");
			}
		} else if (str.equals("Home")) {
			f.dispose();
			Home h = new Home();
			h.main(null);
		}
	}

	public static void main(String args[]) {
		

		f.setContentPane(new EncryptionGui());
		f.setBounds(10, 10, 650, 400);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
