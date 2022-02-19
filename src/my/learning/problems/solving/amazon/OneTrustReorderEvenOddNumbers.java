package my.learning.problems.solving.amazon;

import java.util.Arrays;

/**
 * Question 1: Given an array of integers (arbitrary size of array and distribution of elements), 
 * reorder the array such that all the even numbers come before any of the odds. 
 * 
 * Question 1.a: Do in place without creating extrabuffer.
 */
public class OneTrustReorderEvenOddNumbers {

	public static void main(String[] args) {
		int[] nums = { 2, 5, 6, 7, 1, 8 };		
		reOrder(nums);
		System.out.println(Arrays.toString(nums));
	}

	private static void reOrder(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return;
		}
		
		int l = 0, r = nums.length - 1;
		while (l < r) {
			if (isOdd(nums[l]) && isEven(nums[r])) {
				swap(nums, l, r);
				l++;
				r--;
			} else {
				while (isEven(nums[l]) && l < r) {
					l++;
				}
				while (isOdd(nums[r]) && l < r) {
					r--;
				}				
			}
		}
	}
	
	private static boolean isEven(int num) {
		return num % 2 == 0;
	}
	
	private static boolean isOdd(int num) {
		return num % 2 != 0;
	}

	private static void swap(int[] nums, int l, int r) {
		int temp = nums[l];
		nums[l] = nums[r];
		nums[r] = temp;
	}
	
}