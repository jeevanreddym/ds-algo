package my.learning.algorithms.recursion.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RodCutting {

	public static void main(String[] args) {
		
		int rodLength = 6;
		int[] rodLengths = {1,2,3,4};
		int[] rodPiecePrices = {2,5,7,8};
		
		int maxProfit = cutRod(rodLengths, rodPiecePrices, rodLength);
		System.out.println("Max profit: " + maxProfit);
		
		maxProfit = cutRodAlternative(rodLengths, rodPiecePrices, rodLength);
		System.out.println(String.format("max profit: %s", maxProfit));
	}

	private static int cutRod(int[] rodLengths, int[] rodPiecePrices, int rodLength) {		
		List<List<Integer[]>> allCuts = new LinkedList<>();
		
		int[] lenWiseProfit = new int[rodLengths.length + 1];
		Arrays.fill(lenWiseProfit, -1);
		
		int maxProfit = cutRod(rodLengths, rodPiecePrices, rodLength, 0, new HashMap<>(), new LinkedList<>(), allCuts);
		allCuts.forEach(oneCut -> {			
			oneCut.forEach(item -> System.out.print(String.format("%s(%s), ", item[0], item[1])));
			System.out.println("");
		});
		return maxProfit;
	}
	
	
	private static int cutRod(int[] rodLengths, int[] rodPiecePrices, int availableLength, int runningPrice, Map<Integer, Integer> lenWiseProfit, 
			List<Integer[]> partial, List<List<Integer[]>> allCuts) {
		
		if (availableLength == 0) {
			allCuts.add(new ArrayList<>(partial));
			return runningPrice;
		}
		
		int maxProfitForLen = Integer.MIN_VALUE; 
		
		for (int i=0; i < rodLengths.length; i++) {
		
			int currLenOptn = rodLengths[i]; 
			int currPriceOptn = rodPiecePrices[i];
			
			if (availableLength - currLenOptn >= 0) {
							
				partial.add(new Integer[] {currLenOptn, currPriceOptn});
				
				int currProfit = 0;
//				if (lenWiseProfit.containsKey(currLenOptn)) {
//					currProfit = lenWiseProfit.get(currLenOptn);
//				} else {
					currProfit = cutRod(rodLengths, rodPiecePrices, availableLength - currLenOptn, runningPrice + currPriceOptn, lenWiseProfit, partial, allCuts);
//					lenWiseProfit.put(currLenOptn, currProfit);
//				}
				
				partial.remove(partial.size() - 1);
				
				if (currProfit > maxProfitForLen) {
					maxProfitForLen = currProfit;
				}
			}
		}
		
		return maxProfitForLen;
	}
	
	
	
	private static int cutRodAlternative(int[] rodLengths, int[] rodPiecePrices, int availableLength, int i, 
			List<Integer[]> partial, List<List<Integer[]>> allCuts) {
		
		// base case.
		if (availableLength <= 0 || i == rodLengths.length) {			
			if (availableLength == 0) {
				allCuts.add(new ArrayList<>(partial));	
			}
			return 0;
		}
				
		int itemExcludexValue = cutRodAlternative(rodLengths, rodPiecePrices, availableLength, i + 1, partial, allCuts);
		
		if (availableLength - rodLengths[i] >= 0) {
			
			partial.add(new Integer[] {rodLengths[i], rodPiecePrices[i]});
			int itemIncludedValue = rodPiecePrices[i] + cutRodAlternative(rodLengths, rodPiecePrices, availableLength - rodLengths[i], i + 1, partial, allCuts);
			partial.remove(partial.size() - 1);
			
			return Integer.max(itemIncludedValue, itemExcludexValue); // choose the option which gives better value by including/excluding this item.	
		}		
		else { // else go ahead without considering this item.	
			
			return itemExcludexValue;
		}
	}
	
	
	private static int cutRodAlternative(int[] rodLengths, int[] rodPiecePrices, int rodLength) {		
		List<List<Integer[]>> allCuts = new LinkedList<>();
		
		int[] lenWiseProfit = new int[rodLengths.length + 1];
		Arrays.fill(lenWiseProfit, -1);
		
		int maxProfit = cutRodAlternative(rodLengths, rodPiecePrices, rodLength, 0, new LinkedList<>(), allCuts);
		allCuts.forEach(oneCut -> oneCut.forEach(item -> System.out.print(String.format("%s(%s), ", item[0], item[1]))));
		return maxProfit;
	}
	
}
