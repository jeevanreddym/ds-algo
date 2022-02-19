package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {
				
		int[] array = new int[] {1,3,7,9,2};
		
		int targetSum = 11;
				
		System.out.println(Arrays.toString(findTwoSumIndeces(array, targetSum)));
		
		System.out.println(Arrays.toString(findTwoSumIndecesOptimal(array, targetSum)));
	}
	
	/**
	 * 	O(n^2) time & O(1) space.
	 */
	private static int[] findTwoSumIndeces(int[] array, int targetSum) {
		
		for (int i=0; i < array.length - 1; i++) {
			
			for (int j = i + 1; j < array.length; j++) {
				
				if (array[i] + array[j] == targetSum) {
					
					return new int[] {i, j};
				}
			}
		}		
		return new int[] {-1, -1};
	}
	
	/**
	 * 	O(n) time & O(n) space.
	 */
	private static int[] findTwoSumIndecesOptimal(int[] nums, int targetSum) {
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i=0; i < nums.length; i++) {
			
			if (map.containsKey(targetSum - nums[i])) {
				
				return new int[] {map.get(targetSum - nums[i]), i};
				
			} else {
				
				map.put(nums[i], i);
			}
		}		
		return new int[] {-1, -1};
	}
	
}
