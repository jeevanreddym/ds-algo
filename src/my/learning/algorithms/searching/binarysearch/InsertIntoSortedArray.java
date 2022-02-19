package my.learning.algorithms.searching.binarysearch;

import java.util.Arrays;

public class InsertIntoSortedArray {

	public static void main(String[] args) {
		
		
		int[] nums = new int[20]; 
		nums[0] = 12; 
		nums[1] = 16; 
		nums[2] = 20; 
		nums[3] = 40; 
		nums[4] = 50; 
		nums[5] = 70; 
						
		insertNum(nums, 0, 5, 26);
		System.out.println(Arrays.toString(nums));
	}

	static void insertNum(int[] nums, int from, int to, int k) {
				
		int l = from;
		int r = to;
				
		//int[] ltArr = Arrays.copyOfRange(nums, l, m + 1);		
		//int[] rtArr = Arrays.copyOfRange(nums, m + 1, r + 1);
	
		while (l < r) {
			
			int m = l + (r - l) / 2;
			
			if (k > nums[m]) {
				
				l = m + 1;
				
			} else {
				
				r = m - 1;
			}
			
		}
				
		for (int i = to + 1; i > l; i--) {
			nums[i] = nums[i - 1];
		}
		
		nums[l] = k;		
	}
	
}
