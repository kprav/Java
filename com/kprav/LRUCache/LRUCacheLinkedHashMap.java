package com.kprav.LRUCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 
 * @author kprav
 * 
 */

public class LRUCacheLinkedHashMap {

	private LinkedList<String> keys;
	private LinkedHashMap<String, String> cache;
	private String[] arrayOfKeys;
	private int capacity;
	private int currentSize;

	public LRUCacheLinkedHashMap(int size) {
		keys = new LinkedList<String>();
		cache = new LinkedHashMap<String, String>(1000000);
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
		arrayOfKeys = new String[keys.size()];
		arrayOfKeys = keys.toArray(arrayOfKeys);
		Arrays.sort(arrayOfKeys);
		for (String key : arrayOfKeys)
			System.out.println(key + " " + cache.get(key));
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
		LRUCacheLinkedHashMap lruCache;
		int size = 0;
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int numOperations = Integer.parseInt(reader.readLine());
		line = reader.readLine();
		String[] input = line.split("\\s");
		size = Integer.parseInt(input[1]);
		lruCache = new LRUCacheLinkedHashMap(size);
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
