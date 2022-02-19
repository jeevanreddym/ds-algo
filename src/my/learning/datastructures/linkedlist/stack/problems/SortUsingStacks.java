package my.learning.datastructures.linkedlist.stack.problems;

import java.util.Stack;

public class SortUsingStacks {

	public static Stack<Integer> sortstack(Stack<Integer> input) {
		
		Stack<Integer> tmpStack = new Stack<Integer>();
		
		while (!input.isEmpty()) {
			 
			int tmp = input.pop(); // pop out the first element

			// while temporary stack is not empty and top of stack is greater than temp 
			while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
				
				// pop from temporary stack and push it to the input stack 
				input.push(tmpStack.pop());
			}
			 
			tmpStack.push(tmp); // push temp in tempory of stack.
		}
		return tmpStack;
	}

	
	public static void main(String args[]) {
		Stack<Integer> input = new Stack<Integer>();
		input.add(34);
		input.add(3);
		input.add(31);
		input.add(98);
		input.add(92);
		input.add(23);

		// This is the temporary stack 
		Stack<Integer> tmpStack = sortstack(input);
		System.out.println("Sorted numbers are:");
		while (!tmpStack.empty()) {
			System.out.print(tmpStack.pop() + " ");
		}
	}

}
