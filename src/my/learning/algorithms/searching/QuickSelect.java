package my.learning.algorithms.searching;




/**
 * 	Best case time complexity: O(n), if we find the kth element in the first iteration itself.
 * 	Worst case time complexity: O(n^2), if we find the kth element in the last iteration.
 * 
 * 	space complexity: O(1) due to tail recursion.
 */
public class QuickSelect {

	public static void main(String[] args) {
		
		int[] nums = {5,4,3,2,1};
				
		int k = 2;
		
		int kthSmallest = kthSmallest(k, nums, 0, nums.length - 1);
		System.out.println(String.format("kth smallest: %s", kthSmallest));	
		
		int kthLargest = kthLargest(k, nums, 0, nums.length - 1);
		System.out.println(String.format("kth largest: %s", kthLargest));	
		
		
		
		kthSmallest = findKthElement(nums, 0, nums.length - 1, k - 1);
		System.out.println(String.format("kth smallest: %s", kthSmallest));	
		
		kthLargest = findKthElement(nums, 0, nums.length - 1, nums.length - k);
		System.out.println(String.format("kth largest: %s", kthLargest));	
	}
	
	/**
	 * 	kth smallest.
	 */
	private static int kthSmallest(int k, int[] nums, int l, int r) {
		
		int indexToFind = k - 1;
		
		if (l < r) {
			
			int partitionIndex = partition(nums, l, r); // after partition we fix the location of the element at "partitionIndex" in its sort order. 
			
			if (indexToFind == partitionIndex) {
				
				return nums[partitionIndex];
				
			} else if (indexToFind < partitionIndex) {
				
				return kthSmallest(k, nums, l, partitionIndex - 1); // repeating quickselect process for sub array left of "partitionIndex".
				
			}  else if (indexToFind > partitionIndex) {
				
				return kthSmallest(k, nums, partitionIndex + 1, r); // repeating quickselect process for sub array right of "partitionIndex".
			}
		}	
		return -1;
	}
	
	/**
	 * 	kth largest.
	 */
	private static int kthLargest(int k, int[] nums, int l, int r) {
		
		int indexToFind = nums.length - k;
		
		if (l < r) {
			
			int partitionIndex = partition(nums, l, r); // after partition we fix the location of the element at "partitionIndex" in its sort order. 
			
			if (indexToFind == partitionIndex) {
				
				return nums[partitionIndex];
				
			} else if (indexToFind < partitionIndex) {
				
				return kthLargest(k, nums, l, partitionIndex - 1); // repeating quickselect process for sub array left of "partitionIndex".
				
			}  else if (indexToFind > partitionIndex) {
				
				return kthLargest(k, nums, partitionIndex + 1, r); // repeating quickselect process for sub array right of "partitionIndex".
			}
		}	
		return -1;
	}

	private static int partition(int[] nums, int l, int r) {
		
		int pivotIndex = r; // here we choose pivotal index as the right most index of the array.
		
		r--;
		
		while (l <= r) {
			
			if (nums[l] > nums[pivotIndex] && nums[r] < nums[pivotIndex]) {
				
				swap(nums, l, r);
				
				l++;
				r--;
				
			} else {
			
				if (nums[l] < nums[pivotIndex]) {
					l++;
				}
				
				if (nums[r] > nums[pivotIndex]) {
					r--;
				}
			}			
		}
		
		swap(nums, l, pivotIndex);
		
		return l;
	}

	
	/**	
	 * 	multiple ways to choose pivotal index:
	 * 		
	 * 		1) right most index,
	 * 		2) left most index,
	 * 		3) random index,
	 * 		4) median etc...
	 */
	static int findPivotIndex(int[] nums, int l, int r) {
		
		int m = l + (r - l)/2;
		
		return m;
	}
	
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	/**
	 * 	find kth largest/snallest element
	 */
	private static int findKthElement(int[] nums, int l, int r, int indexToFind) {
		
		if (l < r) {
			
			int partitionIndex = partition(nums, l, r); // after partition we fix the location of the element at "partitionIndex" in its sort order. 
			
			if (indexToFind == partitionIndex) {
				
				return nums[partitionIndex];
				
			} else if (indexToFind < partitionIndex) {
				
				return findKthElement(nums, l, partitionIndex - 1, indexToFind); // repeating quickselect process for sub array left of "partitionIndex".
				
			}  else if (indexToFind > partitionIndex) {
				
				return findKthElement(nums, partitionIndex + 1, r, indexToFind); // repeating quickselect process for sub array right of "partitionIndex".
			}
		}	
		return -1;
	}
	
}
