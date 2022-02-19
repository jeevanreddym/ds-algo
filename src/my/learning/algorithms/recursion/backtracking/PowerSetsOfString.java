package my.learning.algorithms.recursion.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 	find all possible substrings of a string -> all combinations of all possible lengths.
 * 
 * 	"abc" => [a, ab, abc, b, bc, c]
 * 
 *  @PowerSetsOfArray
 */
public class PowerSetsOfString {

	public static void main(String[] args) {
		
		String str = "abc"; // ["", "a", "b", "c", "ab", "bc", "ca", "abc"]
		
		List<String> powersets = new LinkedList<>();		
		combinations(str, new StringBuilder(), 0, powersets);		
		System.out.println(Arrays.toString(powersets.toArray()));
		
		
		System.out.println("\n\n");
		
		powersets = new LinkedList<>();		
		combinations("", str, powersets);
		System.out.println(Arrays.toString(powersets.toArray()));
	}

	
	private static void combinations(String str, StringBuilder partial, int i, List<String> combinations) {
	
		if (i == str.length()) {
			combinations.add(partial.toString());
			return;
		}
		
		
		// with item.
				
		partial.append(str.charAt(i));
		
		combinations(str, partial, i + 1, combinations);
		
		
		// without item.
		
		partial.deleteCharAt(partial.length() - 1);
		
		combinations(str, partial, i + 1, combinations);		
	}
	
	
	public static void combinations(String prefix, String suffix, List<String> substrings) {
	    
		substrings.add(prefix);
	    
	    for (int i = 0; i < suffix.length(); i++) {
	
	    	combinations(prefix + suffix.charAt(i), suffix.substring(i + 1), substrings);
	    	
	    }
	}
	
}
