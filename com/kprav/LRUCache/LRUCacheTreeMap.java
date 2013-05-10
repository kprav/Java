package com.kprav.LRUCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * @author kprav
 * 
 */

public class LRUCacheTreeMap {

	private LinkedList<String> keys;
	private TreeMap<String, String> cache;
	private int capacity;
	private int currentSize;

	public LRUCacheTreeMap(int size) {
		keys = new LinkedList<String>();
		cache = new TreeMap<String, String>();
		capacity = size;
		currentSize = 0;
	}

	public void resizeCache(int size) {
		if (size < capacity) {
			int numDel = currentSize - size;
			if (numDel > 0)
				for (int i = 0; i < numDel; i++)
					deleteLRUCacheEntry();
		}
		capacity = size;
	}

	public void set(String key, String value) {
		if (capacity == 0)
			return;
		String val = search(key);
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
		if (!(val == null))
			markCacheEntryAsAccessed(key);
		else
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
		for (Entry<String, String> entry : cache.entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
	}

	private String search(String key) {
		return cache.get(key);
	}

	private void addCacheEntry(String key, String value) {
		cache.put(key, value);
		keys.add(key);
		currentSize++;
	}

	private void updateCacheEntry(String key, String value) {
		keys.remove(key);
		cache.put(key, value);
		keys.add(key);
	}

	private void deleteLRUCacheEntry() {
		cache.remove(keys.getFirst());
		keys.removeFirst();
		currentSize--;
	}

	private void markCacheEntryAsAccessed(String key) {
		keys.remove(key);
		keys.add(key);
	}

	public static void main(String[] args) throws IOException {
		LRUCacheTreeMap lruCache;
		int size = 0;
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int numOperations = Integer.parseInt(reader.readLine());
		line = reader.readLine();
		String[] input = line.split("\\s");
		size = Integer.parseInt(input[1]);
		lruCache = new LRUCacheTreeMap(size);
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
