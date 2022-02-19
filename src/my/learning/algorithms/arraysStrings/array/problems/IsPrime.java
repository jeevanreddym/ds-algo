package my.learning.algorithms.arraysStrings.array.problems;

public class IsPrime {

	public static void main(String[] args) {
		
		System.out.println(isPrime(5));
		System.out.println(isPrime(6));
		System.out.println(isPrime(7));
		System.out.println(isPrime(8));
		System.out.println(isPrime(9));
	}
	
	private static boolean isPrime(int num) {		
		
		for (int i=2; i <= Math.sqrt(num); i++) {
		
			if (num % i == 0) {
				
				return false;
			}			
		}
		
		return true;
	}
	
}
