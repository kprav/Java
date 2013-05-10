package com.kprav.LRUCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author kprav
 * 
 */

public class LRUCacheTreeSet {

	private HashMap<String, CacheEntry> cacheEntryMap;
	private TreeMap<String, String> cacheMap;
	private TreeSet<CacheEntry> cacheTree;
	private Comparator<CacheEntry> lruOrder;
	private CacheEntry cacheEntry;
	private int capacity;
	private int currentSize;
	private long track;

	private class CacheEntry {
		String key;
		long trackUsage;

		public CacheEntry(String key) {
			this.key = key;
			this.trackUsage = track;
			track++;
		}
	}

	public class LRUOrder implements Comparator<CacheEntry> {
		@Override
		public int compare(CacheEntry o1, CacheEntry o2) {
			if (o1.trackUsage < o2.trackUsage)
				return -1;
			else if (o1.trackUsage > o2.trackUsage)
				return 1;
			else
				return 0;
		}
	}

	public LRUCacheTreeSet(int size) {
		lruOrder = new LRUOrder();
		cacheTree = new TreeSet<CacheEntry>(lruOrder);
		cacheMap = new TreeMap<String, String>();
		cacheEntryMap = new HashMap<String, CacheEntry>();
		capacity = size;
		currentSize = 0;
		track = 0;
	}

	public void resizeCache(int size) {
		if (size < capacity) {
			int numDel = currentSize - size;
			if (numDel > 0)
				for (int i = 0; i < numDel; i++) {
					deleteLRUCacheEntry();
				}
		}
		capacity = size;
	}

	public void set(String key, String value) {
		if (capacity == 0)
			return;
		String val = search(key);
		cacheEntry = createCacheEntry(key);
		if (val == null) {
			if (currentSize < capacity) {
				addCacheEntry(key, value);
			} else {
				deleteLRUCacheEntry();
				addCacheEntry(key, value);
			}
		} else {
			updateCacheEntry(key, value);
		}
	}

	public void get(String key) {
		String val = search(key);
		if (!(val == null)) {
			cacheEntry = createCacheEntry(key);
			markCacheEntryAsAccessed(key);
		} else
			val = "NULL";
		System.out.println(val);
	}

	public void peek(String key) {
		String val = search(key);
		if (val == null)
			val = "NULL";
		System.out.println(val);
	}

	public void dump() {
		for (Entry<String, String> entry : cacheMap.entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
	}

	private String search(String key) {
		return cacheMap.get(key);
	}

	private CacheEntry createCacheEntry(String key) {
		CacheEntry cacheEntry = new CacheEntry(key);
		return cacheEntry;
	}

	private void addCacheEntry(String key, String value) {
		cacheTree.add(cacheEntry);
		cacheMap.put(key, value);
		cacheEntryMap.put(key, cacheEntry);
		currentSize++;
	}

	private void updateCacheEntry(String key, String value) {
		cacheTree.remove(cacheEntryMap.get(key));
		cacheTree.add(cacheEntry);
		cacheMap.put(key, value);
		cacheEntryMap.put(key, cacheEntry);
	}

	private void deleteLRUCacheEntry() {
		String keyToDelete = cacheTree.first().key;
		cacheMap.remove(keyToDelete);
		cacheEntryMap.remove(keyToDelete);
		cacheTree.remove(cacheTree.first());
		currentSize--;
	}

	private void markCacheEntryAsAccessed(String key) {
		cacheTree.remove(cacheEntryMap.get(key));
		cacheTree.add(cacheEntry);
		cacheEntryMap.put(key, cacheEntry);
	}

	public static void main(String[] args) throws IOException {
		LRUCacheTreeSet lruCache;
		int size = 0;
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int numOperations = Integer.parseInt(reader.readLine());
		line = reader.readLine();
		String[] input = line.split("\\s");
		size = Integer.parseInt(input[1]);
		lruCache = new LRUCacheTreeSet(size);
		for (int i = 1; i < numOperations; i++) {
			line = reader.readLine();
			input = line.split("\\s");
			if (input[0].equals("BOUND"))
				lruCache.resizeCache(Integer.parseInt(input[1]));
			else if (input[0].equals("SET"))
				lruCache.set(input[1], input[2]);
			else if (input[0].equals("GET"))
				lruCache.get(input[1]);
			else if (input[0].equals("PEEK"))
				lruCache.peek(input[1]);
			else if (input[0].equals("DUMP"))
				lruCache.dump();
		}
		reader.close();
	}
}
