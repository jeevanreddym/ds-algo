package my.learning.algorithms.recursion;


/**
 * 	Max call stack: 10,000 calls approx.
 */
public class MaxCallStackCheck {

	public static void main(String[] args) {		
		
		callMe(0);
	}
	
	static void callMe(int n) {
		
		System.out.println(n);
		
		if (n == 11410) {
			return;
		}
		
		callMe(n + 1);
	}	
	
}
