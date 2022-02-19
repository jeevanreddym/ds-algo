package my.learning.algorithms.arraysStrings.string.problems;

import java.util.ArrayList;
import java.util.List;

public class KMPpatternMatching {

	
	public static void main(String[] args) {
	
		//String text = "adsgwadsxdsgwadsgz";
		//String pattern = "dsgwadsgz";
		
		String text = "apppplapp";
		String pattern = "pp";
		
		//findFirstIndex();
		//findAny();
		//findLastIndex();

		System.out.println(KMP(text, pattern));
	}	
	
	
	
	
	public static List<Integer> KMP(String text, String pattern) {
		
		List<Integer> occurrences = new ArrayList<>();
		
					
		// each of these boxes is the length of the longest suffix thats also a prefix. 
		int[] prefixSuffixTable = new int[pattern.length()];
		
		int i=0, j=1;
		while (j < pattern.length()) {
			
			if (pattern.charAt(i) == pattern.charAt(j)) { // if chars at i & j match set value at index j = index i + 1.
				
				prefixSuffixTable[j] = i + 1;
				
				i++;			
			}
			
			j++;		
		}
				
		
		
		
		j = 0; // j is pattern pointer.
		i = 0; // i is text pointer.
		
		while (i < text.length()) { 
			
			if (text.charAt(i) == pattern.charAt(j)) {
				
				j++;
				
				i++;
				
				
				if (j == pattern.length()) { // found a match for the pattern in the text.
					
					occurrences.add(i - j); // add to result.
					
					j = prefixSuffixTable[j - 1];
					
					//return i - j; // return the starting index of the first pattern match in the text.
				}				
				
			} else {
				
				if (j != 0) {
					
					j = prefixSuffixTable[j - 1]; // go back in the pattern till where prefix matches the suffix.
					
				} else {
					
					i++;
				}
			}			
		}
		
		return occurrences;
		//return -1;		
	}

}
