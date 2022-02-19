package my.learning.algorithms.recursion.dynamic.programming;

/**
 * 	Maximum Length of Repeated Subarray
 * 
 * 	Given two strings s1 and s2, return the maximum length of an substring that appears in both arrays.

 *  
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		
		String str1 = "abeebbbb";
		String str2 = "abeae";
		
		int lengthOflongestCommonSubstring = longestCommonSubstring(str1, str2, 0, 0, 0);
		System.out.println(String.format("longest common substring b/w %s & %s is %s", str1, str2, lengthOflongestCommonSubstring));			
	}
		
	/**
	 * 	longestCommonSubstring() b/w the 2 strings.
	 */
	private static int longestCommonSubstring(String str1, String str2, int i, int j, int longestSubstrLen) {
		
		if (i == str1.length() || j == str2.length())
			return longestSubstrLen;
		
		
		// chars at i & j are same.
		if (str1.charAt(i) == str2.charAt(j)) {
			
			return longestCommonSubstring(str1, str2, i + 1, j + 1, longestSubstrLen + 1);
		}
		
		
		int ifInserted = longestCommonSubstring(str1, str2, i, j + 1, 0); // insert.
		
		int ifDeleted = longestCommonSubstring(str1, str2, i + 1, j, 0); // delete.
			
		return max(longestSubstrLen, ifInserted, ifDeleted);
	}
	
	private static int max(int a, int b, int c) {
	    return Math.max(Math.max(a, b), c);
	}
	
}
