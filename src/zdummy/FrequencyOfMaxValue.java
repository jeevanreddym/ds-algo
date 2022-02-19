package zdummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FrequencyOfMaxValue {
	
	
	public static void main(String[] args) {
		
		List<Integer> arr = Arrays.asList(5, 4, 5, 3, 2);
		List<Integer> maxFreqsVals = frequencyOfMaxValue(arr);
		System.out.println(Arrays.toString(maxFreqsVals.toArray()));
		
//		arr = Arrays.asList(2,1,2);
//		maxFreqsVals = frequencyOfMaxValue(arr);
//		System.out.println(Arrays.toString(maxFreqsVals.toArray()));
//		
//		arr = Arrays.asList(2,2,2);
//		maxFreqsVals = frequencyOfMaxValue(arr);
//		System.out.println(Arrays.toString(maxFreqsVals.toArray()));
	}
	
	
	public static List<Integer> frequencyOfMaxValue(List<Integer> arr) {

		Map<Integer, Integer> maxFreqs = new HashMap<>();
		
		for (int i=0; i < arr.size(); i++) {
			
			int max = arr.get(i);
			
			maxFreqs.put(i, 1);
			
			
			for (int j = i + 1; j < arr.size(); j++) {		
				
				if (arr.get(j) > max) {
					
					max = arr.get(j);
					
					maxFreqs.put(i, 1);
					
				} else if (arr.get(j) == max) {
					
					maxFreqs.put(i, maxFreqs.get(i) + 1);					
				}
			}			
		}	
		
		List<Integer> maxFreqsVals = new ArrayList<Integer>(maxFreqs.values());
		return maxFreqsVals;
	}

}