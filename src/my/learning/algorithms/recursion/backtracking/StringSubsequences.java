package my.learning.algorithms.recursion.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Powersets of a string.
 * 
 *  i/p: "abc"
 *  
 *  o/p: ["abc", "ab", "ac", "bc", "a", "b", "c", ""]
 *   
 */

/**
 * 	All possible combinations of all sizes of a set [power set is (set of all subsets)].
 * 
 *  combinations(set, size)
 *  ------------------------ 
 * 	c("abc", 0) => [""]
 *  c("abc", 1) => [a,b,c]
 *  c("abc", 2) => [ab,bc,ca]
 * 	c("abc", 3) => [abc]
 * 
 * 	powerset => all possible combinations of all sizes of a set.
 *  -------------------------------------------------------------
 * 	ps("abc") => ['', a, b, c, ab, bc, ca, abc]
 * 
 * 	ps(str + char) => ps(str) + char * ps(str)
 * 
 * 	therefore time complexity is O(2^n), since increasing 1 char in the i/p string will double the size of output set. 
 * 
 */
public class StringSubsequences {

	public static void main(String[] args) {
		
		List<String> subsequences = getSubsequences("abc");
		System.out.println(Arrays.toString(subsequences.toArray()));
	}
	
	/**
	 * 	comma separated sub sequences of a string.
	 */	
	private static List<String> getSubsequences(String word) {
		List<String> subsequences = new LinkedList<>();		
		getSubsequences("", word, 0, subsequences);
		return subsequences;
	}
	
	private static void getSubsequences(String prefix, String suffix, int i, List<String> subsequences) {
		
		if (i == suffix.length()) {
			subsequences.add(prefix);
			return;
		}
		
		// with char at index i.		
		prefix += String.valueOf(suffix.charAt(i));
		getSubsequences(suffix, prefix, i + 1, subsequences);
		
		// without char at index i.
		prefix = prefix.substring(0, prefix.length() - 1);		
		getSubsequences(suffix, prefix, i + 1, subsequences);		
	}	
	
}
