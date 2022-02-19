package my.learning.algorithms.recursion;

import java.util.*;

public class SumOfDigits {

	public static void main(String[] args) {
		
		int num = 123456;
		
		int sumOfDigits = sumOfDigite(num);
		System.out.println(sumOfDigits);
				
		
		int[] digits = returnDigits(num);
		System.out.println(Arrays.toString(digits));
	}

	private static int sumOfDigite(int num) {
		
		if (num <= 0) {
			return 0;
		}
						
		int reminder = num % 10;
		
		int quotient = num / 10;
				
		return reminder + sumOfDigite(quotient);
	}
	
	private static int[] returnDigits(int num) {
		
		List<Integer> digits = new ArrayList<>();
		
		returnDigits(num, digits);
				
		int[] nums = digits.stream().mapToInt(i->i).toArray();
		
		return reverse(nums);
	}
	
	private static void returnDigits(int num, List<Integer> digits) {
		
		if (num <= 0) {			
			return;
		}
						
		int reminder = num % 10;
		digits.add(reminder);
		
		int quotient = num / 10;
				
		returnDigits(quotient, digits);
	}
	
	static int[] reverse(int[] nums) {
		reverse(nums, 0, nums.length - 1);
		return nums;
	}
	
	static void reverse(int[] nums, int i, int j) {
		
		if (i >= j) {
			return;
		}
		
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
		
		reverse(nums, i + 1, j - 1);
	}
	
}
