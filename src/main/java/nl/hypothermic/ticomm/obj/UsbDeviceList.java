package nl.hypothermic.ticomm.obj;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class UsbDeviceList {
	
	private UsbDeviceList() {
		throw new AssertionError();
	}
	
	public UsbDeviceList(final int exitCode, final PointerByReference pref, final IntByReference iref) {
		this.exitCode = exitCode;
		this.pref = pref;
		this.iref = iref;
	}
	
	public int exitCode;
	public PointerByReference pref;
	public IntByReference iref;

}
