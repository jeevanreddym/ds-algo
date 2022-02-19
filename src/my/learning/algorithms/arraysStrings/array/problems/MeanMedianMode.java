package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;




public class MeanMedianMode {
	
    
    private static double mean(int[] arr) {
        int sum = 0;
        for (int i=0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (double) sum / arr.length;
    }    
    
    private static double median(int[] arr) {
        Arrays.sort(arr);
        int mid = arr.length/2;
        if (arr.length % 2 == 0) {
            return (double) (arr[mid] + arr[mid + 1])/2;
        }
        return (double) arr[mid];
    }
    
    private static int mode(int[] arr) {
    	
        Map<Integer,Integer> freqs = new HashMap<>();
        
        int maxfreq = -1;
        int maxfreqNum = -1;
        
        for (int i=0; i<arr.length; i++) {
        	
        	int currMaxFreq = freqs.getOrDefault(arr[i], 0) + 1; 
        	
        	if (currMaxFreq > maxfreq) {
        		maxfreq = currMaxFreq;
        		maxfreqNum = arr[i];
        	}
        	
            freqs.put(arr[i], currMaxFreq);
        }
        
        System.out.println(maxfreqNum);
        
        return maxfreq;
    }    

	public static void main(String[] args) {
		
		int[] arr = {2,3,2,5,6,5,4};		
		System.out.format("%.1f\n", mean(arr));
		
		arr = new int[] {2,3,2,5,6,5,4};		
		System.out.format("%.1f\n", median(arr));
		
		arr = new int[] {2,3,2,5,6,5,4};
		System.out.println(mode(arr));		
	}
}
