package com.kprav.sorting;

import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author kprav
 * 
 */

public class Quick {

	private static Random random;

	private static long seed;

	static {
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	// Return random integer between 0 (inclusive) and N (exclusive)
	private static int random(int N) {
		return random.nextInt(N);
	}

	// Randomly shuffle the array.
	// Avoid worst case i.e input array is in descending order.
	private static void shuffle(Comparable[] a, int length) {
		for (int i = 0; i < length; i++) {
			int random = i + random(length - i);
			exch(a, i, random);
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static int partition(Comparable[] a, int pivot, int lo, int hi) {
		int i = lo;
		int j = hi + 1;

		while (true) {
			while (less(a[++i], a[pivot]))
				if (i == hi)
					break;

			while (less(a[pivot], a[--j]))
				if (j == lo)
					break;

			if (i >= j)
				break;

			exch(a, i, j);
		}
		exch(a, pivot, j);
		return j;
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;

		// TODO: Performance Improvement 1
		// Cutoff to insertion sort if number of elements <= 10.

		// TODO: Performance Improvement 2
		// Choose pivot to be median of 3 elements - lo, mid and hi
		// Currently just choosing pivot to be first element of sub array.
		int pivot = lo;

		int pivotPosAfterPartition = partition(a, pivot, lo, hi);
		sort(a, lo, pivotPosAfterPartition - 1);
		sort(a, pivotPosAfterPartition + 1, hi);
	}

	public static void sort(Comparable[] a) {
		int length = a.length;
		shuffle(a, length);
		sort(a, 0, length - 1);
	}
}
