package edu.jlu.yuki.xcache.cache;

import static edu.jlu.yuki.xcache.utils.CheckUtils.checkNotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A super simple implementation of ICache.
 * 
 * @param <K>
 * @param <V>
 */
public class EasyCache<K, V> implements ICache<K, V> {
	private Map<K, V> map = new HashMap<K, V>();

	public V get(K key) {
		return map.get(checkNotNull(key));
	}

	public V put(K key, V value) {
		return map.put(checkNotNull(key), checkNotNull(value));
	}

}
