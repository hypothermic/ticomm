package nl.hypothermic.ticomm;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import nl.hypothermic.ticomm.jna.ICalcsLibrary;
import nl.hypothermic.ticomm.jna.INativeLibrary;
import nl.hypothermic.ticomm.obj.CableDeviceInfo;
import nl.hypothermic.ticomm.obj.CableHandle;
import nl.hypothermic.ticomm.obj.CableModel;
import nl.hypothermic.ticomm.obj.CablePort;
import nl.hypothermic.ticomm.obj.CableStatus;
import nl.hypothermic.ticomm.obj.UsbDeviceList;
import nl.hypothermic.ticomm.util.uint32_t;

public class NativeLoader {
	
	/**
	 * Create a new NativeLoader.
	 */
	public NativeLoader() {
		;
	}
	
	private INativeLibrary lib;
	private ICalcsLibrary calcs;
	
	/**
	 * Load the library and initialize the TiCables internals
	 * @return instance count
	 */
	public int load() {
		if (lib == null) {
			lib = (INativeLibrary) Native.loadLibrary("libticables2", INativeLibrary.class);
			calcs = (ICalcsLibrary) Native.loadLibrary("libticalcs2", ICalcsLibrary.class);
		}
		return lib.ticables_library_init();
	}
	
	/**
	 * Check if the native library is loaded
	 * @return true if the native library is loaded, false if not
	 */
	public boolean isLoaded() {
		return lib != null && calcs != null;
	}
	
	/**
	 * Unloads and releases resources for all TiCables instances.
	 */
	public void unload() {
		// Unload TiCables instances
		int loadedSessions = 1;
		while (loadedSessions > 0) {
			loadedSessions = lib.ticables_library_exit();
		}
		// Unload native lib from JNA
		NativeLibrary.getInstance("libticables2.so").dispose();
		NativeLibrary.getInstance("libticalcs2.so").dispose();
		lib = null;
	}
	
	/**
	 * Get version of the TiCables library as "X.Y.Z"
	 * @return version as String
	 */
	public String getCablesVersion() {
		return lib.ticables_version_get();
	}
	
	/**
	 * Get version of the TiCalcs library as "X.Y.Z"
	 * @return version as String
	 */
	public String getCalcsVersion() {
		return calcs.ticalcs_version_get();
	}
	
	/**
	 * Get which cables are supported by the TiCables library
	 * @return uint32_t containing binary
	 */
	public uint32_t getSupportedCables() {
		return lib.ticables_supported_cables();
	}
	
	/**
	 * Get the maximum number of ports supported for any given cable.
	 * @return maximum number of ports as uint32_t
	 */
	public uint32_t getCableMaxPorts() {
		return lib.ticables_max_ports();
	}
	
	/**
	 * Check if USB support is availible
	 * @return maximum number of ports as uint32_t
	 */
	public boolean isUsbEnabled() {
		return lib.ticables_is_usb_enabled() != 0;
	}
	
	/**
	 * Get usb devices.
	 * @return if usb device search succeeded and device was found
	 */
	public UsbDeviceList getUsbDevices() {
		PointerByReference pref = new PointerByReference();
		IntByReference iref = new IntByReference();
		UsbDeviceList out = new UsbDeviceList(lib.ticables_get_usb_devices(pref, iref),
											  pref,
											  iref);
		lib.ticables_free_usb_devices(pref);
		return out;
	}
	
	/**
	 * Get CablePort instance from name
	 * @param name for example "#1"
	 * @return CablePort instance
	 */
	//public CablePort getCablePort(String name) {
	//	return CablePort.match(lib.ticables_string_to_port(name));
	//}
	
	/**
	 * Get CableModel instance from name
	 * @param name for example "usb"
	 * @return CableModel instance
	 */
	//public CableModel getCableModel(String name) {
	//	System.out.println(lib.ticables_string_to_port(name));
	//	return CableModel.match(lib.ticables_string_to_port(name));
	//}
	
	/**
	 * Create a Cable Handle
	 * @param model CableModel instance
	 * @param port CablePort instance
	 * @return CableHandle instance
	 */
	public CableHandle createHandle(CableModel model, CablePort port) {
		return new CableHandle(lib.ticables_handle_new(model.getValue(), port.getValue()));
	}
	
