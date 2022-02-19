package my.learning.algorithms.arraysStrings.string.problems;

public class StringRotationUsingStrReversal {

	public static void main(String[] arg) {
		
		String str = "abcdef";		
		int offset = 7;
		
		String rotatedStr = rotate(str, offset);
		System.out.println(rotatedStr);
	}
		
	/**
	 * 	rotate string by an offset. O(n) time & O(1) space.
	 */
	private static String rotate(String str, int offset) {
		
		char[] chars = str.toCharArray();
		
		int len = chars.length;
		
		offset = offset % len;
		
		
		int endOfStr = len - 1;
		
		
		reverse(chars, 0, endOfStr - offset);
		
		reverse(chars, len - offset, endOfStr);
		
		reverse(chars, 0, endOfStr);
		
		return String.valueOf(chars);
	}
	
	private static void reverse(char[] chars, int start, int end) {
		while (start < end) {
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}
	}
	
}
