package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 	All possible combinations of all sizes of a set [power set is (set of all subsets)].
 * 
 *  combinations(set, size)
 *  ------------------------ 
 * 	c({1,2,3}, 0) => ['']
 *  c({1,2,3}, 1) => [1,2,3]
 *  c({1,2,3}, 2) => [12,13,23]
 * 	c({1,2,3}, 3) => [123]
 * 
 * 	powerset => all possible combinations of all sizes of a set.
 *  -------------------------------------------------------------
 * 	ps({1,2,3}) => c({1,2,3} -> 0,1,2,3) => ['', 1, 2, 3, 12, 13, 23, 123]
 * 
 * 
 * 	formula: ps(str + char) => ps(str) + char * ps(str)
 * 
 * 	therefore time complexity is O(2^n), since increasing 1 char in the i/p string will double the size of output set. 
 * 
 */
public class PowerSetsOfArray {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3};
		
		
		System.out.println("Recursive solution");
		List<List<Integer>> results = powerSets(nums); // recursive.
		results.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		
		System.out.println("Iterative solution");
		results = findSubsets(nums); // iterative.
		//results.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		
		System.out.println("Recursive alternative solution");
		results = subsets(nums); // recursive alternate.
		results.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
	}
	
	
	

	/**
	 * 	Recursive.
	 */
	private static void powersets(int[] nums, List<Integer> partial, int i, List<List<Integer>> res) {
		
		if (i == nums.length) {
			res.add(new ArrayList<>(partial));
			return;
		}
		
		// with item at index i.
		partial.add(nums[i]);
		powersets(nums, partial, i + 1, res);

		// without item at index i.
		partial.remove(partial.size() - 1);
		powersets(nums, partial, i + 1, res);
	}
	
	private static List<List<Integer>> powerSets(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		powersets(nums, new LinkedList<>(), 0, res);
		return res;
	}

	/**
	 * 	Iterative.
	 */
	private static List<List<Integer>> findSubsets(int[] nums) {
		
		List<List<Integer>> subsets = new LinkedList<>();
		
		// start by adding the empty subset [].
		subsets.add(new LinkedList<>());
		
		for (int currentNumber: nums) {
			
			// we will take all existing subsets and insert the current number in them to create new subsets	
			int currSubsetsSize = subsets.size();
			for (int i = 0; i < currSubsetsSize; i++) {
				
				List<Integer> currSubset = subsets.get(i);
				
				// create a new subset from the existing subset and insert the current element to it
				List<Integer> newSubset = new ArrayList<>(currSubset);				
				newSubset.add(currentNumber);
				
				subsets.add(newSubset);
			}
		}
		
		return subsets;
	}

	
	/**
	 * 	alternate.
	 */
	private static List<List<Integer>> subsets(int[] nums) {
		
		List<List<Integer>> results = new LinkedList<>();
		
		
		/**
		 * 	size   elements
		 * 
		 *   0 	   []
			 1     [1, 2, 3]
			 2	   [[1,2], [1,3], [2,3]]
			 3     [1, 2, 3]
		 */		
		for (int size = 0; size <= nums.length; size++) { // subsets of all sizes.
			
			subsets(nums, size, new LinkedList<>(), 0, results);
		}		
		
		return results;
	}
	
	/**
	 * 	generate subsets of size k
	 */
	private static void subsets(int[] nums, int k, List<Integer> partial, int start, List<List<Integer>> results) {

		if (partial.size() == k) {
			results.add(new ArrayList<>(partial));
			return;
		}
		
		if (start == nums.length) {
			return;
		}

		for (int i = start; i < nums.length; i++) {

			partial.add(nums[i]);

			subsets(nums, k, partial, i + 1, results);

			partial.remove(partial.size() - 1);
		}
	}

}
