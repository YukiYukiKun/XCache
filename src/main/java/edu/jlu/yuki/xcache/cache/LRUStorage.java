package edu.jlu.yuki.xcache.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUStorage<K, V> implements IStorage<K, V> {

	/**
	 * Capacity of storage.
	 */
	private final int capacity;

	/**
	 * Map to storage data.
	 */
	private Map<K, V> map = new HashMap<K, V>();

	/**
	 * List to record access order.
	 */
	private LinkedList<K> AccessOrderList = new LinkedList<K>();

	/**
	 * This lock is used to protect AccessOrderList.
	 */
	private Lock lock = new ReentrantLock();

	public LRUStorage(final int capacity) {
		this.capacity = capacity;
	}

	public V get(K key) {
		this.lock.lock();
		try {
			this.updateAccessOrder(key);
			return this.map.get(key);
		} finally {
			this.lock.unlock();
		}
	}

	public V put(K key, V value) {
		this.lock.lock();
		try {
			this.updateAccessOrder(key);
			return this.map.put(key, value);
		} finally {
			this.lock.unlock();
		}
	}

	public void clear() {
		this.lock.lock();
		try {
			this.map.clear();
			this.AccessOrderList.clear();
		} finally {
			this.lock.unlock();
		}
	}

	/**
	 * Update key position in AccessOrderList. if it's a key already in
	 * AccessOrderList, move it to the head of AccessOrderList. if not, add it
	 * to the head of AccessOrderList.
	 * 
	 * Lock shall be held before use.
	 * 
	 * @param key
	 */
	private void updateAccessOrder(final K key) {
		final int oldIndex = this.AccessOrderList.indexOf(key);
		if (oldIndex == -1) {
			this.AccessOrderList.addFirst(key);
			this.checkAndEvictTail();
		} else {
			this.AccessOrderList.remove(oldIndex);
			this.AccessOrderList.addFirst(key);
		}
	}

	/**
	 * If current size bigger than set capacity, evict the tail key.
	 * 
	 * Lock shall be held before use.
	 */
	private void checkAndEvictTail() {
		if (this.AccessOrderList.size() > this.capacity) {
			final K tailKey = this.AccessOrderList.removeLast();
			this.map.remove(tailKey);
		}
	}

}
