package my.learning.algorithms.arraysStrings.array.problems;



/**
 * 	Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
 * 	n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). 
 * 	Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * 
 * 	Example 3:
		Input: height = [4,3,2,1,4]
		Output: 16
	Example 4:	
		Input: height = [1,2,1]
		Output: 2
 * 
 */
public class MaxWaterContainer {

	public static void main(String[] args) {
		
		int[] elevations = new int[] {7,1,2,3,9};
		
		System.out.println(getMostWaterContainerArea(elevations));
		
		System.out.println(getMostWaterContainerAreaOptimal(elevations));		
	}
	
	/**
	 * 	O(n^2)
	 */
	private static int getMostWaterContainerArea(int[] elevations) {
		
		int maxArea = 0;
		
		for (int i=0; i < elevations.length; i++) {
			
			for (int j = i + 1; j < elevations.length; j++) {
			
				int currArea = Integer.min(elevations[i], elevations[j]) * (j - i);
				
				maxArea = Integer.max(currArea, maxArea);
			}
		}		
		return maxArea;
	}
	
	/**
	 * 	O(n)
	 */
	private static int getMostWaterContainerAreaOptimal(int[] elevations) {
		
		int maxArea = 0;
		
		int l = 0, r = elevations.length - 1;
		
		while (l < r) {
			
			int height = Integer.min(elevations[l], elevations[r]);
			
			int width = (r - l);
			
			int currArea = height * width;
			
			maxArea = Integer.max(currArea, maxArea);
			
			if (elevations[l] > elevations[r]) {
				
				r--;
			
			} else {
			
				l++;				
			}
			
		}		
		return maxArea;
	}
	
}
