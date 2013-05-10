package com.kprav.sorting;

/**
 * 
 * @author kprav
 * 
 */

public class Heap {

	public static void sort(Comparable[] a) {
		int N = a.length;

		for (int k = (N - 1) / 2; k >= 0; k--) {
			sink(a, k, N);
		}
		while (N > 0) {
			exch(a, 0, --N);
			sink(a, 0, N);
		}
	}

	private static void sink(Comparable[] a, int k, int N) {
		while (2 * k < N) {
			int j = 2 * k;
			if (j + 1 < N && less(a[j], a[j + 1]))
				j++;
			if (!less(a[k], a[j]))
				break;
			exch(a, k, j);
			k = j;
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
