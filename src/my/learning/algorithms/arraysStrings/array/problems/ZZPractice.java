package my.learning.algorithms.arraysStrings.array.problems;

public class ZZPractice {

	
	public static void main(String[] args) {
		
		int sum = 35;
		int n = 4;
		
		System.out.println(getAvg(sum, n));
		
		System.out.println(getAvg((double) sum, n));
	}
	
	
	private static int getAvg(int sum, int n) {
		
		double avg = (double) sum / n;	
		
		return (int) avg;
	}
	
	
	private static double getAvg(double sum, int n) {
		
		double avg = (double) sum / n;	
		
		return avg;
	}
	
}
