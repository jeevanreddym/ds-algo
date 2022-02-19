package my.learning.algorithms.recursion.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 	m = target sum & n is size of array.
 */
public class TargetSum {

	public static void main(String[] args) {
		
		int targetSum = 100;
		int[] arr = {1,2,5,25};
		
		
		System.out.println(canSum(targetSum, arr));	
		System.out.println(canSumMemo(targetSum, arr));	
		
		//System.out.println("\nPermutations:\n---------------");
		//howSum(targetSum, arr).forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		
		System.out.println("\nBest Sum:\n--------------------");
		System.out.println(Arrays.toString(bestSum(targetSum, arr).toArray()));
		
		System.out.println("\nBest Sum Memo:\n--------------------");
		System.out.println(Arrays.toString(bestSumMemo(targetSum, arr).toArray()));
	}
	
	/**
	 * 	brute force: O(n^m) time & O(m) space.
	 */
	private static boolean canSum(int targetSum, int[] arr) {
		if (targetSum == 0) return true;
		if (targetSum < 0) return false;
		
		for (int num: arr) {
			
			boolean foundAPath = canSum(targetSum - num, arr);			
			if (foundAPath) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 	memoized: O(m * n) time & O(m) space.
	 */
	private static boolean canSumMemo(int targetSum, int[] arr) {
		Map<Integer, Boolean> dp = new HashMap<>();
		return canSumMemo(targetSum, arr, dp);
	}
	private static boolean canSumMemo(int targetSum, int[] arr, Map<Integer, Boolean> dp) {
		
		if (dp.containsKey(targetSum)) return dp.get(targetSum);
		
		if (targetSum == 0) return true;
		if (targetSum < 0) return false;
		
		for (int num: arr) {
			
			boolean foundAPath = canSumMemo(targetSum - num, arr, dp);
			dp.put(targetSum, foundAPath);
		
			if (foundAPath) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 	Return the permutations of elements of the number array thats equal to the target sum. 
	 * 	brute force: O(n^m * m) time & O(m) space.
	 */
	private static List<List<Integer>> howSum(int targetSum, int[] arr) {		
		List<Integer> partial = new ArrayList<>();
		List<List<Integer>> results = new ArrayList<>();		
		howSum(targetSum, arr, partial, results);		
		return results;
	}
	
	private static void howSum(int targetSum, int[] arr, List<Integer> partial, List<List<Integer>> results) {
		
		if (targetSum == 0) {
			results.add(new ArrayList<>(partial));
			return;
		}
		
		if (targetSum < 0) return;
		
		
		for (int num: arr) {
						
			partial.add(num);
			
			howSum(targetSum - num, arr, partial, results);			
			
			partial.remove(partial.size() - 1);
		}
	}
	
	/**
	 * 	Return the combination of elements of the number array thats equal to the target sum. 
	 * 	brute force: O(n^m * m) time & O(m) space.
	 */
//	private static List<List<Integer>> howSumCombinations(int targetSum, int[] arr) {		
//		List<Integer> partial = new ArrayList<>();
//		List<List<Integer>> results = new ArrayList<>();		
//		howSumCombinations(targetSum, arr, 0, partial, results);		
//		return results;
//	}
//	
//	private static void howSumCombinations(int targetSum, int[] arr, int start, List<Integer> partial, List<List<Integer>> results) {
//		
//		if (targetSum == 0) {
//			results.add(new ArrayList<>(partial));
//			return;
//		}
//		
//		if (targetSum < 0) return;
//		
//		
//		for (int i = start; i < arr.length; i++) {
//			
//			int num = arr[i];
//						
//			partial.add(num);
//			
//			howSumCombinations(targetSum - num, arr, start + 1, partial, results);			
//			
//			partial.remove(partial.size() - 1);
//		}
//	}
	
	
	
	
	
	/**
	 * 	still WIP as I have not understood how to cache a whole sub tree in the "dp" map.
	 */
	private static List<List<Integer>> howSumMemo(int targetSum, int[] arr) {		
		List<Integer> partial = new ArrayList<>();
		List<List<Integer>> results = new ArrayList<>();	
		Map<Integer, List<Integer>> dp = new HashMap<>();
		howSumMemo(targetSum, arr, dp, partial, results);		
		return results;
	}
	
	private static void howSumMemo(int targetSum, int[] arr, Map<Integer, List<Integer>> dp, List<Integer> partial, List<List<Integer>> results) {
		
		if (dp.containsKey(targetSum)) {
			dp.get(targetSum);
			// partial.add();
			return;
		}
			
		
		if (targetSum == 0) {
			results.add(new ArrayList<>(partial));
			return;
		}
		
		if (targetSum < 0) return;
		
		
		for (int num: arr) {
						
			partial.add(num);
			
			howSumMemo(targetSum - num, arr, dp, partial, results);			
			
			partial.remove(partial.size() - 1);
		}		
	}
	
	
	
	/**
	 * 	return the array containing the shortest combination of numbers that add up to targetSum.
	 * 	O(n^m * m) time, O(m^2) space.  
	 */
	private static List<Integer> bestSum(int targetSum, int[] arr) {		
		List<Integer> partial = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		bestSum(targetSum, arr, 0, partial, result);
		return result;
	}
	
	private static void bestSum(int targetSum, int[] arr, int start, List<Integer> partial, List<Integer> result) {
		
		if (targetSum == 0 && (result.isEmpty() || partial.size() < result.size())) {
			result.clear();
			result.addAll(partial);
			return;
		}
		
		if (targetSum < 0) return;
		
		
		for (int i = start; i < arr.length; i++) {
			
			int num = arr[i];
			
			partial.add(num);
			
			bestSum(targetSum - num, arr, start + 1, partial, result);
			
			partial.remove(partial.size() - 1);			
		}		
	}
			
	private static List<Integer> bestSumAlternative(int targetSum, int[] arr) {
				
		if (targetSum == 0) {						
			return new ArrayList<>();
		}
		
		if (targetSum < 0) return null;
		
		
		List<Integer> shortestCombination = null;
		
		for (int i = 0; i < arr.length; i++) {
			
			int num = arr[i];
			
			List<Integer> combination = bestSumMemo(targetSum - num, arr);
			if (combination != null) {
				
				List<Integer> targetSumCombination = new ArrayList<>();
				targetSumCombination.addAll(combination);
				targetSumCombination.add(num);
				
				if (shortestCombination == null || (targetSumCombination != null && targetSumCombination.size() < shortestCombination.size())) {
					shortestCombination = new ArrayList<>(targetSumCombination);
				}
			}						
		}		
		
		return shortestCombination;
	}
	
	
	/**
	 * 	O(n * m^2) time, O(m^2) space.  
	 */
	private static List<Integer> bestSumMemo(int targetSum, int[] arr) {
		Map<Integer, List<Integer>> dp = new HashMap<>();
		List<Integer> result = bestSumMemo(targetSum, arr, dp);
		return result;
	}
	
	private static List<Integer> bestSumMemo(int targetSum, int[] arr, Map<Integer, List<Integer>> dp) {
		
		if (dp.containsKey(targetSum)) {			
			return dp.get(targetSum);
		}
		
		if (targetSum == 0) {						
			return new ArrayList<>();
		}
		
		if (targetSum < 0)
			return null;
			
		
		
		List<Integer> shortestCombination = null;
		
		for (int i = 0; i < arr.length; i++) {
			
			int num = arr[i];
			
			List<Integer> targetSumCombination = bestSumMemo(targetSum - num, arr, dp);
			if (targetSumCombination != null) {
				
				targetSumCombination.add(num);
				
				if (shortestCombination == null || (targetSumCombination != null && targetSumCombination.size() < shortestCombination.size())) {
					shortestCombination = new ArrayList<>(targetSumCombination);
				}
			}						
		}		
		
		dp.put(targetSum, shortestCombination);		
		return shortestCombination;
	}
	
}
