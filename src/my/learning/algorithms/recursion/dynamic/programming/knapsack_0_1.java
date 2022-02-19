package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 	0/1 knapsack problem
 * 	---------------------
 * 
 * 	brute-force algorithm will look like:
 * 
 * 	for each number 'i' 
 *  	
 *  	create a new set which INCLUDES number 'i' if it does not exceed 'S',
 *  		and recursively process the remaining numbers
 *  	
 *  	create a new set WITHOUT number 'i',
 *  		and recursively process the remaining numbers
 * 
 *  return true if any of the above two sets has a sum equal to 'S', otherwise return false
 *  
 */
public class knapsack_0_1 {

	public static void main(String[] args) {
		
		int[] weights = {3, 7, 10, 6}; // weight of items.		
		int[] values = {4, 14, 10, 5}; // value of items.		
		int knapsackCapacity = 20;
		
		
		// recursive solution.
		int maxProfit = knapsack(weights, values, knapsackCapacity, 0);
		System.out.println(String.format("max profit: %s", maxProfit));
		
		
		// memoized solution.
		int[][] cache = new int[knapsackCapacity + 1][values.length + 1];
		Arrays.stream(cache).forEach(row -> Arrays.fill(row, -1));		
		maxProfit = knapsackMemo(weights, values, knapsackCapacity, values.length, cache);
		System.out.println(String.format("max profit: %s", maxProfit));
		
		
		// dynamic programming (iterative - tabulation) solution.
		maxProfit = knapsackDP(weights, values, knapsackCapacity);
		System.out.println(String.format("max profit: %s", maxProfit));		
		
		
		// dp reconstruction.
		maxProfit = knapsackDPReconstruction(weights, values, knapsackCapacity);
		System.out.println(String.format("max profit: %s", maxProfit));
	}
	
	
	/**	
	 * 	recursive solution time complexity: O(2^n) where n is # of items to be considered.
	 */
	private static int knapsack(int[] weights, int[] values, int availableCapacity, int i) {
		
		//System.out.println(String.format("Capacity=%s,i=%s", availableCapacity, i));
		
		// base case.
		if (availableCapacity <= 0 || i == values.length) {
			return 0;
		}
		
		if (weights[i] <= availableCapacity) { // wt of item is <= remaining space (available capacity).
			
			int itemIncludedValue = values[i] + knapsack(weights, values, availableCapacity - weights[i], i + 1);
			
			int itemExcludexValue = knapsack(weights, values, availableCapacity, i + 1);
			
			return Integer.max(itemIncludedValue, itemExcludexValue); // choose the option which gives better value by including/excluding this item.	
		}		
		else { // else go ahead without considering this item.
			
			return knapsack(weights, values, availableCapacity, i + 1);
		}
	}	
	
	/**
	 * 	Memoized recursive solution time complexity: O(n) but space complexity has increased.
	 */
	private static int knapsackMemo(int[] weights, int[] values, int availableCapacity, int i, int[][] cache) {
		
		if (availableCapacity == 0 || i == -1) {
			return 0;
		}
		
		// return value from cache if it exists.
		if (cache[availableCapacity][i] != -1) {			
			return cache[availableCapacity][i];
		}		
		
		
		
		int itemExcludexValue = knapsackMemo(weights, values, availableCapacity, i - 1, cache);
		
		if (weights[i] <= availableCapacity) { // wt of item is <= remaining space.
			
			int itemIncludedValue = values[i] + knapsackMemo(weights, values, availableCapacity - weights[i], i - 1, cache);
			
			cache[availableCapacity][i] = Integer.max(itemIncludedValue, itemExcludexValue);
			return cache[availableCapacity][i];
		}
		
		else { // else go ahead without considering this item.
			
			return itemExcludexValue;
		}
	}
		
	/**
	 * 	tabulation (iterative approach), dynamic programming solution time complexity: O(n^2).
	 */
	private static int knapsackDP(int[] weights, int[] values, int knapsackCapacity) {
		
		int[][] cache = new int[knapsackCapacity + 1][values.length + 1];
		
		for (int i = 1; i <= values.length; i++) {
			
			for (int availableCapacity = 1; availableCapacity <= knapsackCapacity; availableCapacity++) {
				
				if (weights[i - 1] <= availableCapacity) { // wt of item is <= remaining space.
					
					int valueWithItem = values[i - 1] + cache[availableCapacity - weights[i - 1]][i - 1];
					
					int valueWithoutItem = cache[availableCapacity][i - 1];
					
					cache[availableCapacity][i] = Integer.max(valueWithItem, valueWithoutItem);					
				}				
				else { // else go ahead without considering this item.
					
					cache[availableCapacity][i] = cache[availableCapacity][i - 1];
				}
			}			
		}		
		
		return cache[knapsackCapacity][values.length];
	}	
	
	/**
	 * 	dynamic programming reconstruction.
	 */
	private static int knapsackDPReconstruction(int[] weights, int[] values, int knapsackCapacity) {
		
		int[][] cache = new int[knapsackCapacity + 1][values.length + 1];
		boolean[][] decisions = new boolean[knapsackCapacity + 1][values.length + 1];
		
		for (int i = 1; i <= values.length; i++) {
			
			for (int availableCapacity = 1; availableCapacity <= knapsackCapacity; availableCapacity++) {
				
				if (availableCapacity - weights[i - 1] >= 0) {
					
					int itemIncludedValue = values[i - 1] + cache[availableCapacity - weights[i - 1]][i - 1];
					
					int itemExcludexValue = cache[availableCapacity][i - 1];
					
					if (itemIncludedValue > itemExcludexValue) { // update decision. 
						
						decisions[availableCapacity][i] = true;
					}
					
					cache[availableCapacity][i] = Integer.max(itemIncludedValue, itemExcludexValue);					
				}				
				else { // else go ahead without considering this item.
					
					cache[availableCapacity][i] = cache[availableCapacity][i - 1];
				}
			}			
		}		
		
		
		int i = values.length;
		int w = knapsackCapacity;
		
		while (i > 0 && w > 0) {
			
			if (decisions[w][i]) {
				
				System.out.println(String.format("Picked : %s, Weight %s, Value %s", (i - 1), weights[i - 1], values[i - 1]));
				
				w = w - weights[i - 1];
				
				i--;
				
			} else {
				
				i--;
			}			
		}		
		
		return cache[knapsackCapacity][values.length];
	}
	
}
