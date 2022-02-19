package my.learning.algorithms.searching;

public class BinarySearch {

	public static void main(String[] args) {
		
		int[] nums =  {-3, -1, 0, 2,4,7,9,14,56};
		int k = 56;
		System.out.println(binarySearch(nums, k));
				
		System.out.println(recursiveBinarySearch(nums, k));
	}
	
	/**
	 * 	iterative binary search.
	 */
	private static int binarySearch(int[] nums, int k) {
		
		int l = 0, r = nums.length - 1;
		
		while (l <= r) {
			
			int mid = l + (r - l)/2;
			
			if (nums[mid] == k) {
				
				return mid; 
			} 
			
			else if (k > nums[mid]) {
				
				l = mid + 1;
			}
			
			else if (k < nums[mid]) {
			
				r = mid - 1;
			}			
		}	
		
		return -1;
	}
	
	
	static int recursiveBinarySearch(int[] nums, int k) {
		return binarySearch(nums, k, 0, nums.length - 1);
	}
	
	static int binarySearch(int[] nums, int k, int l, int r) {
		
		if (l <= r) {
			
			int mid = l + (r - l)/2;
			
			if (nums[mid] == k) {
			
				return mid;
			}
			
			else if (k < nums[mid]) {
				
				return binarySearch(nums, k, l, mid - 1);
			}
			
			else if (k > nums[mid]) {
				
				return binarySearch(nums, k, mid + 1, r);
			}
			
		}		
		return -1;
	}
	
}
