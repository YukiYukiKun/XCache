package edu.jlu.yuki.xcache.cache;

import static edu.jlu.yuki.xcache.utils.CheckUtils.checkNotNull;

/**
 * Simple cache implementation with loader.
 *
 * @param <K>
 * @param <V>
 */
public class LoadCache<K, V> implements ICache<K, V> {

	private final IStorage<K, V> storage;

	private final ILoader<K, V> loader;

	LoadCache(final IStorage<K, V> storage, final ILoader<K, V> loader) {
		this.storage = checkNotNull(storage);
		this.loader = checkNotNull(loader);
	}

	/*
	 * Get cached value. Try Loading if data is absent.
	 * 
	 * TODO add refreshing or cache retirement.
	 */
	public V get(K key) {
		V value;
		// Try getting from storage first.
		value = this.storage.get(checkNotNull(key));
		if (null != value) {
			return value;
		}

		// Then try loading.
		value = this.loader.loadData(key);

		return value;
	}

	/*
	 * Put data into cache.
	 * 
	 * TODO consider data persistence through this method (by loader).
	 */
	public V put(K key, V value) {
		return this.storage.put(checkNotNull(key), checkNotNull(value));
	}

}
