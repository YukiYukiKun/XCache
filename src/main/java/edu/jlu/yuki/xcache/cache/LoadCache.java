package edu.jlu.yuki.xcache.cache;

import static edu.jlu.yuki.xcache.utils.CheckUtils.checkNotNull;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple cache implementation with loader. Thread safety shall be guaranteed by
 * IStorage and ILoader.
 *
 * @param <K>
 * @param <V>
 */
public class LoadCache<K, V> implements ICountingCache<K, V> {

	/**
	 * cache storage.
	 */
	private final IStorage<K, V> storage;

	/**
	 * loader for absent data.
	 */
	private final ILoader<K, V> loader;

	/**
	 * statistic count.
	 */
	private final AtomicLong hitCount = new AtomicLong();
	private final AtomicLong queryCount = new AtomicLong();

	/**
	 * Constructor. 
	 * TODO: Better be used by a builder.
	 * 
	 * @param storage
	 * @param loader
	 */
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
			// Add hit count
			this.hitCount.incrementAndGet();
			this.queryCount.incrementAndGet();

			return value;
		}

		// Then try loading.
		value = this.loader.loadData(key);
		this.storage.put(key, value);

		// Add query count.
		this.queryCount.incrementAndGet();

		return value;
	}

	/*
	 * Put data into cache without persistence.
	 * 
	 * TODO consider data persistence through this method (by loader).
	 */
	public V put(K key, V value) {
		return this.storage.put(checkNotNull(key), checkNotNull(value));
	}

	/*
	 * clear data in storage
	 */
	public void clear() {
		this.storage.clear();
	}

	public long getHitCount() {
		return this.hitCount.get();
	}

	public long getQueryCount() {
		return this.queryCount.get();
	}

	public void clearCounting() {
		this.hitCount.set(0);
		this.queryCount.set(0);
	}

}
