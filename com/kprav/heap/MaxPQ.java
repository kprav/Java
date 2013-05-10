package com.kprav.heap;

/**
 * 
 * @author kprav
 * 
 *         Priority Queue implementation using Max Heap
 * 
 */

public class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;

	private int N;

	private int capacity;

	// TODO: Implement MaxPQ() constructor with resizing array for industrial
	// strength.

	// Assume client passes size of priority queue.
	public MaxPQ(int capacity) {
		this.capacity = capacity;

		// NOTE: For sake of simplicity, we do not use position 0 of array.
		// Java doesn't allow array creation of generic type. Hence the cast.
		pq = (Key[]) new Comparable[this.capacity + 1];

		// Keep track of current size of priority queue.
		N = 0;
	}

	// Check if priority queue is full
	public boolean isFull() {
		return N == capacity;
	}

	// Check if priority queue is empty
	public boolean isEmpty() {
		return N == 0;
	}

	// Insert new entry into priority queue
	public void insert(Key k) throws Exception {
		if (!isFull()) {
			pq[++N] = k;
			swim(N);
		} else
			throw new Exception(
					"Cannot insert into already full priority queue");
	}

	// Delete max entry from priority queue
	// Max entry is entry at the root i.e. position 1
	public Key delMax() throws Exception {
		if (isEmpty())
			throw new Exception("Cannot delete from an empty priority queue");
		else {
			Key max = pq[1];
			exch(1, N--);
			pq[N + 1] = null;
			sink(1);
			return max;
		}
	}

	// Re-heapify if child key greater than parent key
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	// Re-heapify if parent key less than child key
	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

}
