package nl.hypothermic.ticomm.tests;

import org.junit.Test;

import junit.framework.Assert;
import nl.hypothermic.ticomm.NativeLoader;

public class TestBulkNativeLoader {
	
	/**
	 * Here's where the fun begins: messing around with things you shouldn't be doing
	 * We create 100 instances of TiCables and see if the unloader unloads them all
	 * If it doesn't, and you don't have much RAM on your computer, you're screwed.
	 */
	@Test public void BulkNativeLoaderTest() {
		NativeLoader nl = new NativeLoader();
		for (int i = 0; i < 100; i++) {
			nl.load();
		}
		nl.unload();
		int j = nl.load();
		nl.unload();
		Assert.assertEquals(j, 1);
	}
}
