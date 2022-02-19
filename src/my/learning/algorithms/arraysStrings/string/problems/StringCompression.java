package my.learning.algorithms.arraysStrings.string.problems;







public class StringCompression {

	
	public static void main(String[] args) {
		
		System.out.println(compressString("a"));
		System.out.println(compressString("aaa"));
		System.out.println(compressString("aaabbb"));
		System.out.println(compressString("aaabccc"));
	}
	
	/**
	 * 	O(n) time & O(n) space.
	 */
	private static String compressString(String s) {
		
		StringBuilder sb = new StringBuilder();
	    
		int cnt = 1;
	    
		for (int i = 0; i < s.length() - 1; i++) {
	    
			if (s.charAt(i) == s.charAt(i + 1)) {
	        
				cnt++; // increment counter.
	        } 
			
			else {
				
	        	sb.append(s.charAt(i)); // append previous char with count of occurrences.
	        	sb.append(cnt);
	        	
	        	cnt = 1; // reset counter.
	        }
	    }
	    
		sb.append(s.charAt(s.length() - 1)); // appending last char count also as that would be missed in the loop.
		sb.append(cnt);
	    
	    return sb.toString();
	}
	
}
