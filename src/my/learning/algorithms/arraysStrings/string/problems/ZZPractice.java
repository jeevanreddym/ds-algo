package my.learning.algorithms.arraysStrings.string.problems;

public class ZZPractice {

	public static void main(String[] args) {
		
		
		
		
		System.out.println("a bcd, : ef !lllllllllllllllllllllll".replaceAll("[ ,:!]", ""));
		
		
		
		
		
		String str = "abcdef";
		
		// substr().
		
		//System.out.println(str.substring(0));
		
		System.out.println(str.substring(0, str.length())); // beginIndex inclusive & endIndex exclusive.
		
		System.out.println(str.substring(0, str.length() - 1));
		
		//System.out.println(str.substring(1));
		
		System.out.println(str.substring(1, str.length() - 1));
		
		
		
		// substr().
				
		System.out.println("\n\n" + str.subSequence(0, str.length())); // beginIndex inclusive & endIndex exclusive.
		
		System.out.println(str.subSequence(0, str.length() - 1));
				
		System.out.println(str.subSequence(1, str.length() - 1));
	} 
	
}
