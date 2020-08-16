package com.mrGrapher;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Encryption {

	static ArrayList<Integer> randomNumber(int w, long fileLength) {
		ArrayList<Integer> temp = new ArrayList();
		Random rd = new Random();
		for (int i = 0; i < fileLength; i++) {
			temp.add(rd.nextInt(w));
		}
		return temp;

	}

	static String generatingDigit(int value) {
		String pre = "P";
		if (value < 0) {
			pre = "N";
			value = Math.abs(value);
		}
		String val = "" + value;
		if (val.length() == 3) {
			return (pre + val);
		} else if (val.length() == 2) {
			return (pre + "0" + val);
		} else if (val.length() == 1) {
			return (pre + "00" + val);
		}

		return null;
	}

	Boolean encrypt(String actualAdd, String destinationAdd, String imageAdd, String pwd) {
		try {

			File file = new File(actualAdd);

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			String s = new String();
			long fileLength = file.length();
			while ((st = br.readLine()) != null)
				s = s + st;

			BufferedImage bimg = ImageIO.read(new File(imageAdd));
			int width = bimg.getWidth();
			int height = bimg.getHeight();

			int x = (int) (fileLength / width);
			int y = (int) (fileLength / height);

			int maxValue = Math.max(x, y);

			ArrayList<Integer> x1 = new ArrayList<Integer>();
			ArrayList<Integer> y1 = new ArrayList<Integer>();

			if (fileLength < width && fileLength < height) {
				x1 = randomNumber(width, fileLength);
				System.out.println(x1);
				y1 = randomNumber(height, fileLength);
				System.out.println(y1);
			}
			String password = pwd;
			ArrayList<Integer> pass = new ArrayList();
			for (int i = 0; i < 8; i++) {
				int n = (int) (password.charAt(i)) - 48;
				System.out.println(n);
				pass.add(n + 80);
			}
			System.out.println(pass);
			String finalValue = new String();
			String enc = new String();
			int j = 0;

			for (int i = 0; i < s.length(); i += 3, j++) {
				int r, b, g;
				r = (int) (s.charAt(i));
				if (s.length() <= i + 1) {
					b = 0;
				} else {
					b = (int) (s.charAt(i + 1));
				}
				if (s.length() <= i + 2) {
					g = 0;
				} else {
					g = (int) (s.charAt(i + 2));
				}
				System.out.println(r + " " + b + " " + g);
				Color c = new Color(bimg.getRGB(x1.get(j), y1.get(j)));
				int v1 = c.getRed() - r;
				int v2 = c.getBlue() - b;
				int v3 = c.getGreen() - g;

				String val1 = generatingDigit(v1);
				String val2 = generatingDigit(v2);
				String val3 = generatingDigit(v3);

				finalValue = finalValue + val1 + val2 + val3;
				enc = enc + (char) ((x1.get(j) / 1000) + pass.get(0))
						+ (char) (((x1.get(j) % 1000) / 100) + pass.get(1))
						+ (char) (((x1.get(j) % 100) / 10) + pass.get(2)) + (char) (x1.get(j) % 10 + pass.get(3))
						+ (char) ((y1.get(j) / 1000) + pass.get(4)) + (char) (((y1.get(j) % 1000) / 100) + pass.get(5))
						+ (char) (((y1.get(j) % 100) / 10) + pass.get(6)) + (char) ((y1.get(j) % 10) + pass.get(7));

			}

			enc = enc + "BIKRANT SON OF AYUSH RAI" + finalValue;

			FileWriter fw = new FileWriter(destinationAdd);
			fw.write(enc);
			fw.close();

			File file1 = new File(destinationAdd);

			BufferedReader br1 = new BufferedReader(new FileReader(file1));

			String st1;
			String s1 = new String();
			while ((st1 = br1.readLine()) != null)
				s1 = s1 + st1;

			System.out.println(s1);

			System.out.println(enc + " \n" + finalValue);

			System.out.println(width + " " + height + " " + file.length());
			System.out.println(s);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
