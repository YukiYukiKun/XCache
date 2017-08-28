package edu.jlu.yuki.xcache.cache;

import junit.framework.TestCase;

public class EasyCacheTest extends TestCase {

	private ICache<String, String> easyCache;

	protected void setUp() throws Exception {
		this.easyCache = CacheFactory.getEasyCache(String.class, String.class);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGet() {
		easyCache.put(new String("test1"), "test1");
		easyCache.put(new String("test2"), "test2");
		assertEquals("test1", easyCache.get("test1"));
		assertEquals("test2", easyCache.get("test2"));
	}

	public void testPut() {
		easyCache.put(new String("test"), "test1");
		final String former = easyCache.put(new String("test"), "test2");
		assertEquals("test1", former);
		assertEquals("test2", easyCache.get("test"));
	}

}
