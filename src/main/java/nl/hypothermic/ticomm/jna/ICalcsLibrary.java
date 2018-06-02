package nl.hypothermic.ticomm.jna;

import com.sun.jna.Library;

public interface ICalcsLibrary extends Library {
	
	public int ticalcs_library_init();
	public int ticalcs_library_exit();
	
	// --- ticalcs.h --- //
	public String ticalcs_version_get();
}
