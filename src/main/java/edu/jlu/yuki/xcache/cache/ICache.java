package edu.jlu.yuki.xcache.cache;

public interface ICache<K, V> {

	/**
	 * get the value from cache.
	 * 
	 * @param key
	 * @return null if required value dosn't exist.
	 * @throws NullPointerException
	 *             if key is null
	 */
	public V get(K key);

	/**
	 * put the value into cache.
	 * 
	 * @param key
	 * @param value
	 * @return replaced value
	 * @throws NullPointerException
	 *             if key or value is null
	 */
	public V put(K key, V value);
	
	/**
	 * clear up cached data.
	 */
	public void clear();
}
