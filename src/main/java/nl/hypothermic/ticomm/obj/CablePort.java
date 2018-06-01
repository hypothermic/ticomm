package nl.hypothermic.ticomm.obj;

public enum CablePort {
	
	PORT0(0),
	PORT1(1),
	//PORT_FIRST(1),
	PORT2(2),
	PORT3(3),
	PORT4(4);
	//PORT_MAX(4);
	
	private final int value;
	
	public int getValue() {
		return this.value;
	}
	
	private CablePort(int x) {
		this.value = x;
	}
	
	public static CablePort match(final int base) {
		switch (base) {
			case 0:
				return PORT0;
			case 1:
				return PORT1;
			case 2:
				return PORT2;
			case 3:
				return PORT3;
			case 4:
				return PORT4;
		}
		throw new IllegalArgumentException("Could not match to cableport " + base);
	}
}
