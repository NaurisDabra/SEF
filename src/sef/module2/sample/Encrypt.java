package sef.module2.sample;

import java.util.Random;
import java.util.Scanner;

public class Encrypt {

	public static void main(String[] args) throws Exception {
		String original ="Jauki";
		char[] mainStr = original.toCharArray();
		Random randomGenerator = new Random();
		int key = randomGenerator.nextInt(25);
		// int key =3;
		for (int i = 0; i < mainStr.length; i++) {
			if ( mainStr[i] >= 'a' && mainStr[i] <= 'z') {
				if ((int) mainStr[i] + key > 'z') {
					mainStr[i] = (char) (96 + ((mainStr[i] + key) - 'z'));

				} else
					mainStr[i] += key;
			} else {
				if ((int) mainStr[i] >= 'A' && (int) mainStr[i] <= 'Z') {
					if ((int) mainStr[i] + key > 'Z') {
						mainStr[i] = (char) (64 + ((mainStr[i] + key) - 'Z'));
					} else
						mainStr[i] += key;
				}
			}

		}

		String result = String.valueOf(mainStr);
		System.out.println(result);
		int key2 = 0;
		Scanner in = new Scanner(System.in);
		String result2;
		char[] otrais;

		while (true) {
			otrais = result.toCharArray();
			while (true) {
				System.out.println("ievadiet atslegu");
				key2 = in.nextInt();
				if (key2 >= 0 && key2 < 26)
					break;
			}
			for (int i = 0; i < otrais.length; i++) {
				if ( otrais[i] >= 'A' &&  otrais[i] <= 'Z') {
					if (otrais[i] - key2 < 'A') {
						otrais[i] = (char)('Z' - (64 - ( otrais[i] - key2)));
					} else
						otrais[i] -= key2;
				}
				if ((int) otrais[i] >= 'a' &&  otrais[i] <= 'z') {
					if ((int) otrais[i] - key2 < 'a') {
						otrais[i] = (char) ('z' - (96 - (otrais[i] - key2)));

					} else
						otrais[i] -= key2;

				}

			}
			result2 = String.valueOf(otrais);

			System.out.println(otrais);
			if (result2.equals(original))
				break;
		}
in.close();
	}
	

}
