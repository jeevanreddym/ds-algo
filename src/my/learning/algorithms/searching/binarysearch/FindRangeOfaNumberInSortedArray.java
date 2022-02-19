package my.learning.algorithms.searching.binarysearch;




/**
 * 	Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

	If target is not found in the array, return [-1, -1].

	Follow up: Could you write an algorithm with O(log n) runtime complexity?
 */
public class FindRangeOfaNumberInSortedArray {

	public static void main(String[] args) {
		
		int[] result = findRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
		
		result = findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
		
		result = findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
	}
	
	static int[] findRange(int[] nums, int k) {		
		int[] range = new int[] {-1,-1};
		
		range[0] = findKeyIndex(nums, k, 0, nums.length - 1, false); // backward serach.
		if (range[0] != -1) {
			range[1] = findKeyIndex(nums, k, range[0], nums.length - 1, true);  // forward serach.
		}
		
		return range;
	}
	
	static int findKeyIndex(int[] nums, int k, int l, int r, boolean forward) {
		int keyIndex = -1;
		while (l <= r) {
			int mid = l + (r - l) / 2; 
			if (k == nums[mid]) {
				keyIndex = mid;
				if (forward) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}				
			} else if (k > nums[mid]) {
				l = mid + 1;
			} else if (k < nums[mid]) {
				r = mid - 1;
			}
		}		
		return keyIndex;
	}
	
}
