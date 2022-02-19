package my.learning.algorithms.arraysStrings.array.problems;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

	public static void main(String[] args) {		
		
		System.out.print(fibRecursive(5));

		System.out.print(fibMemoized(5, new HashMap<>(5)));
		
//		long startTime = System.currentTimeMillis();
//		System.out.print(fibIterative(1000));		
//		System.out.println(" Time taken: " + (System.currentTimeMillis() - startTime));		
//		System.out.println();
//		
//		
//		startTime = System.currentTimeMillis();
//		System.out.print(fibRecursive(30));
//		System.out.println(" Time taken: " + (System.currentTimeMillis() - startTime));
//		System.out.println();
//		 
//		
//		startTime = System.currentTimeMillis();
//		System.out.print(fibMemoized(1000, new HashMap<>(1000)));
//		
//		System.out.println(" Time taken: " + (System.currentTimeMillis() - startTime));
	}
	
	
	/**
	 * 	O(n) time.
	 */
	private static long fibIterative(int n) {
		long prevprev = 0, prev = 0, curr = 1;
		for (int i=1; i < n; i++) {
			prevprev = prev;
			prev = curr;
			curr = prev + prevprev;
		}		
		return curr;
	}
	
	private static long fibRecursive(int n) {		
		System.out.println(String.format("fib(%s)", n));
		if (n < 2)
			return n;		
		return fibRecursive(n - 1) + fibRecursive(n - 2);
	}
	
	
	private static long fibMemoized(int n, Map<Integer, Long> fib) {
		System.out.println(String.format("fib(%s)", n));
		if (n < 2)
			return n;		
		
		Long fibnminus1 = fib.get(n - 1);
		if (fibnminus1 == null) {
			fibnminus1 = fibMemoized(n - 1, fib);
			fib.put(n - 1, fibnminus1);
		}
		
		Long fibnminus2 = fib.get(n - 2);
		if (fibnminus2 == null) {
			fibnminus2 = fibMemoized(n - 2, fib);
			fib.put(n - 2, fibnminus2);
		}		
		return fibnminus1 + fibnminus2;
	}
	
}