package my.learning.algorithms.recursion;

import java.util.Arrays;

public class FindMaxNumberInArray {

	public static void main(String[] args) {
		
		int[] arr = {4,3,6,9,2,5};
		
		int[] maxArr = {Integer.MIN_VALUE};
				
		
		max(arr, arr.length - 1, maxArr);
		System.out.println(Arrays.toString(maxArr));
		
		
		int maxNum = Integer.MIN_VALUE;
		maxNum = max(arr, arr.length - 1, maxNum);
		System.out.println(maxNum);
		
		
		maxNum = max(arr, arr.length - 1);
		System.out.println(maxNum);
	}

	private static void max(int[] arr, int i, int[] maxArr) {
		
		if (i < 0)
			return;
		
		if (arr[i] > maxArr[0]) {
			maxArr[0] = arr[i];
		}
		
		max(arr, i - 1, maxArr);
	}
	
	private static int max(int[] arr, int i, int maxNum) {
		
		if (i < 0)
			return maxNum;
		
		if (arr[i] > maxNum) {
			maxNum = arr[i];
		}
		
		return max(arr, i - 1, maxNum);
	}
	
	private static int max(int[] arr, int i) {
		
		if (i == 0)
			return arr[0];
		
		return Integer.max(arr[i], max(arr, i - 1));
	}
	
}
