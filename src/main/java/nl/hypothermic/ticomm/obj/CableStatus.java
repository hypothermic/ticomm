package nl.hypothermic.ticomm.obj;

public enum CableStatus {
	
	IDLE(0),
	RECEIVING(1),
	TRANSMITTING(2);
	
	private final int value;
	
	public int getValue() {
		return this.value;
	}
	
	private CableStatus(int x) {
		this.value = x;
	}
	
	public static CableStatus match(final int base) {
		switch (base) {
			case 0:
				return IDLE;
			case 1:
				return RECEIVING;
			case 2:
				return TRANSMITTING;
		}
		throw new IllegalArgumentException("Could not match to cablestatus " + base);
	}
}
