package my.learning.algorithms.sorting;

import java.util.Arrays;

public class CyclicSort {

	
	
	public static void main(String[] args) {
		int[] arr = new int[] { 3, 1, 5, 4, 2 };
		CyclicSort.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println();

		arr = new int[] { 2, 6, 4, 3, 1, 5 };
		CyclicSort.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println();

		arr = new int[] { 1, 5, 6, 4, 3, 2 };
		CyclicSort.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println();
	}
	
	private static void sort(int[] arr) {
		
		
		
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
