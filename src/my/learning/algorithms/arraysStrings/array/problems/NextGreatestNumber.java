package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreatestNumber {

	public static void main(String[] args) {
		
		int[] nums = {6,4,5,3,1,2};
		int[] ngns = nextGreatestNumber(nums);
		System.out.println(Arrays.toString(ngns)); 
	}
	
	private static int[] nextGreatestNumber(int[] nums) {		
		
		Map<Integer, Integer> nextGreatest = new HashMap<>();
		
		Stack<Integer> stack = new Stack<>();
		
		for (int num: nums) {
			
			while (!stack.isEmpty() && stack.peek() < num) {
				
				nextGreatest.put(stack.pop(), num);
			}
			
			stack.push(num);
		}
		
		int i = 0;
		int[] ngns = new int[nums.length];
		for (int num: nums) {
			ngns[i++] = nextGreatest.getOrDefault(num, -1);
		}
		
		return ngns;
	}
}
