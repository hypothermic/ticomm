package nl.hypothermic.ticomm.obj;

import com.sun.jna.ptr.IntByReference;

public class CableHandle {
	
	public IntByReference iref;
	
	public CableHandle(IntByReference iref) {
		this.iref = iref;
	}
}
