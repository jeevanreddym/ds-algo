package my.learning.algorithms.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



/**
 * 
 * 	Quadratic functions grow by adding an increasing amount. O(n^2)
	
	Exponential functions grow by multiplying by a constant amount. O(2^n)
	
	Factorial functions grow by multiplying by an increasing amount. O(n!)
 * 
 */
public class PermutationsCombinationsArray {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3};
		
		/**
		 * 	Permutations (n!).
		 */
		List<List<Integer>> allPermutations = permutations(nums);		
		System.out.println("Permutations"); 
		allPermutations.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		System.out.println(allPermutations.size());
		
//		allPermutations = permutationsAlternative(nums);
//		System.out.println("Permutations alternative ");
//		allPermutations.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
//		System.out.println(allPermutations.size());
			
		/**
		 * 	r length permutations of a set of n distinct numbers (nPr = n!/(n - r)!).
		 */
//		allPermutations = kLengthPermutations(nums, 3);
//		System.out.println("k length Permutations");
//		allPermutations.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
//		System.out.println(allPermutations.size());
		
		
		/**
		 * 	Combinations.
		 */
		List<List<Integer>> allCombinations = combinations(nums, 2);		
		System.out.println("Combinations"); 
		allCombinations.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
		System.out.println(allCombinations.size());
//		
//		
//		allCombinations = combinationAlternative(nums, 3);		
//		System.out.println("Combinations Alternative"); 
//		allCombinations.forEach(row -> System.out.println(Arrays.toString(row.toArray())));
//		System.out.println(allCombinations.size());
	}

	/**
	 * 	all permutations of an array of numbers.
	 */
	private static List<List<Integer>> permutations(int[] nums) {		
		List<List<Integer>> allPermutations = new ArrayList<>();		
		permutations(nums, new ArrayList<>(), new boolean[nums.length], allPermutations);		
		return allPermutations;
	}
	
	private static void permutations(int[] nums, List<Integer> partial, boolean[] used, List<List<Integer>> allPermutations) {
						
		if (partial.size() == nums.length) { // if partial o/p matches our criteria then add it to the actual o/p.			
			allPermutations.add(new ArrayList<>(partial));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			
			if (!used[i]) { // check if current choice is already in the partial o/p.
							
				used[i] = true;			
				partial.add(nums[i]); // add next option to partial o/p.
				
				permutations(nums, partial, used, allPermutations); // recursive call with next choice.
				
				used[i] = false;
				partial.remove(partial.size() - 1);	// remove option from partial solution.			
			}
		}
	}
	
	/**
	 * 	permutations alternative method.
	 */
	private static List<List<Integer>> permutationsAlternative(int[] nums) {		
		List<List<Integer>> allPermutations = new ArrayList<>();		
		permutations(nums, 0, allPermutations);		
		return allPermutations;
	}
	
	private static void permutations(int[] nums, int currPos, List<List<Integer>> allPermutations) {
				
		if (currPos == nums.length - 1) {
			allPermutations.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
			return;
		}
		
		for (int i = currPos; i < nums.length; i++) {
			
			swap(nums, currPos, i);
			
			permutations(nums, currPos + 1, allPermutations);
			
			swap(nums, currPos, i);			
		}		
	}

	private static void swap(int[] nums, int i, int j) {				
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	
	
	/**
	 * 	k length permutations, nPk = p(n,k) = n!/(n - k)!.
	 */
	private static List<List<Integer>> kLengthPermutations(int[] nums, int k) {		
		List<List<Integer>> allPermutations = new ArrayList<>();		
		kLengthPermutations(nums, k, new ArrayList<>(), new boolean[nums.length], allPermutations);		
		return allPermutations;
	}
	
	private static void kLengthPermutations(int[] nums, int k, List<Integer> partial, boolean[] used, List<List<Integer>> kLengthPerms) {
						
		if (partial.size() == k) { // if partial o/p matches our criteria then add it to the actual o/p.			
			kLengthPerms.add(new ArrayList<>(partial));
			return;
		}
		
		for (int i=0; i < nums.length; i++) {
			
			if (!used[i]) { // check if current choice is already in the partial o/p.
							
				used[i] = true;			
				partial.add(nums[i]); // add next option to partial o/p.
							
				kLengthPermutations(nums, k, partial, used, kLengthPerms); // recursive call with next choice.
							
				used[i] = false;		
				partial.remove(partial.size() - 1);	// remove option from partial solution.			
			}
		}
	}
	
	
	
	
	/**
	 * 	n choose k (combinations), nCk = C(n,k) = n!/(n - k)! * k!.
	 */
	private static List<List<Integer>> combinations(int[] nums, int k) {		
		List<List<Integer>> allCombinations = new ArrayList<>();		
		combinations(nums, k, new HashSet<>(), 0, allCombinations);		
		return allCombinations;
	}	
	
	private static void combinations(int[] nums, int k, Set<Integer> partial, int currPos, List<List<Integer>> allCombinations) {
		
		if (partial.size() == k) {			
			allCombinations.add(new ArrayList<>(partial));
			return;
		}
		
		if (currPos == nums.length) {			
			return;
		}
		
		for (int i = currPos; i < nums.length; i++) {
			
			partial.add(nums[i]);
			
			combinations(nums, k, partial, i + 1, allCombinations);
		
			partial.remove(nums[i]);			
		}		
	}
	
	
	
	
	/**
	 * 	alternative approach where we try both combinations (including & excluding) of the current element.
	 */
	private static List<List<Integer>> combinationAlternative(int[] nums, int k) {		
		List<List<Integer>> allCombinations = new ArrayList<>();
		combinationAlternative(nums, k, new HashSet<>(), 0, allCombinations);
		return allCombinations;
	}
	
	public static void combinationAlternative(int[] nums, int k, Set<Integer> partial, int i, List<List<Integer>> allCombinations) {
		
		if (partial.size() == k) {
			allCombinations.add(new ArrayList<>(partial));
			return;
		}
		
		if (i == nums.length) {
			return;
		}
	
		// with digit at position i.
		partial.add(nums[i]);		
		combinationAlternative(nums, k, partial, i + 1, allCombinations);
		
		// without digit at position i.
		partial.remove(nums[i]);		
		combinationAlternative(nums, k, partial, i + 1, allCombinations);
	}
	
}