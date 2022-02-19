package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 	
 * 	Edit distance (In standard Edit Distance where we are allowed 3 operations, insert, delete and replace).
 *  
 *  LCS (Longest Common Subsequence): (Consider a variation of edit distance where we are allowed only 
 *  	two operations -> insert and delete, find edit distance in this variation).
 *  
 *  @EditDistance
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		
		String str1 = "bcd";
		String str2 = "bcdcc";
		
		int lengthOflongestCommonSubsequence = lcs(str1, str2, 0, 0);
		System.out.println(String.format("longest common subsequence b/w %s & %s is %s", str1, str2, lengthOflongestCommonSubsequence));	
		
		
		
		int[][] dp = new int[str1.length()][str2.length()];
		Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
		
		lengthOflongestCommonSubsequence = lcsMemo(str1, str2, 0, 0, dp);
		System.out.println(String.format("longest common subsequence b/w %s & %s is %s", str1, str2, lengthOflongestCommonSubsequence));	
	}
		
	/**
	 * 	available operations: insert & delete.	
	 */
	private static int lcs(String str1, String str2, int i, int j) {
		
		if (i == str1.length() || j == str2.length())
			return 0;
		
		
		// chars at i & j are same. 
		
		if (str1.charAt(i) == str2.charAt(j)) {
			
			return 1 + lcs(str1, str2, i + 1, j + 1);
		}
		
		
		int ifInserted = lcs(str1, str2, i, j + 1); // insert.
		
		int ifDeleted = lcs(str1, str2, i + 1, j); // delete.
			
		return Integer.max(ifInserted, ifDeleted); // return max value of 2 options (Insert/Delete).
	}
	
	
	private static int lcsMemo(String str1, String str2, int i, int j, int[][] dp) {
		
		if (i == str1.length() || j == str2.length())
			return 0;
		
		
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		
		
		
		// chars at i & j are same.
		if (str1.charAt(i) == str2.charAt(j)) {
			
			return 1 + lcs(str1, str2, i + 1, j + 1);
		}
		
		
		int ifInserted = lcs(str1, str2, i, j + 1); // insert.
		
		int ifDeleted = lcs(str1, str2, i + 1, j); // delete.
				
		dp[i][j] = Integer.max(ifInserted, ifDeleted);  // return max value of 2 options (Insert/Delete).
			
		return dp[i][j];
	}
	
}
