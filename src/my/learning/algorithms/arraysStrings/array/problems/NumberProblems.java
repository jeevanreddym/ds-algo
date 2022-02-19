package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.Stack;

public class NumberProblems {

	
	public static void main(String[] args) {
			
		System.out.println(Arrays.toString(getDigitsArray(9856)));
	}
	
	static int[] getDigitsArray(int num) {
		
		Stack<Integer> stack = new Stack<>();
		
		int dividend = num;
		int reminder = 0;
		
		do {
			
			reminder = dividend % 10;			
			stack.push(reminder);
			
			dividend = dividend / 10;
			
		} while (dividend > 0);
				
		int[] digits = new int[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			digits[i++] = stack.pop();
		}
		
		return digits;
	}	
	
}
