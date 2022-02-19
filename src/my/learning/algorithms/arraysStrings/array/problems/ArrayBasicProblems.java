package my.learning.algorithms.arraysStrings.array.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayBasicProblems {

	public static void main(String[] args) {
		
		int[] nums = new int[] { 2, 3, 1, 8, 2, 3, 5, 1 };		
		System.out.println(sumOfConsecutiveNumbers(nums));
		
		nums = new int[] { 2, 3, 1, 8, 2, 3, 5, 1 };
		System.out.println(findMissingNumber(nums));
		
		nums = new int[] { 2, 3, 1, 8, 2, 3, 5, 1 };
		System.out.println(Arrays.toString(findMissingNumbers(nums).toArray()));
	}
	
	/**
	 * 	Arithmetic progression: sum of n numbers :(1 + 2 + 3 + 4 + 5 + ...... + n)
	 * 	
	 * 	sum = (n * (n + 1))/2
	 */
	private static int sumOfConsecutiveNumbers(int[] nums) {
		int n = nums.length;
		return (n * (n + 1))/2;
	}
	
	private static int findMissingNumber(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			if (nums[i] - 1 != i && nums[i] < nums.length) {
				swap(nums, i, nums[i]);
			} else {
				i++;
			}			
		}
		for (int j=0; j < nums.length; j++) {
			if (nums[j] - 1 != j) {
				return j + 1;
			}
		}
		return -1;
	}
	
	private static List<Integer> findMissingNumbers(int[] nums) {
		List<Integer> missingNums = new ArrayList<>();
		int i = 0;
		while (i < nums.length) {
			if (nums[i] != nums[nums[i] - 1]) {
				swap(nums, i, nums[i] - 1);
			} else {
				i++;
			}
		}
		for (int j=0; j < nums.length; j++) {
			if (nums[j] - 1 != j) {
				missingNums.add(j + 1);
			}
		}
		return missingNums;
	}
	
	private static void findDuplicateNumber() {
		
	}
	
	private static void findDuplicateNumbers() {
		
	}
	
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
		
}
