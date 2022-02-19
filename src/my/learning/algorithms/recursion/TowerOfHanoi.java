package my.learning.algorithms.recursion;

public class TowerOfHanoi {

	
	public static void main(String[] args) {
		
		move(7, "A", "B", "C");		
	}
	
	
	/**
	 * 	return the number of steps required to move n disks (arranged from small on top to big at bottom)
	 * 	from A (source rod) to C (destination rod) using B (an auxillary rod).
	 */
	private static int move(int n, String start, String tmp, String end) {
		
		if (n == 1) {
			
			System.out.println("Move " + start + " to " + end);
			
		} else {
			
			move(n - 1, start, end, tmp);
			System.out.println("Move " + start + " to " + end);
		
			
			move(n - 1, tmp, start, end);			
		}
		
		return 0;
	}
	
}