	/**
	 * Delete the Cable Handle
	 * @param handle CableHandle instance
	 * @return True if delete succeeded, false if not
	 */
	public void deleteHandle(CableHandle handle) {
		lib.ticables_handle_del(handle.iref);
	}
	
	/**
	 * Open the Cable
	 * @param handle CableHandle instance
	 * @return True if open succeeded, false if not
	 */
	public boolean openCable(CableHandle handle) {
		return lib.ticables_cable_open(handle.iref) == 0;
	}
	
	/**
	 * Close the Cable
	 * @param handle CableHandle instance
	 * @return True if close succeeded, false if not
	 */
	public boolean closeCable(CableHandle handle) {
		return lib.ticables_cable_close(handle.iref) == 0;
	}
	
	/**
	 * Probe the Cable
	 * @param handle CableHandle instance
	 * @return True if cable is present, false if not
	 */
	public boolean probeCable(CableHandle handle) {
		// TODO: memory leak ???
		int[] result = new int[1];
		return lib.ticables_cable_probe(handle.iref, result) == 0 && result[0] != 0;
	}
	
	/**
	 * Send data from PC to hand-held
	 * @param handle CableHandle instance
	 * @return True if data sent, false if not
	 */
	public boolean sendData(CableHandle handle, byte[] data) {
		return lib.ticables_cable_send(handle.iref, data, new uint32_t(data.length)) == 0;
	}
	
	/**
	 * Send one byte of data from PC to hand-held
	 * @param handle CableHandle instance
	 * @return True if data sent, false if not
	 */
	public boolean sendData(CableHandle handle, byte data) {
		return lib.ticables_cable_put(handle.iref, data) == 0;
	}
	
	/**
	 * Receive data from hand-held to PC
	 * @param handle CableHandle instance
	 * @return byte[] with data
	 */
	public byte[] receiveData(CableHandle handle, int length) {
		byte[] data = new byte[length];
		lib.ticables_cable_recv(handle.iref, data, new uint32_t(data.length));
		return data;
	}
	
	/**
	 * Receive one byte of data from hand-held to PC
	 * @param handle CableHandle instance
	 * @return byte with data
	 */
	public byte receiveData(CableHandle handle) {
		byte data = 0;
		lib.ticables_cable_get(handle.iref, data);
		return data;
	}
	
	/**
	 * Check if cable is open
	 * @param handle CableHandle instance
	 * @return True if cable is open, false if closed
	 */
	public boolean isCableOpen(CableHandle handle) {
		int status = lib.ticables_cable_check(handle.iref);
		return status == 0 || status == 1 || status == 2;
	}
	
	/**
	 * Get current cable status for handle
	 * @param handle CableHandle instance
	 * @return CableStatus instance
	 * @throws CableClosedException 
	 */
	public CableStatus getCableStatus(CableHandle handle) throws CableClosedException {
		if (!isCableOpen(handle)) {
			throw new CableClosedException();
		}
		return CableStatus.match(lib.ticables_cable_check(handle.iref));
	}
	
	/**
	 * Get identifier for handle
	 * @param handle CableHandle instance
	 * @return String with identifier
	 * @throws CableClosedException 
	 */
	public String getIdentifier(CableHandle handle) throws CableClosedException {
		if (!isCableOpen(handle)) {
			throw new CableClosedException();
		}
		return lib.ticables_get_device(handle.iref);
	}
	
	/**
	 * Get identifier for handle
	 * @param handle CableHandle instance
	 * @return String with identifier
	 * @throws CableClosedException 
	 */
	public String getDeviceInfo(CableHandle handle) throws CableClosedException {
		if (!isCableOpen(handle)) {
			throw new CableClosedException();
		}
		PointerByReference irf = new PointerByReference();
		lib.ticables_cable_get_device_info(handle.iref, irf);
		return new CableDeviceInfo(irf.getPointer()).toString();
	}
	
	public static class LoaderException extends java.lang.Exception {
		
		public LoaderException() {
			super();
		}
		
		public LoaderException(String msg) {
			super(msg);
		}
	}
	
	/**
	 * Thrown when an operation is requested while the cable is closed.
	 */
	public static class CableClosedException extends java.lang.Exception {
		
		public CableClosedException() {
			super();
		}
	}
}
