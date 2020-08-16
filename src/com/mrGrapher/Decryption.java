package com.mrGrapher;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Decryption {

	boolean decrypt(String encryptAdd, String imageAdd, String pwd, String destinationAdd){

		try {
			System.out.println(imageAdd + "image add" );
			BufferedImage bimg = ImageIO.read(new File(imageAdd));

			String password = pwd;
			ArrayList<Integer> pass = new ArrayList();
			for (int i = 0; i < 8; i++) {
				int n = (int) (password.charAt(i)) - 48;
				System.out.println(n);
				pass.add(n + 80);
			}

			File file = new File(encryptAdd);

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			String s = new String();
			long fileLength = file.length();
			while ((st = br.readLine()) != null)
				s = s + st;

			String[] arrOfStr = s.split("BIKRANT SON OF AYUSH RAI", 2);
			ArrayList<Integer> partialValue = new ArrayList();

			System.out.println(pass + "\n" + s + "\n" + arrOfStr[0] + "\n" + arrOfStr[1]);

			for (int i = 0; i < arrOfStr[1].length(); i += 4) {
				if (arrOfStr[1].charAt(i) == 'N') {
					String temp = "" + arrOfStr[1].charAt(i + 1) + arrOfStr[1].charAt(i + 2)
							+ arrOfStr[1].charAt(i + 3);
					int t = Integer.parseInt(temp);
					partialValue.add(t * (-1));
				} else {
					String temp = "" + arrOfStr[1].charAt(i + 1) + arrOfStr[1].charAt(i + 2)
							+ arrOfStr[1].charAt(i + 3);
					int t = Integer.parseInt(temp);
					partialValue.add(t);
				}
			}

			long dataLength = arrOfStr[0].length();
			int j = 0;
			String dec = new String();
			
			for (int i = 0; i < dataLength; i += 8) {
				int a = (((int) (arrOfStr[0].charAt(i))) - pass.get(0)) * 1000;
				int b = (((int) (arrOfStr[0].charAt(i + 1))) - pass.get(1)) * 100;
				int c = (((int) (arrOfStr[0].charAt(i + 2))) - pass.get(2)) * 10;
				int d = (((int) (arrOfStr[0].charAt(i + 3))) - pass.get(3));
				int e = (((int) (arrOfStr[0].charAt(i + 4))) - pass.get(4)) * 1000;
				int f = (((int) (arrOfStr[0].charAt(i + 5))) - pass.get(5)) * 100;
				int g = (((int) (arrOfStr[0].charAt(i + 6))) - pass.get(6)) * 10;
				int h = (((int) (arrOfStr[0].charAt(i + 7))) - pass.get(7));

				int x = a + b + c + d;
				int y = e + g + f + h;
				System.out.println(bimg.getWidth() + " " + bimg.getHeight());
				
				if(x < bimg.getWidth() && y < bimg.getHeight()) {
					Color c1 = new Color(bimg.getRGB(x, y));
					int v1 = c1.getRed() - partialValue.get(j);
					int v2 = c1.getBlue() - partialValue.get(j + 1);
					int v3 = c1.getGreen() - partialValue.get(j + 2);
					
					if(v1 < 125 && v2 < 125 && v3 < 125 && (partialValue.size() > (j + 2))) {
						dec = dec + (char)v1 + (char)v2 + (char)v3;
					}
					else {
						System.out.println("WRONG");
					}
				} else {

					System.out.println(" again WRONG");
				}
				j+=3;
			}
			System.out.println(dec);
			
			FileWriter fw = new FileWriter(destinationAdd);
			fw.write(dec);
			fw.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
