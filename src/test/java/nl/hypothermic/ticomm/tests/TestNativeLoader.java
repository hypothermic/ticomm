package nl.hypothermic.ticomm.tests;

import org.junit.Test;

import junit.framework.Assert;
import nl.hypothermic.ticomm.NativeLoader;

public class TestNativeLoader {
	
	/**
	 * Try to load and unload TiCables
	 */
	@Test public void NativeLoaderTest() {
		NativeLoader nl = new NativeLoader();
		nl.load();
		nl.unload();
	}
}
