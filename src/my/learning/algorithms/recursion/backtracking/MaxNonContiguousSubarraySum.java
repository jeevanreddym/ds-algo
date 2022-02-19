package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 	Max Sum Non Adjacent. 
 */
public class MaxNonContiguousSubarraySum {

	public static void main(String[] args) {
		
		//int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		
		int[] arr = { -1, 4, -3, 8 };
		
		long maxSum = findMaxNonContiguousSubarraySum(arr);
		System.out.println("Max subarray sum: " + maxSum);		
	}
	
	/**
	 * 	O(n) time complexity and O(1) space complexity.
	 * 	recurrence relation -> 
	 * 		max(max sum till (i-2) + arr[i], max sum till (i-1))
	 */
	private static long findMaxNonContiguousSubarraySum(int[] arr) {
		
		List<Integer> path = new ArrayList<>();
		
		List<Map<Integer, Integer>> dp = new ArrayList<>(arr.length);
		for (int i=0; i < arr.length; i++) {
			dp.add(i, new HashMap<>());
		}
		
		List<List<Integer>> allPaths = new ArrayList<>();
		
		int maxSum = maxSumSubarraySum(arr, 0, 0, dp, path, allPaths);
		
		
		allPaths.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		//System.out.println(Arrays.toString(path.toArray()));		
		
		return maxSum;
	}
	
	private static int maxSumSubarraySum(int[] arr, int i, int runningSum, List<Map<Integer, Integer>> dp, List<Integer> partial, List<List<Integer>> allPaths) {
		
		if (i == arr.length) { // base case.
			
			allPaths.add(new ArrayList<>(partial));
			
			return runningSum;
		}
		
		
		if (!dp.get(i).containsKey(runningSum)) {
						
			// with i.
			partial.add(arr[i]);
			int sumWithItem = maxSumSubarraySum(arr, i + 1, runningSum + arr[i], dp, partial, allPaths);
					
			// without i.	
			partial.remove(partial.size() - 1);
			int sumWithoutItem = maxSumSubarraySum(arr, i + 1, runningSum, dp, partial, allPaths);
			
			dp.get(i).put(runningSum, Integer.max(sumWithItem, sumWithoutItem));
			
			
//			if (sumWithItem > sumWithoutItem) {
//				path.add(arr[i]);
//			}
		}
				
		return dp.get(i).get(runningSum);		
	}
	
}
