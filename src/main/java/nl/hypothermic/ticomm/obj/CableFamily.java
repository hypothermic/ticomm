package nl.hypothermic.ticomm.obj;

public enum CableFamily {
	
	UNKNOWN,
	DBUS,          /* Traditional TI link protocol */
	USB_TI8X,      /* Direct USB for TI-84 Plus, CSE, CE, etc. */
	USB_TI9X,      /* Direct USB for TI-89 Titanium */
	USB_NSPIRE; /* Direct USB for TI-Nspire series */

	private CableFamily() {
		;
	}
	
	public static CableFamily match(int x) {
		switch (x) {
		case 0:
			return UNKNOWN;
		case 1:
			return DBUS;
		case 2:
			return USB_TI8X;
		case 3:
			return USB_TI9X;
		case 4:
			return USB_NSPIRE;
		}
		throw new IllegalArgumentException("Could not match to cablefamily " + x);
	}
}
