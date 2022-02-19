package my.learning.algorithms.arraysStrings.string.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 	Given a string s, find the length of the longest substring without repeating characters.
 * 
 * 	substring: conteguous ~ sequential (without gaps, side-by-side)
	subsequence: can have gaps in b/w.
	
 * 	
 * 	Example 1:
		Input: s = "abcabcbb"
		Output: 3
		Explanation: The answer is "abc", with the length of 3.
	
	Example 2:
		Input: s = "bbbbb"
		Output: 1
		Explanation: The answer is "b", with the length of 1.
	
	Example 3:	
		Input: s = "pwwkew"
		Output: 3
		Explanation: The answer is "wke", with the length of 3.
		Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
	
	Example 4:		
		Input: s = ""
		Output: 0	
 */
public class LongestSubstrWithoutRepeatingChars {

	
	public static void main(String[] args) {
		
		String str = "abcbdca";
		
		System.out.println("Length of the longest substring: " + lengthOfLongestSubstring(str));
		
		System.out.println("Length of the longest substring: " + lengthOfLongestSubstringOptimal(str));
	}
	
	/** 
	 * 	Brute Force: O(n^2) time, O(n) space. 	
	 * 
	 * 	using 2 pointer technique.	 
	 */
	public static int lengthOfLongestSubstring(String str) {
		
		int longestSubstrLen = 0;
				
		Set<Character> set = new HashSet<>();
		
		int currSubstrLen = 0;
		
		int i = 0, j = 0;
		
		while (j < str.length()) {
			
			if (!set.contains(str.charAt(j))) {
				
				set.add(str.charAt(j));
				
				j++;
				
				currSubstrLen++;
				
				if (currSubstrLen > longestSubstrLen) {
					longestSubstrLen = currSubstrLen;
				}
				
			} else {
				
				i++;
				
				j = i;				
				
				currSubstrLen = 0;
				
				set.clear();
			}						
		}
		
		return longestSubstrLen;
	}
	
	/**
	 * 	O(n) time, O(n) space. 		
	 */
	public static int lengthOfLongestSubstringOptimal(String str) {
		
		int longestSubstrLen = 0;
		
		int i = 0, j = 0;
				
		
		/**
		 * 	here we use a map to keep track of the index of each element apart from just the value,
		 * 	so when we come across a diplicate later we can check at what index did we see the previous matched element
		 * 	and if its before i then we dont need to consider it as a duplicate.
		 */
		Map<Character, Integer> map = new HashMap<>();
		
		int currSubstrLen = 0;
		
		while (j < str.length()) {
			
			/**
			 * 	if map contains the char then chk if that char index is left of the i pointer, 
			 * 	if it is then we don't bother as its outside the current window.
			 * 	
			 */			
			if (!map.containsKey(str.charAt(j)) || map.get(str.charAt(j)) < i) {
				
				map.put(str.charAt(j), j);
				
				j++;
				
				currSubstrLen++;
				
				if (currSubstrLen > longestSubstrLen) {
					longestSubstrLen = currSubstrLen;
				}
				
			} else {
				
				i = map.get(str.charAt(j)) + 1;
				
				map.put(str.charAt(j), j); // override the index of the currently found char to the latest index.
				
				currSubstrLen = j - i + 1;
			}			
		}
		
		return longestSubstrLen;
	}
	
}
