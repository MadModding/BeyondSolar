package com.madmodding.space.items.element;

import com.madmodding.space.Main;

import net.minecraft.item.EnumRarity;

public enum EnumAlloy {

	steel("steel", new Element[] { Element.fe, Element.c }, new double[][] { { 95, 0.05 }, { 99, 1 } }, 55.845,
			0x666666, 1, 7, 2750, 5500, 7.87, Main.RARE), 
	carbsteel("carbonsteel", new Element[] { Element.fe, Element.c }, new double[][] { { 90, 2 }, { 99, 8 } }, 55.845,
			0x666666, 1, 7, 2750, 5500, 7.87, Main.RARE), 
	bronze("bronze", new Element[] { Element.cu, Element.sn }, new double[][] { { 88, 4 }, { 99, 20 } }, 55.845,
			0xAF6400, 1, 3.5, 1535, 2750, 7.87, Main.UNCOMMON), 
	brass("brass", new Element[] { Element.cu, Element.zn },
					new double[][] { { 65, 25 }, { 75, 35 } }, 55.845, 0xE19600, 1, 3.0, 1652, 3300, 7.87,
					Main.UNCOMMON), 
	aluminumbrass("aluminumbrass", new Element[] { Element.cu, Element.zn, Element.al },
							new double[][] { { 65, 25, 2 }, { 75, 35, 10 } }, 55.845, 0xE1AA00, 1, 2.5, 1535, 2750,
							7.87, Main.UNCOMMON), 
	meteoriciron("meteoriciron", new Element[] { Element.fe, Element.ni },
									new double[][] { { 65, 1 }, { 99, 25 } }, 55.845, 0xAFAF9B, 1, 3.0, 1535, 2750,
									7.87, Main.COMMON);

	private String Name;
	public Element[] Alloy;
	public double[][] Percent;
	private double Mass;
	private int Color;
	private int Type;
	private double Hardness;
	private int BoilingPoint;
	private int MeltingPoint;
	private double Density;
	private int Rarity;

	private EnumAlloy(String n, Element[] alloy, double[][] percent, double m, int c, int t, double h, int bp, int mp,
			double d, EnumRarity r) {
		Percent = percent;
		Alloy = alloy;
		Name = n;
		Mass = m;
		Color = c;
		Type = t;
		Hardness = h;
		BoilingPoint = bp;
		MeltingPoint = mp;
		Density = d;
		Rarity = ElementLib.rarityToInt(r);
	}

	public static EnumAlloy getAlloy(int[][] el) {
		if (el.length != 2)
			return null;
		int total = 0;

		for (int i = 0; i < el[0].length; i++) {
			total += el[0][i];
		}
		if (total == 0)
			return null;
		for (int i = 0; i < EnumAlloy.values().length; i++) {
			EnumAlloy option = EnumAlloy.values()[i];
			int o = 0;
			for (int l = 0; l < el[0].length; l++) {
				for (int p = 0; p < option.Alloy.length; p++) {
					if (option.Alloy[p] == ElementLib.Elements[el[1][l] % ElementLib.Elements.length]
							&& (((float) el[0][l]) / ((float) total) * 100) >= option.Percent[0][p]
							&& (((float) el[0][l]) / ((float) total) * 100) <= option.Percent[1][p]) {
						o++;
						//System.out.println("Alloy Success!:");
					} else {
						//System.out.println("Alloy Fail:");
					}
					//System.out.println("Element 1: " + option.Alloy[p].getName());
					//System.out.println("Element 2: " + ElementLib.Elements[el[1][l] % ElementLib.Elements.length].getName());
					if (option.Alloy[p] == ElementLib.Elements[el[1][l] % ElementLib.Elements.length]) {
						//System.out.println("Max %: " + option.Percent[1][p]);
						//System.out.println("Min %: " + option.Percent[0][p]);
						//System.out.println("Element %: " + (((float) el[0][l]) / ((float) total) * 100));
					}
				}
			}
			if (o >= option.Alloy.length) {
				//System.out.println("Overall Alloy Success!: " + option.Alloy.length + " >= " + o);
			} else {
				//System.out.println("Overall Alloy Fail: " + option.Alloy.length + " < " + o);
			}

			if (o >= option.Alloy.length)
				return option;

		}
		return null;
	}

	public String getName() {
		return Name;
	}

	public int getColor() {
		return Color;
	}

	public int getType() {
		return Type;
	}

	public double getHardness() {
		return Hardness;
	}

	public int getBoilingPoint() {
		return BoilingPoint;
	}

	public int getMeltingPoint() {
		return MeltingPoint;
	}

	public double getMass() {
		return Mass;
	}

	public double getDensity() {
		return Density;
	}

	public EnumRarity getRarity() {
		return ElementLib.intToRarity(Rarity);
	}

}
