package my.learning.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
	
		int[] nums = {10,6,3,2,4,5,2};
				
		MergeSort sorter = new MergeSort();
		int[] sorted = sorter.mergesort(nums);
		System.out.printf("Sorted array: %s%n", Arrays.toString(sorted));
	}

	private int[] mergesort(int[] nums) {
		return splitToHalf(nums);
	}
	
	private int[] splitToHalf(int[] nums) {
		
		if (nums.length == 1)
			return nums;
		
		int l = 0;
		int r = nums.length - 1;
		
		int mid = (l + r)/2;
		
		int[] leftArr = new int[mid + 1];
		for (int i=0; i <= mid; i++) {
			leftArr[i] = nums[i];
		}
		
		int[] rtArr = new int[r - mid];
		for (int i = mid + 1, k=0; i <= r; i++, k++) {
			rtArr[k] = nums[i];
		}
		
		leftArr = splitToHalf(leftArr);
		rtArr = splitToHalf(rtArr);
		
		return mergeSortedArrays(leftArr, rtArr);		
	}
	
	/**
	 * 	Merge 2 sorted arrays in O(n) time complexity.
	 */
	private int[] mergeSortedArrays(int[] ltArr, int[] rtArr) {
		
		int[] merged = new int[ltArr.length + rtArr.length];
		
		int i=0,j=0,k=0;
		
		while (i < ltArr.length && j < rtArr.length) {
			
			if (ltArr[i] <= rtArr[j]) {
			
				merged[k++] = ltArr[i++];	
			
			} else if (ltArr[i] > rtArr[j]) {
				
				merged[k++] = rtArr[j++];	
			}			
		}
		
		while (i < ltArr.length) {
			merged[k++] = ltArr[i++];				
		}
		
		while (j < rtArr.length) {
			merged[k++] = rtArr[j++];
		}		
		
		return merged;
	}
}