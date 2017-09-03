package edu.jlu.yuki.xcache.cache;

/**
 * Used by cache to load data if request data not exists in cache. Implemented
 * by user.
 * <b>thread safe</b>
 *
 * @param <K>
 * @param <V>
 */
public interface ILoader<K, V> {
	
	/**
	 * To get data.
	 * 
	 * @param key
	 * @return null if data not exists.
	 */
	public V loadData(K key);
	
}
