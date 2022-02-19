package my.learning.algorithms.recursion;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {

	public static void main(String[] args) {
				
		int n = 7;
		
		System.out.println(fib(n, n));
		
		System.out.println(fibMemoized(n, new HashMap<>(), n));
		
		System.out.println(String.format("fib(%s)=%s", n, fibIterative(n)));		
	}
	
	/**
	 * 	fib(n) = fib(n - 1) + fib(n - 2)
	 */
	static long fib(int n, int factFor) {
		
		if (n == 0 || n == 1) {
			System.out.println(String.format("calculating fib(%s) for fib(%s)", n, factFor));
			System.out.println(String.format("fib(%s)=%s", n, 1));
			return 1;
		}
				
		System.out.println(String.format("calculating fib(%s) for fib(%s)", n, factFor));
		long fibVal = fib(n - 1, n) + fib(n - 2, n);		
		System.out.println(String.format("fib(%s)=%s", n, fibVal));
		
		return fibVal;
	}
	
	/**
	 * 	fib(n) = fib(n - 1) + fib(n - 2)
	 */
	static long fibMemoized(int n, Map<Integer, Long> map, int factFor) {
		
		if (n == 0 || n == 1) {
			System.out.println(String.format("calculating fib(%s) for fib(%s)", n, factFor));
			System.out.println(String.format("fib(%s)=%s", n, 1));
			return 1;
		}
		
		if (!map.containsKey(n)) {		
			System.out.println(String.format("calculating fib(%s) for fib(%s)", n, factFor));
			map.put(n, fibMemoized(n - 1, map, n) + fibMemoized(n - 2, map, n));
			System.out.println(String.format("fib(%s)=%s", n, map.get(n)));
		}
		
		return map.get(n);
	}
	
	
	private static int fibIterative(int n) {
		
		int[] fib = new int[n + 1]; 
		fib[0] = 0;
		fib[1] = 1;
		
		for (int i = 2; i <= n; i++) {			
			fib[i] = fib[i - 1] + fib[i - 2];			
		}
		
		return fib[n];
	}
	
}
