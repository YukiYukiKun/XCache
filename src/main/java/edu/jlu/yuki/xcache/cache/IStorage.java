package edu.jlu.yuki.xcache.cache;

/**
 * Storage interface that used by ICache. Store cache data.
 *
 */
public interface IStorage<K, V> {

	/**
	 * get the value from cache.
	 * 
	 * @param key
	 * @return null if required value dosn't exist.
	 */
	public V get(K key);

	/**
	 * put the value into cache.
	 * 
	 * @param key
	 * @param value
	 * @return replaced value
	 */
	public V put(K key, V value);

}
