package my.learning.algorithms.arraysStrings.string.problems;




/**
 * 	Solve using sliding window technique.
 * 
 * 	keep left & right pointers starting at index 0, move right ptr forward until a window satisfies the pattern, 
 * 	chk if thats the smallest possible window.
 * 	try to move the left pointer forward now & see if the new window still satisfies the pattern. 
 * 	If not continue moving the right ptr forward until the window satisfies the pattern.
 * 
 * 	@MaxContiguousSubarraySum
 */
public class MinimumWindowSubstring {
	
	static final int no_of_chars = 256; // unicode character set.
	
	
	public static void main(String[] args) {
		
		String str = "floateeryaaahealeyshaereaaa";
		String pattern = "eerh";
		
		// store occurrence of characters of pattern.
		int[] hashPattern = new int[no_of_chars];	
		for (Character ch: pattern.toCharArray()) {
			hashPattern[ch]++; 
		}
		
		
		int minSubstrLen = Integer.MAX_VALUE;
		String minLenSubstr = "";
		
		
		int matchedCnt = 0; // count of matched characters.
		
		// start traversing the string.
		int[] hashStr = new int[no_of_chars]; 
		for (int lt = 0, rt = 0; rt < str.length(); rt++) {
			
			Character rtChar = str.charAt(rt);
			
			hashStr[rtChar]++;
			
			if (hashPattern[rtChar] != 0) {
				matchedCnt++;
			}
			
			System.out.println(String.format("%s -> p(%s),s(%s)", rtChar, hashPattern[rtChar], hashStr[rtChar]));
			
			
			
			if (matchedCnt == pattern.length()) { // found match. 
								
				while (matchedCnt == pattern.length()) { 
				
					Character ltChar = str.charAt(lt);
					
					int matchedSubstrLen = rt - lt + 1;
					
					if (matchedSubstrLen < minSubstrLen) {
						
						minSubstrLen = matchedSubstrLen;
						
						minLenSubstr = str.substring(lt, rt + 1);
					}
					
					
                    if (hashPattern[ltChar] > 0) {
                    	hashStr[ltChar]--;
                    	matchedCnt--;
                    }                    	 
                    
                    lt++;
                }				
			}			
		}
		
		System.out.println(String.format("%s(%s)", minLenSubstr, minSubstrLen));
	}
	
	
	// similar solution.
	static String minWindow(String str, String pattern) {
		
		
		// store occurrence of characters of pattern.
		int[] hashPattern = new int[no_of_chars];		
        for (int i = 0; i < pattern.length(); i++) {
        	hashPattern[pattern.charAt(i)]++;
        }
        
        
        int[] hashStr = new int[no_of_chars]; 
        
        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;
        
        // start traversing the string 
        int count = 0; // count of characters 
        for (int j = 0; j < str.length() ; j++) {
        	
            // count occurrence of characters of string 
            hashStr[str.charAt(j)]++;
            
            
            // If string's char matches with pattern's char then increment count 
            if (hashPattern[str.charAt(j)] != 0 && hashStr[str.charAt(j)] <= hashPattern[str.charAt(j)]) 
                count++; 
            
            
            
            // if all the characters are matched 
            if (count == pattern.length()) {
            	
            	// Try to minimize the window i.e., 
            	// check if any character is occurring more no of times than its occurrence in pattern, 
            	// if yes then remove it from starting and also remove the useless characters.
            	
                while (hashStr[str.charAt(start)] > hashPattern[str.charAt(start)] || hashPattern[str.charAt(start)] == 0) { 
      
                    if (hashStr[str.charAt(start)] > hashPattern[str.charAt(start)]) {
                    	hashStr[str.charAt(start)]--;
                    }                    	 
                    
                    start++; 
                } 
            	
                
                // update window size 
                int len_window = j - start + 1; 
                if (min_len > len_window) { 
                    min_len = len_window; 
                    start_index = start; 
                }
            }
        }
        
		
        // If no window found 
        if (start_index == -1) { 
        	System.out.println("No such window exists"); 
        	return ""; 
        } 
      
        // Return substring starting from start_index and length min_len 
        return str.substring(start_index, start_index + min_len);
	}
	
	
	

	static int findMinSubArray(int[] arr, int targetSum) {
		
		int windowSum = 0, minLength = Integer.MAX_VALUE;
		
		int windowStart = 0;
		
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
		
			windowSum += arr[windowEnd]; // add the next element
			
			// shrink the window as small as possible until the 'windowSum' is smaller than 'S'
			while (windowSum >= targetSum) {
			
				int currWindowLen = windowEnd - windowStart + 1;
				
				minLength = Math.min(currWindowLen, minLength);
				
				windowSum -= arr[windowStart]; // subtract the element going out
				
				windowStart++; // shrink the window from left.
			}
		}
		
		return minLength == Integer.MAX_VALUE? 0 : minLength;
	}	
	
}
