package my.learning.algorithms.arraysStrings.array.problems;

import java.util.PriorityQueue;

public class SecondLargestNumber {

	public static void main(String[] args) {
		
		int[] nums = {-1, 10, 8, 9, 10, 8, 11};
		System.out.println(findNthLargestNumber(nums, 4));
		
		System.out.println(find2ndLargestNumber(nums));
	}
	
	/**
	 * 	O(n) time for 2nd largest number in array.
	 */
	private static int find2ndLargestNumber(int[] nums) {
		
        if (nums.length < 2) {
            return -1; 
        } 
		
		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		
		for (int i=0; i < nums.length; i++) {
			
			if (nums[i] > largest) {
				
				secondLargest = largest;
				
				largest = nums[i];				
			}	
			
			else if (nums[i] > secondLargest && nums[i] != largest) {
				
				secondLargest = nums[i];
			}
			
		}
		
		return secondLargest == Integer.MIN_VALUE? -1: secondLargest;
	}
	

	
	/**
	 *	O(nlog(n)).
	 */
	private static int findNthLargestNumber(int[] nums, int nthLargest) {		
				
		/**
		 * 	for largest k numbers use a min heap, since u will have the smallest number 
		 * 	on top & can remove it if the next incoming number is greater that the top element. 
		 */
		PriorityQueue<Integer> pq = new PriorityQueue<>(nthLargest, (n1,n2) -> n1 - n2); // Min Heap.
		
		for (int i=0; i < nums.length; i++) {
			
			if (pq.size() == nthLargest) {
				
				if (nums[i] > pq.peek()) {
				
					pq.poll();
									
				}
			}	
			
			pq.offer(nums[i]);
		}
		
		return pq.isEmpty()? -1: pq.poll();
	}
	
}
