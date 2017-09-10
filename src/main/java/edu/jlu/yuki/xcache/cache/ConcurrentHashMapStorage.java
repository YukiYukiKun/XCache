package edu.jlu.yuki.xcache.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple implementation of IStorage by directly wrapping a ConcurrentHashMap.
 * Infinity capacity...
 *
 * @param <K>
 * @param <V>
 */
public class ConcurrentHashMapStorage<K, V> implements IStorage<K, V> {

	private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();

	public V get(K key) {
		return this.map.get(key);
	}

	public V put(K key, V value) {
		return this.map.put(key, value);
	}

	public void clear() {
		this.map.clear();
	}

}
