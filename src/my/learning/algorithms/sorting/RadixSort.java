package my.learning.algorithms.sorting;

import java.util.Arrays;

public class RadixSort {

	
	public static void main(String[] args) {
		int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };		
		radixsort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}


	// The main function to that sorts arr[] of size n using Radix Sort
	static void radixsort(int arr[], int n) {
		
		// Find the maximum number to know number of digits
		int largestNumber = getLargestNumberInArray(arr);

		// Do counting sort for every digit. Note that instead of passing digit number, 
		// exp is passed. exp is 10^i where i is current digit number
		for (int position = 1; largestNumber / position > 0; position = position * 10) { 
			
			countSort(arr, n, position);			
		}			
	}
	

	// A function to do counting sort of arr[] according to the digit represented by exp.
	static void countSort(int arr[], int n, int position) {
		
		int[] output = new int[n]; // output array
		
		int[] count = new int[10];
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]		
		for (int i = 0; i < n; i++) {
			count[(arr[i] / position) % 10]++;
		}			

		// Change count[i] so that count[i] now contains actual position of this digit in output[]
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}			

		// Build the output array
		for (int i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / position) % 10] - 1] = arr[i];
			count[(arr[i] / position) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}			
	}

	// A utility function to get maximum value in arr[]
	static int getLargestNumberInArray(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}				
		}			
		return max;
	}

}
