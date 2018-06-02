package nl.hypothermic.ticomm.obj;

public enum CableVariant {
	
	UNKNOWN("Unknown"),
	TIGLUSB("TI-92 Voyage 200"),      	     /* CABLE_FAMILY_DBUS */         // [!!!] Note: name may not be correct!
	TI84P("TI-84 Plus"),       				 /* CABLE_FAMILY_USB_TI8X */
	TI84PSE("TI-84 Plus Silver Edition"),    /* CABLE_FAMILY_USB_TI8X */
	TI84PCSE("TI-84 Plus C Silver Edition"), /* CABLE_FAMILY_USB_TI8X */
	TI84PCE("TI-84 Plus CE"),      			 /* CABLE_FAMILY_USB_TI8X */
	TI83PCE("TI-83 Plus CE"),     			 /* CABLE_FAMILY_USB_TI8X */
	TI82A("TI-82"),        					 /* CABLE_FAMILY_USB_TI8X */
	TI89TM("TI-89 Titanium"),       		 /* CABLE_FAMILY_USB_TI9X */
	NSPIRE("TI-Nspire"),       				 /* CABLE_FAMILY_USB_NSPIRE */
	TI84PT("TI-84 Plus T");
	
	private String friendlyName;

	private CableVariant(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	
	public static CableVariant match(int x) {
		switch (x) {
		case 0:
			return UNKNOWN;
		case 1:
			return TIGLUSB;
		case 2:
			return TI84P;
		case 3:
			return TI84PSE;
		case 4: 
			return TI84PCSE;
		case 5:
			return TI84PCE;
		case 6:
			return TI83PCE;
		case 7:
			return TI82A;
		case 8:
			return TI89TM;
		case 9:
			return NSPIRE;
		case 10:
			return TI84PT;
		}
		throw new IllegalArgumentException("Could not match to cablevariant " + x);
	}
	
	@Override public String toString() {
		return this.friendlyName;
	}
}
