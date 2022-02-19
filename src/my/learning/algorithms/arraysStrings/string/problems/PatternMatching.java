package my.learning.algorithms.arraysStrings.string.problems;

import java.util.HashMap;
import java.util.Map;

public class PatternMatching {

	public static void main(String[] args) {
		
		String text = "aabcdaasaasm";
		
		String pattern = "aasm";
		
		System.out.println(doesStringMatchPattern(text, pattern));		
	} 
	
	static int doesStringMatchPattern(String text, String pattern) {
		
		for (int i = 0; i <= (text.length() - pattern.length()); i++) {
			
			boolean patternMatched = true;
			
			for (int j = 0; j < pattern.length(); j++) {
				
				if (text.charAt(i + j) != pattern.charAt(j)) {
					
					patternMatched = false;
					break;					
				}				
			}
			
			if (patternMatched) {
				return i;
			}
		}		
		return -1;
	}
	
	static boolean doesWordMatchPatternTest(String text, String pattern) {
		
		Map<Character, Character> map = new HashMap<>();
		
		for (int i = 0; i < text.length(); i++) {
			
			char wordCh = text.charAt(i);
			
			char patCh = pattern.charAt(i);
			
			if (map.containsKey(wordCh) && !map.get(wordCh).equals(patCh)) {
				return false;
			} else {
				map.put(wordCh, patCh);
			}			
		}
		return true;
	}
	
}
