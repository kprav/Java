package com.kprav.sorting;

/**
 * 
 * @author kprav
 * 
 */

public class Merge {

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo,
			int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	private static void sort(Comparable[] a, Comparable aux[], int lo, int hi) {
		if (hi <= lo)
			return;
		
		int mid = lo + (hi - lo) / 2;

		// TODO: Performance Improvement 1
		// Cutoff to insertion sort if number of elements <= 7.

		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);

		// Performance Improvement 2
		// No need to merge if both halves are already sorted.
		// Both halves already sorted if last element of first half <= first
		// element of second half.
		if (!less(a[mid + 1], a[mid]))
			return;

		merge(a, aux, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

}
