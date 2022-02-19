package my.learning.algorithms.arraysStrings.array.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		
		int _3Sum = 11;
		int[] nums = new int[] {12, 3, 4, 1, 6, 9, 12, 0, 24};
		List<Integer[]> tripleSumItems = checkForThreeSum(nums, _3Sum);
		tripleSumItems.forEach(triplet -> System.out.println(Arrays.toString(triplet)));
	}

	/**
	 * 	O(nlog(n)) + O(n^2).
	 */
	private static List<Integer[]> checkForThreeSum(int[] nums, int targetSum) {
		
		List<Integer[]> tripleSumItems = new ArrayList<>();
		
		Arrays.sort(nums);
		
		for (int i=0; i < nums.length - 2; i++) {
			
			int l = i + 1;
			int r = nums.length - 1;
			
			while (l < r) {
				
				if (nums[i] + nums[l] + nums[r] == targetSum) { // equals.
					
					tripleSumItems.add(new Integer[] {nums[i], nums[l], nums[r]});
					
					l++;
					r--;
					
				} else if (nums[i] + nums[l] + nums[r] > targetSum) { // greater.
					
					r--;
					
				} else if (nums[i] + nums[l] + nums[r] < targetSum) { // less than.
					
					l++;
				}				
			}
		}		
		
		return tripleSumItems;
	}
	
}
