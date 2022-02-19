package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 	Levenshtein Distance:
 * 	----------------------
 * 	also see -> @LongestCommonSubsequence
 * 
 * 	Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

	You have the following three operations permitted on a word:
	
	Insert a character
	Delete a character
	Replace a character
	 
	
	Example 1:	
		Input: word1 = "horse", word2 = "ros"
		Output: 3
		Explanation: 
		horse -> rorse (replace 'h' with 'r')
		rorse -> rose (remove 'r')
		rose -> ros (remove 'e')
		
	Example 2:
		Input: word1 = "intention", word2 = "execution"
		Output: 5
		Explanation: 
		intention -> inention (remove 't')
		inention -> enention (replace 'i' with 'e')
		enention -> exention (replace 'n' with 'x')
		exention -> exection (replace 'n' with 'c')
		exection -> execution (insert 'u')
		
 */
public class EditDistance {

	public static void main(String[] args) {
		
		String str1 = "abc";
		String str2 = "bcd";
		
//		int editDist = editDistance(str1, str2, 0, 0); // expecting editDist = 2 (delete a & add d to str1).
//		System.out.println(String.format("edit dist to convert %s to %s is %s", str1, str2, editDist));
//		
//		
//		str1 = "sunday";
//		str2 = "saturday";
//		
//		editDist = editDistance(str1, str2, 0, 0); // expecting editDist = 3 (insert at & replace n with r in str1).
//		System.out.println(String.format("edit dist to convert %s to %s is %s", str1, str2, editDist));
		
		
		str1 = "";
		str2 = "saturday";
		
		int editDist = editDistance(str1, str2, 0, 0); // expecting editDist = 3 (insert at & replace n with r in str1).
		System.out.println(String.format("edit dist to convert %s to %s is %s", str1, str2, editDist));
		
		
		
//		str1 = "abc";
//		str2 = "bcd";
//		
//		int[][] dp = new int[str1.length()][str2.length()];
//		Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
//		
//		editDist = editDistanceMemo(str1, str2, 0, 0, dp); // expecting editDist = 3 (insert at & replace n with r in str1).
//		System.out.println(String.format("edit dist to convert %s to %s is %s", str1, str2, editDist));
//		
//		
//		str1 = "sunday";
//		str2 = "saturday";
//		
//		dp = new int[str1.length()][str2.length()];
//		Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
//		
//		editDist = editDistanceMemo(str1, str2, 0, 0, dp); // expecting editDist = 3 (insert at & replace n with r in str1).
//		System.out.println(String.format("edit dist to convert %s to %s is %s", str1, str2, editDist));
	}

	private static int editDistance(String str1, String str2, int i, int j) {
	
		if (i == str1.length())
			return str2.length() - j;
		
		if (j == str2.length())
			return str1.length() - i;
		
		
		
		
		
		// chars at i & j are same. 
		
		if (str1.charAt(i) == str2.charAt(j)) {
			
			return editDistance(str1, str2, i + 1, j + 1);
		}
			
			
		int ifInserted = 1 + editDistance(str1, str2, i, j + 1); // insert.
		
		int ifDeleted = 1 + editDistance(str1, str2, i + 1, j); // delete.
		
		int ifReplaced = 1 + editDistance(str1, str2, i + 1, j + 1); // replace.
					
		return min(ifInserted, ifDeleted, ifReplaced); // return min value of 3 options (Insert/Delete/Replace).
	}
	
	
	/**
	 * 	Memoized solution.
	 */
	private static int editDistanceMemo(String str1, String str2, int i, int j, int[][] dp) {
		
		if (i == str1.length())
			return str2.length() - j;
		
		if (j == str2.length())
			return str1.length() - i;
		
		
		
		
		if (dp[i][j] != -1) {			
			return dp[i][j];
		}
		
		
		
		if (str1.charAt(i) == str2.charAt(j)) {
			
			return editDistanceMemo(str1, str2, i + 1, j + 1, dp);
		}
			
			
		int ifInserted = 1 + editDistanceMemo(str1, str2, i, j + 1, dp); // insert.
		
		int ifDeleted = 1 + editDistanceMemo(str1, str2, i + 1, j, dp); // delete.
		
		int ifReplaced = 1 + editDistanceMemo(str1, str2, i + 1, j + 1, dp); // replace.
				
		dp[i][j] = Integer.min(Integer.min(ifInserted, ifDeleted), ifReplaced);
				
		return dp[i][j]; // return min value of 3 options (Insert/Delete/Replace).
	}
	
	
	private static int min(int a, int b, int c) {
	    return Integer.min(Integer.min(a, b), c);
	}
	
}
