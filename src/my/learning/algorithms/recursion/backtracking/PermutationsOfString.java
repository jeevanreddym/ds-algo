package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;


/**
 * 	i/p: "abc"
 *  
 *  o/p: ["abc", "acb", "bac", "bca", "cab", "cba"] 
 *
 */
public class PermutationsOfString { // All Anagrams of a string.

	public static void main(String[] args) {
		
		String str = "abc";
				
		// prefix suffix approach.
		List<String> anagrams = permutations(str);
		anagrams.forEach(anagram -> System.out.println(anagram));
		
		
		System.out.println("\n\n");
		
		// swap approach.
//		anagrams = permutationsAlternative(str);
//		anagrams.forEach(anagram -> System.out.println(anagram));
	}

	
	/**
	 * 	String prefix suffix approach.
	 */
	private static void permutations(String prefix, String suffix, List<String> permutations) {
	
		if (suffix.length() == 0) {
			permutations.add(prefix);
			return;
		}
		
		for (int i=0; i < suffix.length(); i++) {
			
			String newPrefix = prefix + suffix.charAt(i); // existing prefix + char at index i.
			
			String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1, suffix.length()); // remove the char at index i from the existing suffix.
			
			permutations(newPrefix, newSuffix, permutations);			
		}		
	}
	
	private static List<String> permutations(String str) {		
		List<String> allPermutations = new ArrayList<>();		
		permutations("", str, allPermutations);		
		return allPermutations;
	}
	
	
	
	
	
	
	
	
	/**
	 * 	Alternative approach using swaping technique
	 */
	private static List<String> permutationsAlternative(String str) {		
		List<String> allPermutations = new ArrayList<>();		
		permutationsAlternative(str, 0, allPermutations);		
		return allPermutations;
	}
	
	private static void permutationsAlternative(String str, int pos, List<String> allPermutations) {
		
		int len = str.length();
		
		if (pos == len) {
			allPermutations.add(str);
			return;
		}
		
		for (int i = pos; i < len; i++) {
			
			str = swap(str, pos, i);
			
			permutationsAlternative(str, pos + 1, allPermutations);	
			
			str = swap(str, pos, i);			
		}		
	}

	private static String swap(String str, int i, int j) {
		
		char[] chars = str.toCharArray();
		
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		
		return String.valueOf(chars);
	}
	
}
