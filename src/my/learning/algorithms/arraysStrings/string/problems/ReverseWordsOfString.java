package my.learning.algorithms.arraysStrings.string.problems;

public class ReverseWordsOfString {

	public static void main(String[] args) {
		
		String str = "the eagle has landed";
		
		System.out.println(reverseWords(str)); // "landed has eagle the"		
	}

	/**
	 * 	reverse words of a string.
	 */
	private static String reverseWords(String str) {
		
		char[] chars = str.toCharArray();
		
		int endofStrPos = chars.length - 1;
		
		reverse(chars, 0, endofStrPos); // reverse all chars in the string.
		
		
		int wordStartPos = 0;
		for (int i=0; i < chars.length; i++) {
			
			if (chars[i] == ' ' || i == endofStrPos) { // find each word in the reversed string.
				
				reverse(chars, wordStartPos, i == endofStrPos? i: i - 1); // reverse it.
				
				wordStartPos = i + 1;
			}			
		}
		
		return String.valueOf(chars);
	}
	
	private static void reverse(char[] chars, int startPos, int endPos) {
		int l = startPos, r = endPos;
		while (l < r) {
			swap(chars, l, r);	
			l++;
			r--;
		}
	}
	
	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
	
}
