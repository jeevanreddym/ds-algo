package my.learning.algorithms.arraysStrings.array.problems;


/**
 * 	Reverse a String / Number. 
 */
public class Reverse {
	
	public static void main(String[] args) {		
		System.out.println(Reverse.isPalindrome("nitinn"));
		System.out.println(Reverse.isPalindrome("nitin"));
		System.out.println(Reverse.isPalindrome("nittin"));		
	}	

	private static String isPalindrome(String str) {		
		char[] chars = str.toCharArray();
		for (int i=0; i < chars.length/2; i++) {
			swap(chars, i, chars.length - 1 -i);
		}
		return String.valueOf(chars);
	}
	
	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
	
}
