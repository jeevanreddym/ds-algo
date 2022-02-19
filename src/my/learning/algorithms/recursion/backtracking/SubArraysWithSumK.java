package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubArraysWithSumK {

	public static void main(String[] args) {
		
		int[] nums = { 10, 1, 2, 7, 7, 6, 5, 2 };
		int targetSum = 14;
				
		List<List<Integer>> kSumSubArrays = combinationSum(nums, targetSum);
		kSumSubArrays.forEach(row -> System.out.println(Arrays.toString(row.toArray())));		
	}

	private static List<List<Integer>> combinationSum(int[] nums, int targetSum) {		
		List<List<Integer>> kSumSubArrays = new ArrayList<>();
		Arrays.sort(nums);
		combinationSum(nums, targetSum, 0, new ArrayList<>(), 0, kSumSubArrays);
		return kSumSubArrays;
	}
	
	private static void combinationSum(int[] nums, int targetSum, int runningSum, List<Integer> partial, int start, List<List<Integer>> subarrays) {		
		
		if (targetSum == runningSum) {
			subarrays.add(new ArrayList<>(partial));
			return;
		}
		
		if (start == nums.length) {
			return;
		}
		
		for (int i = start; i < nums.length; i++) {
		
			/**
			 * 	skip execution if sum exceeds the target sum by including the current element 
			 *  (or) if the same number repeats again (duplicate).
			 */			
			if (runningSum + nums[i] <= targetSum && (i - 1 < 0 || nums[i] != nums[i - 1])) {
				
				partial.add(nums[i]);
				
				combinationSum(nums, targetSum, runningSum + nums[i], partial, i + 1, subarrays);
				
				partial.remove(partial.size() - 1);
			}			
		}
				
	}	
	
}
