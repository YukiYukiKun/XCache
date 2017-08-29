package edu.jlu.yuki.xcache.cache;

import static edu.jlu.yuki.xcache.utils.CheckUtils.checkNotNull;

import java.util.Map;

/**
 * Skeletal implementation of {@code ICache}. Load data if absent, by loader.
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractCacheWithLoader<K, V> implements ICache<K, V> {

	public final ILoader<K, V> loader;

	public AbstractCacheWithLoader(final ILoader<K, V> loader) {
		this.loader = loader;
	}

	public V get(final K key) {
		final Map<K, V> innerMap = this.getInnerMap();

		// Hit the cache. Just return result;
		V result = innerMap.get(checkNotNull(key));
		if (null != result) {
			return result;
		}

		// Result not exists even load by loader. Return null.
		result = this.loader.loadData(key);
		if (null == result) {
			return null;
		}

		// Got new result, and put in into cache
		innerMap.put(key, result);

		return result;
	}

	public V put(final K key, final V value) {
		return this.getInnerMap().put(checkNotNull(key), checkNotNull(value));
	}

	/**
	 * Get the map for caching.
	 * 
	 * @return
	 */
	abstract protected Map<K, V> getInnerMap();

}
