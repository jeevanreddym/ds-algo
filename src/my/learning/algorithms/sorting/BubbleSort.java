package my.learning.algorithms.sorting;

import java.util.Arrays;

/**
 * 	O(n^2) time & O(1) space.
 */
public class BubbleSort {

	public static void main(String[] args) {
		
		int[] nums = {10,7,3,6,8,12};
		System.out.println(Arrays.toString(nums));
		BubbleSort sorter = new BubbleSort();
		sorter.bubbleSort(nums);
		System.out.println(Arrays.toString(nums));		
	}
	
	public void bubbleSort(int[] nums) {
		for (int i=0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					swap(nums, i, j);
				}
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
}
