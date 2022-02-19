package my.learning.algorithms.sorting;

import java.util.Arrays;



/**
 * 	Counting sort is a sorting technique based on keys between a specific range. 
 *
 */
public class CountingSort {

	public static void main(String args[]) {

		char charArr[] = { 'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's' };

		System.out.print("Input character array is ");
		System.out.println(Arrays.toString(charArr));
		
		CountingSort ob = new CountingSort();
		ob.sort(charArr);

		System.out.print("Sorted character array is ");
		System.out.println(Arrays.toString(charArr));

		
		
		
		
		int[] arr = { -5, -10, 0, -3, 8, 5, -1, 10 };
		System.out.print("Input array is ");
		System.out.println(Arrays.toString(arr));
		
		countSort(arr);
		System.out.println("Sorted character array is ");
		System.out.println(Arrays.toString(arr));
		
		
		int[] arr1 = { 25, 20, 0, 3, 8, 5, 1, 10 };
		countingSort(arr1);
		System.out.println("Sorted character array is ");
		System.out.println(Arrays.toString(arr1));
	}

	void sort(char arr[]) {

		int n = arr.length;

		// The output character array that will have sorted arr
		char output[] = new char[n];

		// Create a count array to store count of inidividul characters and initialize
		// count array as 0
		int count[] = new int[256];
		for (int i = 0; i < 256; ++i)
			count[i] = 0;

		// store count of each character
		for (int i = 0; i < n; ++i)
			++count[arr[i]];

		// Change count[i] so that count[i] now contains actual position of this
		// character in output array
		for (int i = 1; i <= 255; ++i)
			count[i] += count[i - 1];

		// Build the output character arrayto make it stable we are operating in reverse
		// order.
		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			--count[arr[i]];
		}

		// Copy the output array to arr, so that arr now contains sorted characters
		for (int i = 0; i < n; ++i)
			arr[i] = output[i];
	}

	
	
	static void countSort(int[] arr) {
		int max = Arrays.stream(arr).max().getAsInt();
		int min = Arrays.stream(arr).min().getAsInt();
		int range = max - min + 1;
		int count[] = new int[range];
		int output[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			count[arr[i] - min]++;
		}

		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			output[count[arr[i] - min] - 1] = arr[i];
			count[arr[i] - min]--;
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] = output[i];
		}
	}
	
	
	
	public static int[] countingSort(int[] a) {
		int max = findMax(a);
		int[] sorted = new int[a.length];
		
		int[] counts = new int[max + 1];
		for (int i = 0; i < a.length; i++)
			counts[a[i]]++;
		
		
		for (int i = 1; i < counts.length; i++)
			counts[i] += counts[i - 1];
		
		for (int i = 0; i < a.length; i++) {
			sorted[counts[a[i]] - 1] = a[i];
			counts[a[i]]--;
		}
		return sorted;
	}

	private static int findMax(int[] a) {
		if (a.length == 0)
			return 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max)
				max = a[i];
		}
		return max;
	}

}