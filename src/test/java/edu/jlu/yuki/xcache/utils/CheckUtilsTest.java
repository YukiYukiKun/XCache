package edu.jlu.yuki.xcache.utils;

import junit.framework.TestCase;

public class CheckUtilsTest extends TestCase {

	public void testCheckNotNull() {
		final int result1 = CheckUtils.checkNotNull(0);
		assertEquals(0, result1);

		try {
			CheckUtils.checkNotNull(null);
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
			return;
		}
		fail("no exception thrown");
	}
}
