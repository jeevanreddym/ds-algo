package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 	On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

	Once you pay the cost, you can either climb one or two steps. 
	You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
	
	Example 1:
	Input: cost = [10, 15, 20]
	Output: 15
	Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
	
	Example 2:
	Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
	Output: 6
	Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 *	
 *	
 */
public class ClimbingStairs {

	public static void main(String[] args) {
		
		int[] costToClimbEachStep = {10, 15, 20, 25};
		
		int minCost = minCost(costToClimbEachStep);
		System.out.println(minCost);
		
		minCost = minCostMemo(costToClimbEachStep);
		System.out.println(minCost);
	}
	
	
	/**
	 * 	Brute Force, recursive solution. time: O(2^n), space: O(n).
	 */
	private static int minCost(int[] costToClimbEachStep) {
		return minCost(costToClimbEachStep.length, costToClimbEachStep);
	}
	
	private static int minCost(int i, int[] costs) {
		
		if (i < 0) return 0;
		if (i == 0 || i == 1) return costs[i];
		
		return (i != costs.length? costs[i]: 0) + Integer.min(minCost(i - 1, costs), minCost(i - 2, costs));
	}
	
	
	/**
	 * 	Memoized. time: O(n), space: O(n).
	 */
	private static int minCostMemo(int[] costs) {
		
		int[] dp = new int[costs.length + 1];
		Arrays.fill(dp, -1);
		
		return minCostMemo(costs.length, costs, dp);	
	}
	
	private static int minCostMemo(int i, int[] costs, int[] dp) {
		
		if (i < 0) return 0;
		if (i == 0 || i == 1) return costs[i];
		
		if (dp[i] == -1) {			
			dp[i] = (i != costs.length? costs[i]: 0) + Integer.min(minCost(i - 1, costs), minCost(i - 2, costs));
		}
		
		return dp[i];
	}
	
}
