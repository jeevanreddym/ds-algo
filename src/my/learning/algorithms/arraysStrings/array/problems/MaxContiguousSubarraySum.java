package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;



/**
 * 
 * 	@MinimumWindowSubstring
 */
public class MaxContiguousSubarraySum {

	public static void main(String[] args) {
		
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		
		long maxSum = findMaxContiguousSubarraySum_1(arr);
//		System.out.println("Max contiguous subarray sum: " + maxSum);
//		
//		maxSum = findMaxContiguousSubarraySum_2(arr);
//		System.out.println("Max contiguous subarray sum: " + maxSum);		
		
		arr = new int []{ -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		maxSum = findMaxContiguousSubarraySum_3(arr);
		System.out.println("Max contiguous subarray sum: " + maxSum);		
		
//		int[] maxSumSubarrayRange = findMaxContiguousSubarrayLength_1(arr);
//		System.out.println(String.format("Max contiguous subarray length [%s,%s]: %s", 
//				maxSumSubarrayRange[0], maxSumSubarrayRange[1], (maxSumSubarrayRange[1] - maxSumSubarrayRange[0] + 1)));
//		
//		arr = new int []{ -2, 1, -3, 4, -1, 2, 1, -5, 4 };
//		maxSumSubarrayRange = findMaxContiguousSubarrayLength_2(arr);
//		System.out.println(String.format("Max contiguous subarray length [%s,%s]: %s", 
//				maxSumSubarrayRange[0], maxSumSubarrayRange[1], (maxSumSubarrayRange[1] - maxSumSubarrayRange[0] + 1)));
//		
//		System.out.println("Maximum sum of a subarray of size K: "
//		        + findMaxSumSubArrayOfSizeK(new int[] { 2, 1, 5, 1, 3, 2 }, 3));
//				
//	    System.out.println("Smallest subarray length: " + findMinSubArray(new int[] { 2, 1, 5, 2, 3, 2 }, 7));
	}

	/**
	 * 	O(n^3) time - brute force technique.
	 */
	private static long findMaxContiguousSubarraySum_1(int[] arr) {
		
		long maxConteguousSubarraySum = Long.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = i; j < arr.length; j++) {
				
				long currSum = 0;
				
				for (int k = i; k <= j; k++) {
					
					currSum += arr[k];					
				}				
				//System.out.println(currSum);
								
				if (currSum > maxConteguousSubarraySum) {
					maxConteguousSubarraySum = currSum;
				}				
			}			
		}		
		return maxConteguousSubarraySum;
	}
	
	/**
	 * 	O(n^2) time - using sliding window technique.
	 */
	private static long findMaxContiguousSubarraySum_2(int[] arr) {
		
		long maxConteguousSubarraySum = Long.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			
			long currSum = 0;
			
			for (int j = i; j < arr.length; j++) {
				
				currSum += arr[j];
				
				if (currSum > maxConteguousSubarraySum) {
					maxConteguousSubarraySum = currSum; 
				}
			}			
		}
		
		return maxConteguousSubarraySum;
	}
	
	/**
	 * 	O(n) time - Kadane's algorithm.
	 */
	private static int findMaxContiguousSubarraySum_3(int[] arr) {
		
		int maxSubarraySum = arr[0];		
		
		for (int i = 1; i < arr.length; i++) {
			
			/**
			 *  if val at a[i] is greater that a[i - 1] + a[i] then don't consider previous sub array sub.
			 *  cut off & start the new sub array sum from this index. 
			 */
			
			if (arr[i] + arr[i - 1] > arr[i]) {
						
				arr[i] = arr[i] + arr[i - 1];
			}
			// arr[i] = Integer.max(arr[i - 1] + arr[i], arr[i]);			
			
						
			if (arr[i] > maxSubarraySum) {
				maxSubarraySum = arr[i];
			}
		} 
		
		System.out.println(Arrays.toString(arr));
		
		return maxSubarraySum;
	}
	
	/**
	 * 	O(n^2).
	 */
	static int[] findMaxContiguousSubarrayLength_1(int[] arr) {
		
		int[] longestSubarrayRange = { -1, -1 };
		int maxConteguousSubarraySum = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			
			int currSum = 0;
			
			for (int j = i; j < arr.length; j++) {
				
				currSum += arr[j]; // sliding window pattern.
				
				if (currSum > maxConteguousSubarraySum) {
					maxConteguousSubarraySum = currSum;
					longestSubarrayRange = new int[]{ i, j };					
				}				
			}			
		}		
		return longestSubarrayRange;
	}
	
	/**
	 * 	O(n).
	 */
	static int[] findMaxContiguousSubarrayLength_2(int[] arr) {
		
		int[] longestSubarrayRange = {0,0};
		
		for (int i = 1; i < arr.length; i++) {
			
			if (arr[i] + arr[i - 1] > arr[i]) {
				
				arr[i] = arr[i] + arr[i - 1];
				
				longestSubarrayRange[1] = i;
				
			} else { // cut off previous sub array as the val at a[i] > a[i] + a[i - 1]
				
				longestSubarrayRange = new int[] {i,i};
			}
		}
		return longestSubarrayRange;
	}
	
	/**
	 * 	O(n).
	 */
	static int findMaxSumSubArrayOfSizeK(int[] arr, int k) {
		int windowSum = 0, maxSum = 0;
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
			windowSum += arr[windowEnd]; // add the next element
			// slide the window, we don't need to slide if we've not hit the required window size of 'k'
			if (windowEnd >= k - 1) {
				maxSum = Math.max(maxSum, windowSum);
				windowSum -= arr[windowStart]; // subtract the element going out
				windowStart++; // slide the window ahead
			}
		}
		return maxSum;
	}

	static int findMinSubArray(int[] arr, int targetSum) {
		int windowSum = 0, minLength = Integer.MAX_VALUE;
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
			windowSum += arr[windowEnd]; // add the next element
			// shrink the window as small as possible until the 'windowSum' is smaller than 'S'
			while (windowSum >= targetSum) {
				minLength = Math.min(minLength, windowEnd - windowStart + 1);
				windowSum -= arr[windowStart]; // subtract the element going out
				windowStart++; // slide the window ahead
			}
		}
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
	
}
