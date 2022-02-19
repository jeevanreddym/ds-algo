package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class PalindromicDecompositions {
		
	public static void main(String[] args) {
		
		String str = "google";
				
		List<List<String>> decompositions = new ArrayList<>();
		decompose(str, 0, new ArrayList<>(), decompositions);				
	    decompositions.forEach(set -> System.out.println(Arrays.toString(set.toArray())));
	}

	static void decompose(String str, int start, List<String> partial, List<List<String>> decompositions) {
						
		if (start == str.length()) { // base case.			
			decompositions.add(new ArrayList<>(partial));
			return;
		}
		
		for (int i = start; i < str.length(); i++) {
			
			if (isPalindrome(str, start, i)) { // chk if substr[start to i] is a palindrome?			
				
		        String palindromicSnippet = str.substring(start, i + 1);
		        partial.add(palindromicSnippet);
		        	        
		        decompose(str, i + 1, partial, decompositions); // Explore - Recurse on the choice.

				partial.remove(partial.size() - 1);				
			}		
		}
	}
	
	/**
	 * 	Checks if the region from left (inclusive) to right (inclusive) is a palindrome.
	 */
	static boolean isPalindrome(String str, int l, int r) {
		while (l < r) {
			if (str.charAt(l++) != str.charAt(r--)) {
				return false;
			}
		}
		return true;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// expand in both directions of low and high to find all palindromes
	public static void expand(String str, int low, int high, Set<String> set) {
		// run till str[low.high] is a palindrome
		while (low >= 0 && high < str.length() && str.charAt(low) == str.charAt(high)) {
			// push all palindromes into the set
			set.add(str.substring(low, high + 1));

			// expand in both directions
			low--;
			high++;
		}
	}

	// Function to find all unique palindromic substrings of given string
	public static void allPalindromicSubStrings(String str) {
		// create an empty set to store all unique palindromic substrings
		Set<String> set = new HashSet<>();

		for (int i = 0; i < str.length(); i++) {
			// find all odd length palindrome with str[i] as mid point
			expand(str, i, i, set);

			// find all even length palindrome with str[i] and str[i+1]
			// as its mid points
			expand(str, i, i + 1, set);
		}

		// print all unique palindromic substrings
		System.out.print(set);
	}
	
}
