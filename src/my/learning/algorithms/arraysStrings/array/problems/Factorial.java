package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Map;

public class Factorial {
	
	public static void main(String[] args) {
		
		
		System.out.println(factorialRecursive(10));	
		
		
		
		//System.out.println(factorialMemoized(30, new HashMap<>(30)));
		
		//System.out.println(factorialRecursive(30));		
		
		//System.out.println(factorialTailRecursive(30));	
		
		//System.out.println(factorialIterative(30));	
	}
	
	private static long factorialIterative(int n) {
		return 0;
	}
	
	private static long factorialRecursive(int n) {
		System.out.println(n);
		if (n == 0)
			return 1; 
		return n * factorialRecursive(n - 1);
	}
	
	private static long factorialMemoized(int n, Map<Integer, Long> fact) {
		if (n == 0)
			return 1;
		
		Long nMinusOneFact = fact.get(n - 1);
		if (nMinusOneFact == null) {
			nMinusOneFact = factorialMemoized(n - 1, fact);
			fact.put(n - 1, nMinusOneFact);
		}
		return n * nMinusOneFact;
	}
	 
	/**
	 * 	The tail recursive function is considered better than non tail recursive function
	 * 	as tail-recursion can be optimized by compiler. since the recursive call is the last statement, 
	 * 	there is nothing left to do in the current function, so saving the current function�s stack frame is of no use.
	 */
	private static long factorialTailRecursive(int n, int a) {
		if (n == 0)  
            return a;
        return factorialTailRecursive(n - 1, n * a);
	}
	
	// A wrapper over factorialTailRecursive.
	private static long factorialTailRecursive(int n) {
		return factorialTailRecursive(n, 1); 
	}
		
}
