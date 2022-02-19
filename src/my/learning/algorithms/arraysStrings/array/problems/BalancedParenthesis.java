package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Stack;

/**
 * 	Classic Stack problem.
 */
public class BalancedParenthesis {

	public static void main(String[] args) {
			
		String parenthesis = "[({()})[]}";
	
		boolean isBalanced = isBalanced(parenthesis);
		System.out.println(isBalanced);
	}

	private static boolean isBalanced(String parenthesis) {  
	
		if (parenthesis.length() % 2 != 0)
			return false;
		
		Stack<Character> stack = new Stack<>();
		
		for (Character ch: parenthesis.toCharArray()) {
			
			if (ch == '[' || ch == '{' || ch == '(') {
				
				stack.push(ch);
			} 
			
			else {
				
				if ((ch == ']' && stack.peek() == '[')
				 || (ch == '}' && stack.peek() == '{')
				 || (ch == ')' && stack.peek() == '(')) {
					
					stack.pop();
				}
				
			}
		}
		
		return stack.isEmpty();
	}
		
}
