package com.kprav.LRUCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author kprav
 * 
 */

public class LRUCacheDoublyLinkedList {

	class Node {
		String item;
		Node next;
		Node previous;

		public Node(String item) {
			this.item = item;
			this.next = null;
			this.previous = null;
		}
	}

	class LinkedList {

		private Node first;
		private Node last;

		public LinkedList() {
			first = null;
			last = null;
		}

		private boolean isEmpty() {
			return first == null;
		}

		public Node add(String item) {
			Node oldLast = last;
			last = new Node(item);
			last.next = null;
			last.previous = oldLast;
			if (isEmpty())
				first = last;
			else
				oldLast.next = last;
			return last;
		}

		public String getFirst() {
			if (isEmpty())
				throw new java.util.NoSuchElementException();
			return first.item;
		}

		public void removeFirst() {
			if (isEmpty())
				throw new java.util.NoSuchElementException();
			first = first.next;
			if (first != null)
				first.previous = null;
			if (isEmpty())
				last = null;
		}

		public void remove(Node node) {
			if (node == null)
				throw new java.lang.IllegalArgumentException();

			Node nodePrevious = node.previous;
			Node nodeNext = node.next;

			if (first == node)
				first = nodeNext;

			if (last == node)
				last = nodePrevious;

			if (nodePrevious != null)
				nodePrevious.next = nodeNext;

			if (nodeNext != null)
				nodeNext.previous = nodePrevious;

			node.previous = null;
			node.next = null;
		}
	}

	private LinkedList keys;
	private HashMap<String, String> cache;
	private HashMap<String, Node> track;
	private List<String> listOfKeys;
	private int capacity;
	private int currentSize;

	public LRUCacheDoublyLinkedList(int size) {
		keys = new LinkedList();
		cache = new HashMap<String, String>();
		track = new HashMap<String, Node>();
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
		listOfKeys = new ArrayList<String>(cache.keySet());
		Collections.sort(listOfKeys);
		for (String key : listOfKeys)
			System.out.println(key + " " + cache.get(key));
	}

	private String search(String key) {
		return cache.get(key);
	}

	private void addCacheEntry(String key, String value) {
		cache.put(key, value);
		track.put(key, keys.add(key));
		currentSize++;
	}

	private void updateCacheEntry(String key, String value) {
		cache.put(key, value);
		markCacheEntryAsAccessed(key);
	}

	private void deleteLRUCacheEntry() {
		cache.remove(keys.getFirst());
		track.remove(keys.getFirst());
		keys.removeFirst();
		currentSize--;
	}

	private void markCacheEntryAsAccessed(String key) {
		keys.remove(track.get(key));
		track.put(key, keys.add(key));
	}

	public static void main(String[] args) throws IOException {
		LRUCacheDoublyLinkedList lruCache;
		int size = 0;
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int numOperations = Integer.parseInt(reader.readLine());
		line = reader.readLine();
		String[] input = line.split("\\s");
		size = Integer.parseInt(input[1]);
		lruCache = new LRUCacheDoublyLinkedList(size);
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
