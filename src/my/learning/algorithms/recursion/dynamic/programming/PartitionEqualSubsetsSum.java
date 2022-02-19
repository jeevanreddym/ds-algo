package my.learning.algorithms.recursion.dynamic.programming;


/**
 * 	Given a non-empty array nums containing only positive integers, 
 * 	find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

	Example 1:	
		Input: nums = [1,5,11,5]
		Output: true
		Explanation: The array can be partitioned as [1, 5, 5] and [11].
		
	Example 2:	
		Input: nums = [1,5,11,5]
		Output: false
		Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 */
public class PartitionEqualSubsetsSum {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3,5};
		
		boolean canPartition = canPartition(nums);
		System.out.println(canPartition);
	}

	private static boolean canPartition(int[] nums) {		
		if (nums == null || nums.length <= 1) {
			return false;
		}
		
		int totalSum = 0;
		for (int num: nums) {
			totalSum += num;
		}
		
		if (totalSum % 2 != 0) {
			return false;
		}
		
		return canPartition(nums, 0, 0, totalSum/2);
	}
	
	private static boolean canPartition(int[] nums, int i, int runningSum, int halfSum) {		
		
		if (runningSum == halfSum) {
			return true;
		}
		
		if (i >= nums.length || runningSum > halfSum) {
			return false;
		}
		
		return canPartition(nums, i + 1, runningSum + nums[i], halfSum) // with item. 
				|| canPartition(nums, i + 1, runningSum, halfSum); // without item.
	}
	
}
