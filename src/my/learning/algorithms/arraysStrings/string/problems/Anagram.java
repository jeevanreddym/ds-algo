package my.learning.algorithms.arraysStrings.string.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Anagram {

	public static void main(String[] args) {
		
		System.out.println(areAnagramsBF("Listen", "Silent"));
		System.out.println(areAnagramsBF("Night", "Thing"));
		System.out.println(areAnagramsBF("Night", "Thinn"));
		
		
//		String[] strs = {"persist", "priests", "spriest", "sprites", "stripes"};
//		for (String anagramStr: strs) {
//			System.out.println(anagramStr);
//		}
	}
	
	/**
	 * 	Brute force: O(nlog(n)) + O(nlog(n)) + O(n) time = O(nlog(n)).
	 */
	private static boolean areAnagramsBF(String s1, String s2) {		
		if (s1 == s2)
			return true;
		if (s1.length() != s2.length())
			return false;
		
		char[] s1Chars = s1.toLowerCase().toCharArray();
		char[] s2Chars = s2.toLowerCase().toCharArray();
		
		Arrays.sort(s1Chars);
		Arrays.sort(s2Chars);
		
		//return Arrays.equals(s1Chars, s2Chars);
		
		for (int i=0; i < s1Chars.length; i++ ) {
			if (s1Chars[i] != s2Chars[i])
				return false;
		}		
		return true;
	}
	
	/**
	 * 	O(n) time, O(n) space.
	 */
	private static boolean areAnagramsWrong(String s1, String s2) {		
		if (s1 == s2)
			return true;
		if (s1.length() != s2.length())
			return false;
		
		char[] s1Chars = s1.toLowerCase().toCharArray();
		char[] s2Chars = s2.toLowerCase().toCharArray();
		
		Set<Character> set = new HashSet<>(s1Chars.length);
		
		for (int i=0; i < s1Chars.length; i++) {
			set.add(s1Chars[i]);
			set.add(s2Chars[i]);			
		}		
		return set.size() == s1Chars.length;
	}
	
	/**
	 * 	O(n) time & O(1) space.
	 */
	private static boolean areAnagrams(String first, String second) {
		if (first.length() != second.length()) 
			return false;
		
		first = first.toLowerCase();
		second = second.toLowerCase();
		
		int[] frequencies = new int[26];		
		for (int i = 0; i < first.length(); i++) {			
			frequencies[first.charAt(i) - 'a']++;
		}
		for (int i = 0; i < second.length(); i++) {
			if (frequencies[second.charAt(i) - 'a'] == 0) 
				return false;
			frequencies[second.charAt(i) - 'a']--;
		}
		return true;
	}
	
}
