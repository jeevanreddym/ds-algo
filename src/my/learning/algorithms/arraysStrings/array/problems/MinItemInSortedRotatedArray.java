package my.learning.algorithms.arraysStrings.array.problems;

public class MinItemInSortedRotatedArray {

	public static void main(String[] args) {
		
		int[] arr = {4,5,6,7,1,2,3}; // rotated sorted array.
		
		int minItem = getMin(arr);
		System.out.println(minItem);
	}
	
	/**
	 * 	time: O(log(n)).
	 */
	private static int getMin(int[] arr) {
		
		int l = 0;
		int r = arr.length - 1;
				
		while (l <= r) {
		
			int m = l + (r - l)/2;
			
			if (m > 0 && arr[m - 1] > arr[m]) {
				
				return arr[m];
				
			} else if (arr[l] <= arr[m] && arr[m] > arr[r]) {
				
				l = m + 1;
				
			} else {
				
				r = m - 1;
			}
			
		}		
		return arr[0];
	}
	
}
