package my.learning.algorithms.arraysStrings.string.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palindrome {

	public static void main(String[] args) {
				
		System.out.println(Palindrome.isPalindrome("nitinn"));
		System.out.println(Palindrome.isPalindrome("nitin"));
		System.out.println(Palindrome.isPalindrome("nittin"));
		
		System.out.println(Palindrome.isPalindrome(12321));
		System.out.println(Palindrome.isPalindrome(123211));
		
	}	

	private static boolean isPalindrome(String str) {		
		char[] chars = str.toCharArray();
		int len = chars.length;
		for (int i=0; i < len/2; i++) {
			if (chars[i] != chars[len - 1 -i])
				return false;
		}
		return true;
	}
	
	private static boolean isPalindrome(int num) {		
		List<Integer> digitsList = split(num);
		Integer[] digits = digitsList.toArray(new Integer[0]);
		int len = digits.length;
		for (int i=0; i < len/2; i++) {
			if (digits[i] != digits[len - 1 -i])
				return false;
		}
		return true;
	}
	
	/**
	 * 	  Division Vocabulary:
	 * 	=======================
	 * 	Dividend / Divisor = Quotient.
	 * 	Dividend % Divisor = Reminder.
	 * 
	 * 	
	 * 	300 	 = 	42  		* 7 		+ 6
	 * 	Dividend = 	Quotient  	* Divisor	+ Reminder
	 * 	 * 
	 */
	private static List<Integer> split(int num) {
		List<Integer> digits = new ArrayList<>();
		int q = num, r = 0;		
		while (q != 0) {			
			r = q % 10;
			digits.add(r);			
			q = q / 10;		
		}
		Collections.reverse(digits);
		return digits;
	}

	
}
