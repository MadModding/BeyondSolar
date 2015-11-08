package com.madmodding.space;

public class ElementLib {
	public static int getColor(long time) {
		double t = (int) ((time / 8) % 300);
		int clr = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		if (t > 250 || t < 150) { // 000 - 100
			if (t > 250)
				r = (int) ((t - 250d) / 50d * 255d);
			else if (t < 150 && t > 100)
				r = (int) ((150d - t) / 50d * 255d);
			else
				r = 255;
		}
		if (t > 50 && t < 250) { // 100 - 200
			if (t > 200 && t < 250)
				g = (int) (-(t - 250d) / 50d * 255d);
			else if (t < 100 && t > 50)
				g = (int) ((t - 50d) / 50d * 255d);
			else
				g = 255;
		}
		if (t > 150 || t < 50) { // 200 - 300
			if (t < 50 && t > 0)
				b = (int) ((50d - t) / 50d * 255d);
			else if (t > 150 && t < 200)
				b = (int) ((t - 150d) / 50d * 255d);
			else
				b = 255;

		}
		clr += r * 65536;
		clr += g * 256;
		clr += b;
		return clr;
	}
}
