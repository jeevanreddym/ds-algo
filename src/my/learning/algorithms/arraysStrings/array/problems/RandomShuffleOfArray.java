package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomShuffleOfArray {

	public static void main(String[] args) {
		
		//System.out.println(getRandomInRange(2,5));
		
//		for (int i=1; i <= 10; i++) {
//			System.out.print(getRandomInRange(2,5) + ",");
//		}
		
		
		
		
		Map<String, Integer> freqs = new HashMap<>();
		
		for (int i = 1; i <= 1000000; i++) {

			int[] nums = {1,2,3,4,5,6};
			
			randomShuffle(nums);
			
			String numsStr = Arrays.stream(nums).mapToObj(num -> String.valueOf(num)).collect(Collectors.joining());
			
			freqs.put(numsStr, freqs.getOrDefault(numsStr, 0) + 1);
		}
		
		for (Entry<String, Integer> e: freqs.entrySet()) {
			System.out.println(e.getKey() + "->" + e.getValue());
		}
		
	}
	
	public static void randomShuffle(int[] arr) {
		
		int lastIndex = arr.length - 1;
		
		// for each index in the array
	    for (int currIndex = 0; currIndex <= lastIndex; currIndex++) {
	    	
	    	// grab a random other index
	        //int randomIndex = getRandomInRange(0, arr.length - 1);
	        int randomIndex = getRandomInRange(currIndex, lastIndex);
	        
	        // and swap the values
	        if (randomIndex != currIndex) {
	            
	            swap(arr, currIndex, randomIndex);
	        }
	    }		
	}
	
	/**
	 * 	return num in range, floor & ceil inclusive.
	 */
	static int getRandomInRange(int l, int r) {	 
		
		Random random = new Random();
		
		return random.nextInt(r - l + 1) + l;		
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
	}
	
}
