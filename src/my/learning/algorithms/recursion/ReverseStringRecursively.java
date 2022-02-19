package my.learning.algorithms.recursion;

public class ReverseStringRecursively {

	public static void main(String[] args) {
		
		String str = "apple";
		
		char[] chars = str.toCharArray();
		
		reverse(chars, 0, chars.length - 1);
		
		System.out.println(String.valueOf(chars)); 
	}
	
	static void reverse(char[] chars, int i, int j) {
		
		// base case.
		if (i >= j) {
			return;
		}
		
		// core logic.
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
		
		// delegate next case to recursion.
		reverse(chars, i + 1, j - 1);
	}
}
