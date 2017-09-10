package edu.jlu.yuki.xcache.cache;

/**
 * Empty implementation of ILoader. Always return null.
 *
 */
public class EmptyLoader<K, V> implements ILoader<K, V> {

	public V loadData(K key) {
		return null;
	}
}
