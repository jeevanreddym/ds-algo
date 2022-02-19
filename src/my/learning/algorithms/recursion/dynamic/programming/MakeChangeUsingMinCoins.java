package my.learning.algorithms.recursion.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * 
 * 	You are given coins of different denominations and a total amount of money amount. 
 * 	Write a function to compute the fewest number of coins that you need to make up that amount. 
 * 	If that amount of money cannot be made up by any combination of the coins, return -1.

	You may assume that you have an infinite number of each kind of coin.
	
	Example 1:	
		Input: coins = [1,2,5], amount = 11
		Output: 3
		Explanation: 11 = 5 + 5 + 1
	
	Example 2:	
		Input: coins = [2], amount = 3
		Output: -1
		
	Example 3:
		Input: coins = [1], amount = 0
		Output: 0
		
	Example 4:
		Input: coins = [1], amount = 1
		Output: 1
		
	Example 5:
		Input: coins = [1], amount = 2
		Output: 2
 *
 *	
 */
public class MakeChangeUsingMinCoins {

	
	public static void main(String[] args) {
		
		//int[] bases = {1, 5, 10, 25};	// change in cents.		
		int[] bases = {1, 2};	// change in cents.		
		int totalChange = 4;
		
//		List<List<Integer>> allPossibleChanges = allPossibleChanges(bases, totalChange);
//		allPossibleChanges.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
//		
//		System.out.println("\n\n");
//		
//		int minNoOfCoins = makeChangeAlternative(bases, totalChange);
//		System.out.println("Min change: " + minNoOfCoins);
		
		makeChangeRecursive(bases, totalChange);
	}
	
	
	/**
	 * 	all combinations.
	 */
	private static int makeChangeRecursive(int[] bases, int currTotal, int currPos, int[] cache, List<Integer> partial, List<List<Integer>> res) {
		
		if (currTotal == 0) {
			res.add(new ArrayList<>(partial));
			return 1;		
		}
		
		if (currTotal < 0) return 0;
		
		if (cache[currTotal] != -1) {
			//return cache[currTotal];
		}
		
		int totalCombinations = 0;
		for (int i = currPos; i < bases.length; i++) {
			
			int currBase = bases[i];
			
			partial.add(currBase);
			
			totalCombinations += makeChangeRecursive(bases, currTotal - currBase, i, cache, partial, res);
			
			cache[currTotal] = totalCombinations;
			
			partial.remove(partial.size() - 1);
		}		
		
		return totalCombinations;
	}
	
	/**
	 * 	all permutations.
	 */
	private static int makeChangeRecursive(int[] bases, int currTotal, int[] cache, List<Integer> partial, List<List<Integer>> res) {
		
		if (currTotal == 0) {
			res.add(new ArrayList<>(partial));
			return 1;		
		}
		
		if (currTotal < 0) return 0;
		
		if (cache[currTotal] != -1) {
			//return cache[currTotal];
		}
		
		int totalCombinations = 0;
		for (int i = 0; i < bases.length; i++) {
			int base = bases[i];
			partial.add(base);
			totalCombinations += makeChangeRecursive(bases, currTotal - base, cache, partial, res);
			cache[currTotal] = totalCombinations;
			partial.remove(partial.size() - 1);
		}		
		
		return totalCombinations;
	}
	
	/**
	 * 	recursive: all permutations & combinations.
	 */
	private static void makeChangeRecursive(int[] bases, int totalChange) {
		List<List<Integer>> res = new ArrayList<>(); // final result.
		
		int[] cache = new int[totalChange + 1]; // dp : cache. 
		Arrays.fill(cache, -1);
		
		int noOfPermutations = makeChangeRecursive(bases, totalChange, cache, new LinkedList<>(), res);
		System.out.println("# of permutations: " + noOfPermutations);
		res.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		
		System.out.println("\n");
		res = new ArrayList<>();
		int noOfCombinations = makeChangeRecursive(bases, totalChange, 0, cache, new LinkedList<>(), res);
		System.out.println("# of combinations: " + noOfCombinations);
		res.forEach(row -> System.out.println(Arrays.toString(row.toArray())));		
	}
	
	
	
	
	
	
	
	private static int allPossibleChanges(int[] bases, int currChange, int[] cache, List<Integer> partial, List<List<Integer>> allPossibleChanges) {
		
		if (currChange == 0) {
			allPossibleChanges.add(new ArrayList<>(partial));
			return 0;
		}
		
		int minNoOfCoins = Integer.MAX_VALUE; // to make change 'currChange'.
		
		for (int base: bases) {
			
			int newChange = currChange - base;
			
			if (newChange >= 0) {
				
				partial.add(base);
				
				int currNoOfCoins = 0;				
				if (cache[newChange] != -1) { // dp -> memoization technique to avoid doing duplicate work by caching previous results for later use. 					
					currNoOfCoins = cache[newChange];										
				} else {
					currNoOfCoins = allPossibleChanges(bases, newChange, cache, partial, allPossibleChanges) + 1;
					cache[newChange] = currNoOfCoins; // min # coins needed to make 'newChange'.
				}
				
				partial.remove(partial.size() - 1);		
				
				if (currNoOfCoins < minNoOfCoins) {
					minNoOfCoins = currNoOfCoins;
				}		
			}			
		}	
		
		return minNoOfCoins;
	}
		
	private static List<List<Integer>> allPossibleChanges(int[] bases, int totalChange) {
		
		List<List<Integer>> allPossibleChanges = new ArrayList<>(); // final result.
		
		int[] cache = new int[totalChange + 1]; // dp : cache. 
		Arrays.fill(cache, -1);
		
		int minnumOfCoinsToMakeChange = allPossibleChanges(bases, totalChange, cache, new LinkedList<>(), allPossibleChanges);
		System.out.println(String.format("min # of coins to make %s = %s", totalChange, minnumOfCoinsToMakeChange));
		
		return allPossibleChanges;
	}
	
	
	private static int getMinChangeIterative(int[] bases, int totalChange) {
		
		int[] minCoinsForChange = new int[totalChange];
		minCoinsForChange[0] = 0;
		
		for (int changeFor = 1; changeFor <= totalChange; changeFor++) {
					
			int minNoOfCoins = Integer.MAX_VALUE;
			
			for (int coinBase: bases) {
				
				if (changeFor - coinBase >= 0) {
					
					int currNoOfCoins = 0;
					if (minNoOfCoins < currNoOfCoins) {
						minNoOfCoins = currNoOfCoins;
					}
				}			
			}
			minCoinsForChange[changeFor] = minNoOfCoins;
		}		
		return minCoinsForChange[totalChange];
	}
	
}
