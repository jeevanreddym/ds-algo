package my.learning.algorithms.recursion.dynamic.programming;

public class TrappingRainWater {

	public static void main(String[] args) {
		
		int[] elevations = new int[] {0,1,0,2,1,0,3,1,0,1,2};
		
		System.out.println(getTrappedRainWater(elevations));
		
		System.out.println(getTrappedRainWaterOptimal(elevations));		
	}
	
	/**
	 * 	O(n^2)
	 */
	private static int getTrappedRainWater(int[] elevations) {
		
		int totalWater = 0;
				
		for (int i=0; i < elevations.length; i++) {
			
			int maxLt = 0;
			int maxRt = 0;
			
			// find max left.
			for (int j = i - 1; j >= 0; j--) {
				if (elevations[j] > maxLt) {
					maxLt = elevations[j];
				}
			}
			
			// find max right.
			for (int j = i + 1; j < elevations.length; j++) {
				if (elevations[j] > maxRt) {
					maxRt = elevations[j];
				}
			}
			
			
			
			// water above curr elevation = min(maxLt, maxRt) - curr elevation
			
			int currContainerHeight = Integer.min(maxLt, maxRt);
			
			int currAmtOfWaterAtElevation = currContainerHeight - elevations[i];			
			
			currAmtOfWaterAtElevation = Integer.max(currAmtOfWaterAtElevation, 0); // don't consider -ve values.
			
			totalWater += currAmtOfWaterAtElevation;
		}
		
		return totalWater;
	}
	
	/**
	 * 	O(n)
	 */
	private static int getTrappedRainWaterOptimal(int[] elevations) {
		
		// yet to implement.
		
		return 0;
	}
	
}
