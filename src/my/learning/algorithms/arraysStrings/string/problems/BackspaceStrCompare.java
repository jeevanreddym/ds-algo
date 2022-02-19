package my.learning.algorithms.arraysStrings.string.problems;

import java.util.Stack;

public class BackspaceStrCompare {

	public static void main(String[] args) {
						
		String s1 = "abc#d";
		String s2 = "abzz##d";
		
		System.out.println(backspaceCompare(s1, s2));	
		
		System.out.println(backspaceCompareOptimal(s1, s2));		
	}
	
	
	/**
	 * 	O(n + m) time & O(n + m) auxillary space. 
	 */
	private static boolean backspaceCompare(String s1, String s2) {
		
		Stack<Character> s1Stack = buildCharStack(s1); // build stack of chars after removing "backspaced" chars.
		
		Stack<Character> s2Stack = buildCharStack(s2);
		
		
		if (s1Stack.size() != s2Stack.size()) {
			return false;
		}
		
		while (!s1Stack.isEmpty() && !s2Stack.isEmpty()) {
			if (s1Stack.pop() != s2Stack.pop()) {
				return false;
			}
		}
		
		return true;
	}
	
	private static Stack<Character> buildCharStack(String str) {
		char[] chars = str.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int i=0; i < chars.length; i++) {
			if (chars[i] != '#') {
				stack.push(chars[i]);
			} else {
				if (!stack.isEmpty()) {
					stack.pop();
				}				
			}
		}
		return stack;
	}
	
	
	
	
	
	
	/**
	 * 	O(n + m) time & O(1) auxillary space. 
	 */
	private static boolean backspaceCompareOptimal(String s1, String s2) {
		
		int p1 = s1.length() - 1, p2 = s2.length() - 1;
		
		while (p1 >= 0 || p2 >= 0) {
									
			if (s1.charAt(p1) == '#' || s2.charAt(p2) == '#') {
			
				if (s1.charAt(p1) == '#') {
					
					int backCnt = 2;
					while (backCnt > 0) {
						
						p1--;
						
						backCnt--;
						
						if (s1.charAt(p1) == '#') {
							backCnt += 2;
						}
					}
				}
				
				if (s2.charAt(p2) == '#') {
					
					int backCnt = 2;
					while (backCnt > 0) {
						
						p2--;
						
						backCnt--;
						
						if (s2.charAt(p2) == '#') {
							backCnt += 2;
						}
					}
				}
				
			} else {
				
				if (s1.charAt(p1) != s2.charAt(p2)) {
					
					return false;
					
				} else {
					
					p1--;
					p2--;
				}
			}			
		}
		
		return true;
	}
	
}
