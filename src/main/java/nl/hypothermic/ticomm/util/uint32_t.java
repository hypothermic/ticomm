package nl.hypothermic.ticomm.util;

import com.sun.jna.IntegerType;

// Source: https://www.mkammerer.de/blog/jna-and-unsigned-integers/
public class uint32_t extends IntegerType {
	
	public uint32_t() {
        this(0);
    }

    public uint32_t(int value) {
        super(4, value, true);
    }
    
    public int toInteger() {
    	return super.intValue();
    }
}
