package com.mrGrapher;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;


public class SplashPanel extends JPanel {

	JProgressBar progressBar;

	public SplashPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5 ));
		Icon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
		add(new JLabel(icon), BorderLayout.CENTER);
		progressBar = new JProgressBar(1, 100);
		progressBar.setForeground(new Color(255, 0, 0));
		add(progressBar, BorderLayout.SOUTH);
	}

	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}

	public static void main(String args[]) {
		SplashPanel sp = new SplashPanel();
		JWindow win = new JWindow();
		win.setContentPane(sp);
		win.pack();
		win.setLocationRelativeTo(null);
		win.setVisible(true);
		try {
			for (int i = 1; i <= 100; i++) {
				Thread.sleep(50);
				sp.setProgress(i);
			}
		} catch (Exception e) {
		}
		win.setVisible(false);
		win.dispose();
		Home h = new Home();
		h.main(null);
	}

}
