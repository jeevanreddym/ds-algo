package zdummy;

import java.util.Arrays;
import java.util.Random;

public class Dummy {

	public static void main(String[] args) {

		int[] nums = {6,5,4,3,2,1};
		
		int[] mergeSorted = mergeSort(nums);
		System.out.println(Arrays.toString(mergeSorted));	
		
		
		shuffle(nums);
		
		int[] quickSorted = quickSort(nums);
		System.out.println(Arrays.toString(quickSorted));
	}

	private static int[] mergeSort(int[] nums) {
		return split(nums);
	}
	
	/**
	 * 	split arrays into 2 halves.
	 */
	private static int[] split(int[] nums) {	
		
		if (nums.length == 1) {
			return nums;
		}
		
		int l = 0, r = nums.length - 1;
		
		int m = l + (r - l) / 2;
		
		int[] ltArr = Arrays.copyOfRange(nums, l, m + 1);
		
		int[] rtArr = Arrays.copyOfRange(nums, m + 1, r + 1);
				
		ltArr = split(ltArr);
		
		rtArr = split(rtArr);
		
		return merge(ltArr, rtArr);
	}
	
	/**
	 * 	merge 2 sorted arrays.
	 */
	private static int[] merge(int[] a, int[] b) {
		
		int[] merged = new int[a.length + b.length];
		
		int i=0, j=0, k=0;
		
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				merged[k++] = a[i++];
			} else {
				merged[k++] = b[j++];
			}
		}
		
		while (i < a.length) {
			merged[k++] = a[i++];
		}
		
		while (j < b.length) {
			merged[k++] = b[j++];
		}
		
		return merged;
	}
	
	
	
	private static int[] quickSort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}
	
	private static void quickSort(int[] nums, int l, int r) {
		if (l < r) {
			int pivotIndex = partition(nums, l, r);
			quickSort(nums, l, pivotIndex - 1);
			quickSort(nums, pivotIndex + 1, r);
		}
	}
	
	private static int partition(int[] nums, int l, int r) {
		
		int pivotIndex = r;
		int pivotVal = nums[pivotIndex];
		
		r--;
		
		while (l <= r) {			
			if (nums[l] > pivotVal && nums[r] < pivotVal) {
				swap(nums, l, r);
				l++;
				r--;
			} else {
				if (nums[l] < pivotVal) {
					l++;
				}
				if (nums[r] > pivotVal) {
					r--;
				}
			}			
		}
		
		swap(nums, l, pivotIndex);
				
		return l;
	}
	
	
	private static int[] shuffle(int[] nums) {
		Random random = new Random();
		for (int i=0; i < nums.length; i++) {			
			int randomIndex = random.nextInt(nums.length);
			swap(nums, i, randomIndex);
		}
		return nums;
	}
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
}
