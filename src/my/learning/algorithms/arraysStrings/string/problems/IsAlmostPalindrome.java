package my.learning.algorithms.arraysStrings.string.problems;



/**
 * 	Given a non-empty string str, you may delete at most one character. Judge whether you can make it a palindrome.
 */
public class IsAlmostPalindrome {
	
	
	public static void main(String[] args) {
		
		String str = "abcddba";
		boolean isAlmostPalindrome = isAlmostPalindrome(str);
		System.out.println(isAlmostPalindrome);
	}
	
	/**
	 * 	can str be made a palindrome jus by deleting 1 character in it? 
	 */
	private static boolean isAlmostPalindrome(String str) {
		
		if (str == null) return false;
		if (str.length() == 0 || str.length() == 1) return true;
		
		int l = 0, r = str.length() - 1;
		
		while (l < r) {
			
			if (str.charAt(l) != str.charAt(r)) {
			
				/**
				 * 	chk if substring ([l, r - 1] or [l + 1, r]) is a palindrome by removing either one of the chars which didn't match to each other.
				 * 	If any of the 2 return as Palindrome then we know which char to delete.
				 */				
				return isPalindrome(str, l, r - 1) || isPalindrome(str, l + 1, r); 
			}
			
			l++;			
			r--;
		}
		
		return true;
	}
	
	private static boolean isPalindrome(String str, int l, int r) {
		while (l < r) {
			if (str.charAt(l++) != str.charAt(r--)) {
				return false;
			}				
		}			
		return true;
	}
	
}
