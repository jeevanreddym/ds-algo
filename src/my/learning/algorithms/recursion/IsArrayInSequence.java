package my.learning.algorithms.recursion;

public class IsArrayInSequence {

	public static void main(String[] args) {
		
		int[] arr = {2,3,4,5,6};
		
		System.out.println(isAscendingOrder(arr, arr.length - 1));
	}

	private static boolean isAscendingOrder(int[] arr, int i) {
		
		// base condition.
		if (i == 0) {
			return true;
		}
				
		// main logic in this method.
		if (arr[i] > arr[i - 1]) {
			
			return isAscendingOrder(arr, i - 1); // delegate the next item logic to recursion.
		}
		
		return false; // failed case.
		
		//return (arr[i] > arr[i - 1]) && isAscendingOrder(arr, i - 1);
	}
	
}
