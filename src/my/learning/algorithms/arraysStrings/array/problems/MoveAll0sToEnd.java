package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;

public class MoveAll0sToEnd {

	
	public static void main(String[] args) {
		
		int arr[] = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };
				
		pushZerosToEnd(arr);
		
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 	O(n) time, O(1) space.
	 */
	private static void pushZerosToEnd(int[] arr) {
				
		int count = 0; // Count of non-zero elements

		// Traverse the array. If element encountered is
		// non-zero, then replace the element at index 'count' with this element.
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				arr[count] = arr[i]; // here count is incremented.
				count++;
			}
		}

		// Now all non-zero elements have been shifted to front and 'count' is set as index of first 0.
		// Make all elements 0 from count to end.
		while (count < arr.length) {
			arr[count] = 0;
			count++;
		}
		
	}

}
