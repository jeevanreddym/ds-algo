package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;




/**
 * 	Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to 
 * 	construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

	Note:
	
	The same word in the dictionary may be reused multiple times in the segmentation.
	You may assume the dictionary does not contain duplicate words.
	
	Example 1:
	
		Input: s = "catsanddog", wordDict = ["cat", "cats", "and", "sand", "dog"]
		Output:
		[
		  "cats and dog",
		  "cat sand dog"
		]
		
	Example 2:
	
		Input: s = "pineapplepenapple", wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
		Output:
		[
		  "pine apple pen apple",
		  "pineapple pen apple",
		  "pine applepen apple"
		]
		Explanation: Note that you are allowed to reuse a dictionary word.
		
	Example 3:
	
		Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
		Output: []
 *	
 *	
 */
public class BreakStringToValidWords {

	public static void main(String[] args) {
		
		String str = "catsanddog";
		Set<String> dictonary = new LinkedHashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog", "catsan"));
	
		
		
		// using List<String> partial.
		List<List<String>> breakdown = new LinkedList<>();		
		wordBreakdown(str, dictonary, new LinkedList<>(), breakdown);
		breakdown.forEach(wordsArr -> System.out.println(Arrays.toString(wordsArr.toArray())));	
		
		// using String partial.
		List<String> words = new LinkedList<>();		
		wordBreakdown("", str, dictonary, words);
		words.forEach(word -> System.out.println(word));
		
		
		
		
		
//		List<List<String>> matches = new LinkedList<>();		
//		for (String word: dictonary) {				
//			wordBreakdown(word, dictonary, new LinkedList<>(), matches);	
//		}
//		matches.forEach(match -> System.out.println(Arrays.toString(match.toArray())));		
	}
	
	
	private static void wordBreakdown(String str, Set<String> dictonary, List<String> partial, List<List<String>> breakdown) {
		
		if (str.length() == 0) { // base case.		
			breakdown.add(new ArrayList<>(partial));			
			return;
		}
		
		for (int i=0; i < str.length(); i++) { // recursive case.
			
			String word = str.substring(0, i + 1);
			
			if (dictonary.contains(word)) { // skip if word is not in dictonary.
				
				partial.add(word);
			
				String remainingWord = str.substring(i + 1);
				
				wordBreakdown(remainingWord, dictonary, partial, breakdown); // recurse with remaining part of the string (from index [i + 1]).
				
				partial.remove(partial.size() - 1);
			}
		}		
	}
	
	private static void wordBreakdown(String prefix, String suffix, Set<String> dictonary, List<String> breakdown) {
		
		if (suffix.length() == 0) {			
			breakdown.add(prefix);			
			return;
		}
		
		for (int i=0; i < suffix.length(); i++) {
			
			String word = suffix.substring(0, i + 1);
			
			if (dictonary.contains(word)) { // skip if word is not in dictonary.
				
				prefix += " " + word;
			
				String remainingWord = suffix.substring(i + 1);
				
				wordBreakdown(prefix, remainingWord, dictonary, breakdown); // recurse with remaining part of the string (from index [i + 1]).
			}
		}		
	}	
	
}
