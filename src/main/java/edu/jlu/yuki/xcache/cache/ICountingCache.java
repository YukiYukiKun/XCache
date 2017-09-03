package edu.jlu.yuki.xcache.cache;

/**
 * ICache extension interface with counting for statistics.
 *
 */
public interface ICountingCache<K, V> extends ICache<K, V> {

	/**
	 * return total cache hit count.
	 * 
	 * @return cache hit count
	 */
	public long getHitCount();

	/**
	 * return total cache query count.
	 * 
	 * @return cache query count
	 */
	public long getQueryCount();

	/**
	 * clear counting.
	 */
	public void clearCounting();
}
