package my.learning.algorithms.sorting;

import java.util.Arrays;

public class BinaryInsertionSort {

	public static void main(String[] args) {
		
		int[] nums = {10,7,3,6,8,12};
		System.out.println(Arrays.toString(nums));
		
		binaryInsertionSort(nums);
		System.out.println(Arrays.toString(nums));		
		
		
		System.out.println(Arrays.binarySearch(new int[] {2,4,6,8,10}, 4));
	}
	
	static void binaryInsertionSort(int[] nums) {
				
		for (int i = 1; i < nums.length; i++) {
			
			int x = nums[i];

			// Find location to insert using binary search
			int j = Math.abs(Arrays.binarySearch(nums, 0, i, x) + 1);

			// Shifting array to one location right
			System.arraycopy(nums, j, nums, j + 1, i - j);

			// Placing element at its correct location
			nums[j] = x;
		}
	}
		
}
