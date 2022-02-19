package my.learning.algorithms.arraysStrings.string.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 	problem statement: find all the occurrences of a pattern in the given text.
 * 
 * 	Rabin-Karp: rolling hash, pattern matching => algorithm uses hashing as a base in order to find all the occurrences
 * 	
 */
public class RabinKarpPatternMatching {

	
	
	public static void main(String[] args) {
		
//		List<Integer> occurrences = search("ABACABAD", "ABA");
//		System.out.println(occurrences); // [0, 4]
//		
//		occurrences = search("AAAA", "AA");
//		System.out.println(occurrences); // [0, 1, 2]
				
		System.out.println(rabinKarp("BACABAD", "ABA"));		
	}
	
	static int rabinKarp(String text, String pattern) {
				
		// finding the hash of the pattern.
		long patternHash = hash(pattern, 0, pattern.length() - 1);
		
		
		// finding the hash for all the subpatterns(of length=pattern�s length) of the text.
		long currSubstrHash = hash(text, 0, pattern.length() - 1);
		
		
		
		// matching the subpattern of text and the pattern, if any hash for the subpattern of the text is equal to the pattern�s hash
		
		int prime = 3;
		
		int i  = 0; 
		int j = pattern.length() - 1;
		
		while (j < text.length()) {
			
			if (currSubstrHash == patternHash) {
				return i;
			} 
			
			else { // rolling hash method (sliding window technique).
				
				/**
				 * 	remove the hash val of outgoing index value (i).
				 * 	and add the hash value of the incoming index (j).
				 */
							
				currSubstrHash = currSubstrHash - (text.charAt(i) - 'A');
				i++;
				
				currSubstrHash = currSubstrHash / prime;
								
				j++;
				currSubstrHash = currSubstrHash + (long)((text.charAt(j) - 'A') * Math.pow(prime, pattern.length() - 1));				
			}						
		}
		
		return -1;
	}	

	/**
	 * 	hash("abc") = [(97 * 3^0) + (98 * 3^1) + (99 * 3^2)]
	 */
	static long hash(String str, int i, int j) {
				
		long hashcode = 0;
		
		int prime = 3;
		int pow = 0;
		
		while (i <= j) {
			
			Character ch = str.charAt(i);
			
			int intVal = ch - 'A';
			
			hashcode += intVal * Math.pow(prime, pow);
			
			pow++;	
			
			i++;
		}
				
		return hashcode;
	}
	
	
	
	
	
	
	
	
	
	
	public static List<Integer> search(String text, String pattern) {
		
		List<Integer> occurrences = new ArrayList<>();
		
		
		int a = 53;
	    long m = 1_000_000_000 + 9;
	 
		
		long patternHash = 0;
	    long currSubstrHash = 0;
	    long pow = 1;
		
	    for (int i=0; i < pattern.length(); i++) {
	        
	    	patternHash += charToLong(pattern.charAt(i)) * pow;
	    	patternHash %= m;
	        
	        
	    	currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
	        currSubstrHash %= m;
	    	
	        if (i != pattern.length() - 1) {
	            pow = pow * a % m;
	        }
	    }
	    
	    
	    for (int i = text.length(); i >= pattern.length(); i--) {
	    
	    	if (patternHash == currSubstrHash) {
	            boolean patternIsFound = true;
	         
	            for (int j = 0; j < pattern.length(); j++) {
	                if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
	                    patternIsFound = false;
	                    break;
	                }
	            }
	 
	            if (patternIsFound) {
	                occurrences.add(i - pattern.length());
	            }
	    	}
	    	
			if (i > pattern.length()) {			
				currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
				currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
			}
	    }
					
	    Collections.reverse(occurrences);
	    return occurrences;
	}

	
	public static long charToLong(char ch) {
	    return (long)(ch - 'A' + 1);
	}	

}
