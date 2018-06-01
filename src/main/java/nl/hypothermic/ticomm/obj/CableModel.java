package nl.hypothermic.ticomm.obj;

public enum CableModel {
	
	NUL(0),
	GRY(1),
	BLK(2),
	PAR(3),
	SLV(4),
	USB(5),
	VTI(6),
	TIE(7),
	ILP(8),
	DEV(9),
	TCPC(10),
	TCPS(11);
	
	private final int value;
	
	public int getValue() {
		return this.value;
	}
	
	private CableModel(int x) {
		value = x;
	}
	
	public static CableModel match(final int base) {
		switch (base) {
			case 0:
				return NUL;
			case 1:
				return GRY;
			case 2:
				return BLK;
			case 3:
				return PAR;
			case 4:
				return SLV;
			case 5:
				return USB;
			case 6:
				return VTI;
			case 7:
				return TIE;
			case 8:
				return ILP;
			case 9:
				return DEV;
			case 10:
				return TCPC;
			case 11:
				return TCPS;
		}
		throw new IllegalArgumentException("Could not match to cablemodel " + base);
	}
}
