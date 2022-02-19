package my.learning.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {

	public static void main(String[] args) {
		
		BucketSort sorter = new BucketSort();
		
		int[] nums = {43, 17, 87, 92, 31, 6, 96, 13, 66, 62, 4};
		List<Integer> sortedNums = sorter.bucketSort(nums);
		for (int sortedNum: sortedNums) {
			System.out.print(sortedNum + " ");
		}		
	}

	private List<Integer> bucketSort(int[] nums) {
		List<Integer> sorted = new ArrayList<>(nums.length);
		
		int numOfBuckets = 4;
		
		// Create buckets.
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(i, new ArrayList<>());
        }
		
        // Iterate through the passed array and add each integer to the appropriate bucket.
        for (int i=0; i < nums.length; i++) {
        	int bucket = i % numOfBuckets;
        	buckets.get(bucket).add(nums[i]);
        }
                
        // Sort each bucket and add it to the result List.
        for (int i = 0; i < numOfBuckets; i++) {
            List<Integer> temp = insertionSort(buckets.get(i));
            sorted.addAll(temp);
        }
		
		return sorted;		
	}
	
	//Insertion Sort
	public static List<Integer> insertionSort(List<Integer> input) {
		for (int i = 1; i < input.size(); i++) {
			// 2. Store the current value in a variable
			int currentValue = input.get(i);
			int pointer = i - 1;

			// 3. As long as we are pointing to a valid value in the array...
			while (pointer >= 0) {
				// 4. If the current value is less than the value we are pointing at...
				if (currentValue < input.get(pointer)) {
					// 5. Move the pointed-at value up one space, and insert the current value at the pointed-at position.
					input.set(pointer + 1, input.get(pointer));
					input.set(pointer, currentValue);
				} else
					break;
			}
		}
		return input;
	}
	
}
