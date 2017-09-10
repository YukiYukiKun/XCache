package edu.jlu.yuki.xcache.cache;

public class LoadCacheBuilder<K, V> {
	IStorage<K, V> storage = null;
	ILoader<K, V> loader = new EmptyLoader<K, V>();
	int capacity = 10; // default capacity

	/**
	 * Build a LoadCache with user defined storage. setCapacity dosen't work if
	 * use this method to build.
	 * 
	 * @return LoadCache instance
	 */
	public LoadCache<K, V> buildLoadCache() {
		if (null == this.storage) {
			throw new RuntimeException("build failed, storage haven't been set!");
		}
		return new LoadCache<K, V>(this);
	}

	/**
	 * Build a LoadCache with ConcurrentHashMapStorage.
	 * 
	 * @return LoadCache instance
	 */
	public LoadCache<K, V> buildHashMapLoadCache() {
		this.setStorage(new ConcurrentHashMapStorage<K, V>());
		return new LoadCache<K, V>(this);
	}

	/**
	 * Build a LoadCache with LRUStorage.
	 * 
	 * @param capacity
	 * @return
	 */
	public LoadCache<K, V> buildLRULoadCache() {
		this.setStorage(new LRUStorage<K, V>(capacity));
		return new LoadCache<K, V>(this);
	}

	/**
	 * Set IStorage provided by user.
	 * 
	 * @param storage
	 */
	public void setStorage(final IStorage<K, V> storage) {
		this.storage = storage;
	}

	/**
	 * Set ILoader provided by user.
	 * 
	 * @param loader
	 */
	public void setLoader(final ILoader<K, V> loader) {
		this.loader = loader;
	}

	/**
	 * Set capacity of IStorage;
	 * 
	 * @param capacity
	 */
	public void setCapacity(final int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
}
