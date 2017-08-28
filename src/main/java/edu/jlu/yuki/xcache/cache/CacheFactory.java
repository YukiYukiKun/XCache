package edu.jlu.yuki.xcache.cache;

/**
 * Factory to product <code>ICache</code> instances;
 *
 */
public class CacheFactory {

	/**
	 * get <code>EasyCache</code> instance.
	 * 
	 * @return new instance.
	 */
	public static <K, V> ICache<K, V> getEasyCache(Class<K> clazzK, Class<V> clazzV) {
		return new EasyCache<K, V>();
	}
}
