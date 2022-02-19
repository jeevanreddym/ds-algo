package zdummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountingDistinctPairs {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1, 1, 2, 2, 3, 3);
		
		//System.out.println(countPairs(numbers, 1));
		
		System.out.println(pairs(1, numbers));
		
	}
	
	
	static int pairs(int k, List<Integer> numbers) {
		
		Set<Integer> uniq = new HashSet<>(numbers);
		numbers = new ArrayList<>(uniq);

	    int res = 0;
	    Map<Integer, Integer> complements = new HashMap<>();
	    for (int num: numbers) {
	        if (complements.containsKey(num)) {
	            res = res + complements.get(num);
	        }
	        if (num - k > 0) {
	            complements.compute(num - k, (key, value) -> (value == null) ? 1 : value + 1);
	        }
	        complements.compute(num + k, (key, value) -> (value == null) ? 1 : value + 1);
	    }
	    return res;
	}
	
	
	public static int countPairs(List<Integer> numbers, int k) {
        if (numbers == null || numbers.size() == 0) {
            return 0;
        }

        int noOfDistinctPairs = 0;
        
        Set<Integer> uniq = new HashSet<>(numbers);
        numbers = new ArrayList<>(uniq);
        numbers.sort((n1,n2) -> n1 - n2);
       
        for (int i=0; i < numbers.size() - 1; i++) {            
            if (search(numbers, i + 1, numbers.size() - 1, numbers.get(i) + k)) {
                noOfDistinctPairs++;
            }
        }
      
        return noOfDistinctPairs;
    }
    
    
    // Returns index of first index of element which is greater than key
    private static boolean search(List<Integer> a, int low, int high, int key) {        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (a.get(mid) == key) 
                return true;
            
            else if (key > a.get(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
	
	
	
	
	
	
	public static int countPairs1(List<Integer> numbers, int k) {
        if (numbers == null || numbers.size() == 0) {
            return 0;
        }
        
        int noOfDistinctPairs = 0;
        
        
        
        Set<Integer> uniq = new HashSet<>(numbers);
        
        numbers = new ArrayList<>(uniq);
        
        
        for (int i=0; i < numbers.size(); i++) {
        	
        	for (int j= i + 1; j < numbers.size(); j++) {
            	
            	if (numbers.get(i) + k == numbers.get(j)) {
            		noOfDistinctPairs++;
            	}
            	
            }
        	
        }
        
        
        
        return noOfDistinctPairs;
	}
	
}
