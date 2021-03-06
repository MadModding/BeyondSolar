package com.madmodding.space.items.element;

import com.madmodding.space.Main;

import net.minecraft.item.EnumRarity;

public enum Element {

	h("hydrogen", 1, 1.0079, 0xdcdcff, 3, 0, -259, -253, 0.09, Main.COMMON), 
	he("helium", 2, 4.0026, 0xffdcff, 3, 0, -272, -269, 0.18, Main.COMMON), 
	li("lithium", 3, 6.941, 0xa0a0a0, 3, 0.6, 180, 1347, 0.53, Main.COMMON), 
	be( "beryllium", 4, 9.0122, 0x787878, 2, 5.5, 1278, 2970, 1.85, Main.COMMON), 
	b("boron", 5, 10.811, 0x9696a5, 2, 9.5, 2300, 2550, 2.34, Main.COMMON), 
	c("carbon", 6, 12.0107, 0x282828, 2, 1.5, 3500, 4827, 2.26, Main.UNCOMMON), 
	n("nitrogen", 7, 14.0067, 0xa0a0a0, 3, 0, -210, -196, 1.25, Main.COMMON), 
	o( "oxygen", 8, 15.9994, 0xf0f0f0, 3, 0, -218, -183, 1.43, Main.UNCOMMON), 
	f("fluorine", 9, 18.9984, 0x8c8cf0, 3, 0, -220, -188, 1.7, Main.UNCOMMON), 
	ne("neon", 10, 20.1797, 0xf02828, 3, 0, -249, -246, 0.9, Main.RARE), 
	na("sodium", 11, 22.9897, 0x969696, 3, 0.5, 98, 883, 0.97, Main.UNCOMMON), 
	mg("magnesium", 12, 24.305, 0x969696, 2, 2.5, 639, 1090, 1.74, Main.COMMON), 
	al("aluminium", 13, 26.9815, 0xafafaf, 1, 3, 660, 2467, 2.7, Main.COMMON), 
	si("silicon", 14, 28.0855, 0x414141, 2, 6.5, 1410, 2355, 2.33, Main.COMMON), 
	p("phosphorus", 15, 30.9738, 0xa54141, 3, 0, 44, 280, 1.82, Main.COMMON), 
	s( "sulfur", 16, 32.065, 0xebe119, 2, 2.0, 113, 445, 2.07, Main.COMMON), 
	cl( "chlorine", 17, 35.453, 0xcdd719, 3, 0, -101, -35, 3.21, Main.UNCOMMON), 
	ar( "argon", 18, 39.0983, 0xb914cd, 3, 0, -189, -186, 1.78, Main.RARE), 
	k( "potassium", 19, 39.948, 0x9b9bcd, 3, 0.4, 64, 774, 0.86, Main.UNCOMMON), 
	ca( "calcium", 20, 40.078, 0xa5a59b, 2, 1.5, 839, 1484, 1.55, Main.UNCOMMON), 
	sc( "scandium", 21, 44.9559, 0xafb99b, 2, 0, 1539, 2832, 2.99, Main.COMMON), 
	ti( "titanium", 22, 47.867, 0xffffff, 1, 6.0, 1660, 3287, 4.54, Main.RARE), 
	v( "vanadium", 23, 50.9415, 0x9b9b9b, 3, 7.0, 1890, 3380, 6.11, Main.COMMON), 
	cr( "chromium", 24, 51.9961, 0xc3c3c3, 1, 8.5, 1857, 2672, 7.19, Main.RARE), 
	mn( "manganese", 25, 54.938, 0xafafc3, 1, 6.0, 1245, 1962, 7.43, Main.COMMON), 
	fe( "iron", 26, 55.845, 0xafaf9b, 1, 4.0, 1535, 2750, 7.87, Main.COMMON), 
	co( "cobalt", 27, 58.6934, 0x4141e1, 1, 5.0, 1495, 2870, 8.9, Main.UNCOMMON), 
	ni( "nickel", 28, 58.9332, 0xb4b4b4, 1, 4.0, 1453, 2732, 8.9, Main.UNCOMMON), 
	cu( "copper", 29, 63.546, 0xd77300, 1, 3.0, 1083, 2567, 8.96, Main.COMMON), 
	zn( "zinc", 30, 65.39, 0xb4b4b4, 2, 2.5, 420, 907, 7.13, Main.UNCOMMON), 
	ga( "gallium", 31, 69.723, 0xb4b4b4, 2, 1.5, 30, 2403, 5.91, Main.COMMON), 
	ge( "germanium", 32, 72.64, 0xa4a4a4, 3, 6.0, 937, 2830, 5.32, Main.COMMON), 
	as( "arsenic", 33, 74.9216, 0xffa573, 3, 3.5, 81, 613, 5.72, Main.UNCOMMON), 
	se( "selenium", 34, 78.96, 0xd7d7d7, 3, 2.0, 217, 685, 4.79, Main.COMMON), 
	br( "bromine", 35, 79.904, 0xd7370f, 3, 0, -7, 59, 3.12, Main.COMMON), 
	kr( "krypton", 36, 83.8, 0xd77373, 3, 0, -157, -153, 3.75, Main.RARE), 
	rb( "rubidium", 37, 85.4678, 0xd75f5f, 3, 0.3, 39, 688, 1.63, Main.COMMON), 
	sr( "strontium", 38, 87.62, 0xd7c39b, 2, 1.5, 769, 1384, 2.54, Main.COMMON), 
	y( "yttrium", 39, 88.9059, 0xa4a4a4, 2, 0, 1523, 3337, 4.47, Main.COMMON), 
	zr( "zirconium", 40, 91.224, 0xd7d7d7, 1, 5.0, 1852, 4377, 6.51, Main.COMMON), 
	nb( "niobium", 41, 92.9064, 0xafafaf, 1, 6.0, 2468, 4927, 8.57, Main.COMMON), 
	mo( "molybdenum", 42, 95.94, 0x878787, 1, 5.5, 2617, 4612, 10.22, Main.COMMON), 
	tc( "technetium", 43, 98, 0xa5a5b9, 1, 0, 2200, 4877, 11.5, Main.COMMON), 
	ru( "ruthenium", 44, 101.07, 0x7d7d7d, 1, 6.5, 2250, 3900, 12.37, Main.RARE), 
	rh( "rhodium", 45, 102.9055, 0x7d7d7d, 1, 6.0, 1966, 3727, 12.41, Main.RARE), 
	pd( "palladium", 46, 106.42, 0xa0a0a0, 1, 5.0, 1552, 2927, 12.02, Main.RARE), 
	ag( "silver", 47, 107.8682, 0xaaaaa0, 1, 2.5, 962, 2212, 10.5, Main.RARE), 
	cd( "cadmium", 48, 112.411, 0xa0a0aa, 2, 2.0, 321, 765, 8.65, Main.COMMON), 
	in( "indium", 49, 114.818, 0x8c8c8c, 2, 1.0, 157, 2000, 7.31, Main.COMMON), 
	sn( "tin", 50, 118.71, 0xb4b4b4, 2, 1.5, 232, 2270, 7.31, Main.COMMON), 
	sb( "antimony", 51, 121.76, 0xb4b4be, 2, 3.0, 630, 1750, 6.68, Main.COMMON), 
	te( "tellurium", 52, 126.9045, 0x969696, 3, 2.0, 449, 990, 6.24, Main.COMMON), 
	i( "iodine", 53, 127.6, 0x3c3c6e, 3, 0, 114, 184, 4.93, Main.UNCOMMON), 
	xe( "xenon", 54, 131.293, 0xa03cd2, 3, 0, -112, -108, 5.9, Main.RARE), 
	cs( "cesium", 55, 132.9055, 0xa0a082, 3, 0.2, 29, 678, 1.87, Main.UNCOMMON), 
	ba( "barium", 56, 137.327, 0xd2d2dc, 2, 1.0, 725, 1140, 3.59, Main.UNCOMMON), 
	la( "lanthanum", 57, 138.9055, 0xa0aaa0, 2, 2.5, 920, 3469, 6.15, Main.UNCOMMON), 
	ce( "cerium", 58, 140.116, 0xbe7878, 2, 2.5, 795, 3257, 6.77, Main.UNCOMMON), 
	pr( "praseodymium", 59, 140.9077, 0x788c8c, 2, 0, 935, 3127, 6.77, Main.UNCOMMON), 
	nd( "neodymium", 60, 144.24, 0x8c7878, 2, 0, 1010, 3127, 7.01, Main.UNCOMMON), 
	pm( "promethium", 61, 145, 0x14ff78, 2, 0, 1100, 3000, 7.3, Main.RARE), 
	sm( "samarium", 62, 150.36, 0xaa9696, 2, 0, 1072, 1900, 7.52, Main.UNCOMMON), 
	eu( "europium", 63, 151.964, 0xa9a9a9, 2, 0, 822, 1597, 5.24, Main.UNCOMMON), 
	gd( "gadolinium", 64, 157.25, 0x808080, 2, 0, 1311, 3233, 7.9, Main.UNCOMMON), 
	tb( "terbium", 65, 158.9253, 0x6c6c6c, 2, 0, 1360, 3041, 8.23, Main.UNCOMMON), 
	dy( "dysprosium", 66, 162.5, 0x303030, 2, 0, 1412, 2562, 8.55, Main.UNCOMMON), 
	ho( "holmium", 67, 164.9303, 0x949494, 2, 0, 1470, 2720, 8.8, Main.UNCOMMON), 
	er( "erbium", 68, 167.259, 0x949494, 2, 0, 1522, 2510, 9.07, Main.UNCOMMON), 
	tm( "thulium", 69, 168.9342, 0x9e9494, 2, 0, 1545, 1727, 9.32, Main.UNCOMMON), 
	yb( "ytterbium", 70, 173.04, 0xb29e8a, 2, 0, 824, 1466, 6.9, Main.UNCOMMON), 
	lu( "lutetium", 71, 174.967, 0xa89eb2, 2, 0, 1656, 3315, 9.84, Main.UNCOMMON), 
	hf( "hafnium", 72, 178.49, 0xa8a8a8, 1, 5.5, 2150, 5400, 13.31, Main.UNCOMMON), 
	ta( "tantalum", 73, 180.9479, 0xa8a8a8, 1, 6.5, 2996, 5425, 16.65, Main.UNCOMMON), 
	w( "tungsten", 74, 183.84, 0xbcada8, 1, 7.5, 3410, 5660, 19.35, Main.RARE), 
	re( "rhenium", 75, 186.207, 0x1c1c1c, 1, 7.0, 3180, 5627, 21.04, Main.RARE), 
	os( "osmium", 76, 190.23, 0x8a809e, 1, 7.0, 3045, 5027, 22.6, Main.RARE), 
	ir( "iridium", 77, 192.217, 0xffffff, 1, 6.5, 2410, 4527, 22.4, Main.RARE), 
	pt( "platinum", 78, 195.078, 0x9e8a80, 1, 3.5, 1772, 3827, 21.45, Main.RARE), 
	au( "gold", 79, 196.9665, 0xc6bc1c, 1, 2.5, 1064, 2807, 19.32, Main.RARE), 
	hg( "mercury", 80, 200.59, 0x808080, 3, 0, -39, 357, 13.55, Main.RARE), 
	tl( "thallium", 81, 204.3833, 0xa88a80, 2, 1.0, 303, 1457, 11.85, Main.UNCOMMON), 
	pb( "lead", 82, 207.2, 0x80808a, 1, 1.5, 327, 1740, 11.35, Main.UNCOMMON), 
	bi( "bismuth", 83, 208.9804, 0x80bc80, 2, 2.5, 271, 1560, 9.75, Main.UNCOMMON), 
	po( "polonium", 84, 209, 0xc6bc44, 2, 0, 254, 962, 9.3, Main.UNCOMMON), 
	at( "astatine", 85, 210, 0x44bc1c, 3, 0, 302, 337, 15, Main.UNCOMMON), 
	rn( "radon", 86, 222, 0xa88080, 3, 0, -71, -62, 9.73, Main.UNCOMMON), 
	fr( "francium", 87, 223, 0xbc8058, 3, 0, 27, 677, 15, Main.UNCOMMON), 
	ra( "radium", 88, 226, 0x00ffff, 2, 0, 700, 1737, 5.5, Main.UNCOMMON), 
	ac( "actinium", 89, 227, 0xebebeb, 3, 0, 1050, 3200, 10.07, Main.UNCOMMON), 
	th( "thorium", 90, 231.0359, 0x505050, 3, 3.0, 1750, 4790, 11.72, Main.UNCOMMON), 
	pa( "protactinium", 91, 232.0381, 0x50fab4, 3, 0, 1568, 8000, 15.4, Main.UNCOMMON), 
	u( "uranium", 92, 237, 0x50ff50, 3, 6.0, 1132, 3818, 18.95, Main.RARE), 
	np( "neptunium", 93, 238.0289, 0x64a0a0, 3, 0, 640, 3902, 20.2, Main.RARE), 
	pu( "plutonium", 94, 243, 0xffb464, 3, 0, 640, 3235, 19.84, Main.RARE), 
	am( "americium", 95, 244, 0xb4a064, 3, 0, 994, 2607, 13.67, Main.UNCOMMON), 
	cm( "curium", 96, 247, 0xe68278, 3, 0, 1340, 8000, 13.5, Main.UNCOMMON), 
	bk( "berkelium", 97, 247, 0xe6b482, 3, 0, 986, 8000, 14.78, Main.UNCOMMON), 
	cf( "californium", 98, 251, 0xe6e682, 3, 0, 900, 8000, 15.1, Main.UNCOMMON), 
	es( "einsteinium", 99, 252, 0x8282e6, 3, 0, 860, 8000, 15, Main.UNCOMMON), 
	fm( "fermium", 100, 257, 0xE6E6E6, 3, 0, 1527, 8000, 15, Main.UNCOMMON), 
	DM( "darkmatter", -1, 10000, 0x000000, 1, 11, 1000000000, 1000000000, 10000, Main.EPIC), 
	EM( "exoticmatter", -2, -1, -1, 1, 20, 1000000000, 1000000000, -1, Main.EPIC), 
	SN( "dev.donator", -3, 0, -2, 1, 15, 1000000000, 1000000000, -1, Main.PLUS), 
	AR( "dev.arideus", -3, 1337, -3, 1, 100, 1000000000, 1000000000, -1, Main.PLUS), 
	MD("dev.madhat", -3, 1337, -4, 1, 100, 1000000000, 1000000000, -1, Main.PLUS), 
	HP("dev.singh", -3, -1, 0x0000FF, 1, 100, 1000000000, 1000000000, -1, Main.PLUS), 
	MR("dev.moor", -3, -1, 0x73059C, 1, 100, 1000000000, 1000000000, -1, Main.PLUS), 
	JA("dev.grundahl", -3, -1, -5, 1, 80, 1000000000, 1000000000, -1, Main.PLUS);
	
	private String Name;
	private int Number;
	private double Mass;
	private int Color;
	private int Type;
	private double Hardness;
	private int BoilingPoint;
	private int MeltingPoint;
	private double Density;
	private int Rarity;

	private Element(String n, int nm, double m, int c, int t, double h, int bp, int mp, double d, EnumRarity r) {
		Name = n;
		Number = nm;
		Mass = m;
		Color = c;
		Type = t;
		Hardness = h;
		BoilingPoint = bp;
		MeltingPoint = mp;
		Density = d;
		Rarity = ElementLib.rarityToInt(r);
		System.out.println(n + " - " + r.rarityName);
	}

	public String getName() {
		return Name;
	}

	public int getNumber() {
		return Number;
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
