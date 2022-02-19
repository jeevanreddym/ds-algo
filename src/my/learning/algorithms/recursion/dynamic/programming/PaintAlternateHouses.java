package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 	What is the min cost to paint all houses with given criteria (no 2 adjacent houses have the same color paint).
 *	Given array of costs to paint each house with a specific color.
 */
public class PaintAlternateHouses {

	public static void main(String[] args) {
		
		int[][] cost = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
		
		System.out.println(minCost(cost));		
		System.out.println(minCostMemo(cost));	
	}
	
	/**
	 * 	Recursive.
	 */
	private static int minCost(int[][] cost) {
		
		int costRed = minCost(cost, 0, Color.RED);
		int costBlue = minCost(cost, 0, Color.BLUE);
		int costGreen = minCost(cost, 0, Color.GREEN);
		
		return Integer.min(costRed, Integer.min(costBlue, costGreen));
	}
	
	private static int minCost(int[][] cost, int i, Color color) {
		
		if (i == cost.length) {
			return 0;
		}
		
		switch (color) {
		
			case RED: {
				
				int costBlue = minCost(cost, i + 1, Color.BLUE);
				int costGreen = minCost(cost, i + 1, Color.GREEN);
				
				return cost[i][color.getVal()] + Integer.min(costBlue, costGreen);
			}
				
			case GREEN: {
				
				int costRed = minCost(cost, i + 1, Color.RED);
				int costBlue = minCost(cost, i + 1, Color.BLUE);
				
				return cost[i][color.getVal()] + Integer.min(costRed, costBlue);
			}				
				
			case BLUE: {
				
				int costRed = minCost(cost, i + 1, Color.RED);
				int costGreen = minCost(cost, i + 1, Color.GREEN);
				
				return cost[i][color.getVal()] + Integer.min(costRed, costGreen);
			}	
				
			default:
				break;
		}		
		return 0;
	}
	
	
	/**
	 * 	Memoized solution.
	 */
	private static int minCostMemo(int[][] cost) {
		
		int[][] cache = new int[cost.length][cost[0].length];
		Arrays.stream(cache).forEach(row -> Arrays.fill(row, -1));
		
		int costRed = minCostMemo(cost, 0, Color.RED, cache);
		int costBlue = minCostMemo(cost, 0, Color.BLUE, cache);
		int costGreen = minCostMemo(cost, 0, Color.GREEN, cache);
		
		return Integer.min(costRed, Integer.min(costBlue, costGreen));
	}
	
	private static int minCostMemo(int[][] cost, int i, Color color, int[][] cache) {
		
		if (i == cost.length) {
			return 0;
		}
		
		if (cache[i][color.getVal()] != -1) {
			return cache[i][color.getVal()];
		}
		
		switch (color) {
			case RED: {				
				int costBlue = minCostMemo(cost, i + 1, Color.BLUE, cache);
				int costGreen = minCostMemo(cost, i + 1, Color.GREEN, cache);				
				return cost[i][color.getVal()] + Integer.min(costBlue, costGreen);
			}	
			case GREEN: {				
				int costRed = minCostMemo(cost, i + 1, Color.RED, cache);
				int costBlue = minCostMemo(cost, i + 1, Color.BLUE, cache);				
				return cost[i][color.getVal()] + Integer.min(costRed, costBlue);
			}	
			case BLUE: {				
				int costRed = minCostMemo(cost, i + 1, Color.RED, cache);
				int costGreen = minCostMemo(cost, i + 1, Color.GREEN, cache);				
				return cost[i][color.getVal()] + Integer.min(costRed, costGreen);
			}
		}		
		return 0;
	}
	
	static enum Color {
		
		RED(0),
		GREEN(1),
		BLUE(2);	
		
		int val;
		Color(int val) {
			this.val = val;
		}		
		int getVal() {
			return this.val;
		}
	}
	
}




