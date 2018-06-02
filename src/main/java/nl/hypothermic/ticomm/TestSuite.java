package nl.hypothermic.ticomm;

import java.util.Arrays;

import nl.hypothermic.ticomm.NativeLoader.CableClosedException;
import nl.hypothermic.ticomm.obj.CableHandle;
import nl.hypothermic.ticomm.obj.CableModel;
import nl.hypothermic.ticomm.obj.CablePort;
import nl.hypothermic.ticomm.obj.CableStatus;

/**
 * Test Suite - do not use in production environment.
 */

public class TestSuite {
	
	public static void main(String[] args) throws CableClosedException {
		NativeLoader nl = new NativeLoader();
		nl.load();
		System.out.println("Initialized TICables v" + nl.getCablesVersion());
		System.out.println("Initialized TICalcs v" + nl.getCablesVersion());
		if (!nl.isUsbEnabled()) {
			System.out.println("USB not supported on this system, exiting");
			return;
		}
		System.out.println(nl.getUsbDevices().exitCode == 0 ? "TI calculator detected!" : "No TI calculators detected");
		CableHandle handle = nl.createHandle(CableModel.USB, CablePort.PORT1);
		System.out.println(nl.openCable(handle));
		System.out.println(nl.getIdentifier(handle));
		System.out.println(nl.getDeviceInfo(handle));
		if (nl.getCableStatus(handle) == CableStatus.IDLE) {
			byte[] data = new byte[] {0x08, 0x68, 0x00, 0x00};
			System.out.println("SEND=" + nl.sendData(handle, data));
			System.out.println(Arrays.toString(nl.receiveData(handle, data.length)));
			System.out.println("OUT=" + Arrays.toString(data));
		}
		System.out.println(nl.closeCable(handle));
		nl.deleteHandle(handle);
		nl.unload();
	}
}
