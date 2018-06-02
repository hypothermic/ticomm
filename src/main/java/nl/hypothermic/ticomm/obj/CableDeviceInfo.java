package nl.hypothermic.ticomm.obj;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class CableDeviceInfo extends Structure {

	public CableDeviceInfo() {
		
	}
	
	public CableDeviceInfo(Pointer p) {
		super(p);
		read();
	}
	
	public int family;
	public int variant;
	
	public CableFamily getFamily() {
		return CableFamily.match(family);
	}
	
	public CableVariant getVariant() {
		return CableVariant.match(variant);
	}
	
	@Override protected List<String> getFieldOrder() {
		return Arrays.asList("family", "variant");
	}

	@Override public String toString() {
		return "CableDeviceInfo [family=" + this.getFamily().toString() + ", variant=" + this.getVariant().toString() + "]";
	}
}
