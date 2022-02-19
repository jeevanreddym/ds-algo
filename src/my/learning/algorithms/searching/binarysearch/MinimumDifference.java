package my.learning.algorithms.searching.binarysearch;

class MinimumDifference {


	public static void main(String[] args) {
		System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
		System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
		System.out.println(searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
		System.out.println(searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
	}
	
	
	public static int searchMinDiffElement(int[] arr, int key) {		
		
		int l = 0, r = arr.length - 1;
		
		if (key < arr[l]) {
			return arr[l];
		} else if (key > arr[r]) {
			return arr[r];
		}
		
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (key > arr[mid]) {
				l = mid + 1;
			} else if (key < arr[mid]) {
				r = mid - 1;
			} else {
				return arr[mid];
			}
		}
		
		int rtKey = Math.abs(arr[r] - key);
		int leftKey = Math.abs(arr[l] - key);
		
		if (rtKey < leftKey) {
			return arr[r]; 
		} else {
			return arr[l]; 
		}
	}

}
