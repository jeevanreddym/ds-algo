package my.learning.algorithms.recursion.dynamic.programming;

import java.util.Arrays;

/**
 * 
 * 	There are n houses build in a line, each of which contains some value in it. 
 * 	A thief is going to steal the maximal value of these houses, but he can’t steal in two adjacent houses 
 * 	because the owner of the stolen houses will tell his two neighbour's left and right side. 
 * 	What is the maximum stolen value?
 * 
 */
public class HouseThief {
	
	public static void main(String[] args) {
		
		int[] houseValues = {3,1,2,5,4,2}; // value of items in each house.
		
		// recusrion.
		System.out.println("Maximum loot value: " + stealFromHouse(houseValues, 0));
		
		// memoization.
		System.out.println("Maximum loot value: " + stealFromHouseMemo(houseValues));
		
		// dynamic programming.
		System.out.println("Maximum loot value: " + stealFromHouseDP(houseValues));
	}
		
	/**
	 * 	Recursive - brute force. 
	 */
	private static int stealFromHouse(int[] houseValues, int currHouseIndex) {
		
		if (currHouseIndex >= houseValues.length) {
			return 0;
		}
				
		int valueFromThisHouseAndNextToNeighbour = houseValues[currHouseIndex] + stealFromHouse(houseValues, currHouseIndex + 2);
		
		int valueFromNextNeighbour = stealFromHouse(houseValues, currHouseIndex + 1);
		
		return Math.max(valueFromThisHouseAndNextToNeighbour, valueFromNextNeighbour);
	}
	
	private static int stealFromHouseMemo(int[] houseValues, int currHouseIndex, int[] dp) {
		
		if (currHouseIndex >= houseValues.length) {
			return 0;
		}
		
		if (dp[currHouseIndex] == -1) {
			
			int valueFromThisHouseAndNextToNeighbour = houseValues[currHouseIndex] + stealFromHouseMemo(houseValues, currHouseIndex + 2, dp);
			
			int valueFromNextNeighbour = stealFromHouseMemo(houseValues, currHouseIndex + 1, dp);
			
			dp[currHouseIndex] = Math.max(valueFromThisHouseAndNextToNeighbour, valueFromNextNeighbour);
		}
		
		return dp[currHouseIndex];
	}
	
	/**
	 * 	Memoization. 
	 */
	private static int stealFromHouseMemo(int[] houseValues) {
		int[] dp = new int[houseValues.length];
		Arrays.fill(dp, -1);
		return stealFromHouseMemo(houseValues, 0, dp);
	}
	
	
	/**
	 * 	Dynamic programming. 
	 */
	private static int stealFromHouseDP(int[] houseValues) {
		
		int[] cache = new int[houseValues.length];
		
		cache[0] = houseValues[0];
		cache[1] = Integer.max(houseValues[0], houseValues[1]);
		
		for (int i = 2; i < houseValues.length; i++) {
			
			int valueFromThisHouseAndNextToNeighbour = houseValues[i] + cache[i - 2];
			
			int valueFromNextNeighbour = cache[i - 1];
			
			cache[i] = Math.max(valueFromThisHouseAndNextToNeighbour, valueFromNextNeighbour);			
		}
		
		return cache[cache.length - 1];
	}
	
}
