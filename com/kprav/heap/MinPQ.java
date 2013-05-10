package com.kprav.heap;

/**
 * 
 * @author kprav
 * 
 *         Priority Queue implementation using Min Heap
 * 
 */

public class MinPQ<Key extends Comparable<Key>> {

	private Key[] pq;

	private int N;

	private int capacity;

	// TODO: Implement MinPQ() constructor with resizing array for industrial
	// strength.

	// Assume client passes size of priority queue.
	public MinPQ(int capacity) {
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

	// Delete min entry from priority queue
	// Min entry is entry at the root i.e. position 1
	public Key delMin() throws Exception {
		if (isEmpty())
			throw new Exception("Cannot delete from an empty priority queue");
		else {
			Key min = pq[1];
			exch(1, N--);
			pq[N + 1] = null;
			sink(1);
			return min;
		}
	}

	// Re-heapify if child key less than parent key
	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	// Re-heapify if parent key greater than child key
	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean greater(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

}
