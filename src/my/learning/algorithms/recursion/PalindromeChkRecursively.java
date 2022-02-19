package my.learning.algorithms.recursion;

public class PalindromeChkRecursively {

	public static void main(String[] args) {
		
		String str = "nitin";
	
		boolean isPalindrome = isPalindrome(str, 0, str.length() - 1);
		System.out.println(isPalindrome);		
	}

	private static boolean isPalindrome(String str, int i, int j) {
		
		if (i >= j) {
			return true;
		}
				
		return (str.charAt(i) == str.charAt(j)) && isPalindrome(str, i + 1, j - 1);
	}
	
}
