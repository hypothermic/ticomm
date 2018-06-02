package nl.hypothermic.ticomm.jna;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import nl.hypothermic.ticomm.obj.CableHandle;
import nl.hypothermic.ticomm.util.uint32_t;

public interface INativeLibrary extends Library {
	
	public int ticables_library_init();
	public int ticables_library_exit();
	
	// --- ticables.c ---
	public String ticables_version_get();
	public uint32_t ticables_supported_cables();
	public uint32_t ticables_max_ports();
	//public int ticables_string_to_port(String s);
	//public int ticables_string_to_model(String s);
	public IntByReference ticables_handle_new(int model, int port);
	public int ticables_handle_del(IntByReference handle);
	
	public int ticables_cable_open(IntByReference handle);
	public int ticables_cable_close(IntByReference handle);
	public int ticables_cable_check(IntByReference handle);
	public int ticables_cable_reset(IntByReference handle);
	public int ticables_cable_probe(IntByReference handle, int[] result);
	public int ticables_cable_send(IntByReference handle, byte[] data, uint32_t length);
	public int ticables_cable_put(IntByReference handle, byte data);
	public int ticables_cable_recv(IntByReference handle, byte[] data, uint32_t length);
	public int ticables_cable_get(IntByReference handle, byte data);
	
	public String ticables_get_device(IntByReference handle);
	public int ticables_cable_get_device_info(IntByReference handle, PointerByReference info);
	
	// --- probe.c ---
	public int ticables_is_usb_enabled();
	public int ticables_get_usb_devices(PointerByReference addr, IntByReference pointer);
	public int ticables_free_usb_devices(PointerByReference addr);
		
}
