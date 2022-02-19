package my.learning.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {		
		System.out.println(Arrays.toString(quicksort(new int[]{1,2,3,4,5,6}))); // best case -> O(nlog(n)).
		System.out.println(Arrays.toString(quicksort(new int[]{1,2,3,4,6,5}))); // almost best case -> O(nlog(n)).
		System.out.println(Arrays.toString(quicksort(new int[]{1,2,4,3,6,5}))); // avg case -> O(nlog(n)).
		System.out.println(Arrays.toString(quicksort(new int[]{6,5,4,3,2,1}))); // worst case -> O(n^2).		
	}
	
	private static int[] quicksort(int[] nums) {		
		quicksort(nums, 0, nums.length - 1);
		return nums;
	}

	/**
	 * 	quicksort is an inplace sort. so auxillary space used will be constant O(1).
	 */
	private static void quicksort(int[] nums, int l, int r) {
		
		if (l <  r) {
			
			int partitionIndex = partition(nums, l, r); // after p artition we fix the location of the element at "partitionIndex" in its sort order. 
			
			/**
			 * 	after fixing the element at "partitionIndex" now repeat the same process for 
			 * 	the subarrays left of "partitionIndex" & right of "partitionIndex".  
			 */
						
			quicksort(nums, l, partitionIndex - 1); // repeating quicksort process for sub array left of "partitionIndex".
			
			quicksort(nums, partitionIndex + 1, r); // repeating quicksort process for sub array right of "partitionIndex".
		}	
	}
	
	/**
	 * 	returns the partitionIndex.
	 */
	private static int partition(int[] nums, int l, int r) {
		
		//int pivotIndex = findPivotIndex(nums, l, r); // choice of pivotal index.
		
		int pivotIndex = r; // here we choose pivotal index as the right most index of the array.
		
		r--;
		
		while (l <= r) {
			
			if (nums[l] > nums[pivotIndex] && nums[r] < nums[pivotIndex]) {
				
				swap(nums, l, r);
				
				l++;
				r--;
				
			} else {
			
				if (nums[l] < nums[pivotIndex]) {
					l++;
				}
				
				if (nums[r] > nums[pivotIndex]) {
					r--;
				}
			}			
		}
		
		swap(nums, l, pivotIndex);
		
		return l;
	}

	/**	
	 * 	multiple ways to choose pivotal index:
	 * 		
	 * 		1) right most index,
	 * 		2) left most index,
	 * 		3) random index,
	 * 		4) median etc...
	 */
	static int findPivotIndex(int[] nums, int l, int r) {
		
		int m = l + (r - l)/2;
		
		return m;
	}
	
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
}
