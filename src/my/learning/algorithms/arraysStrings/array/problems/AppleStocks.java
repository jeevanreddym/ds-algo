package my.learning.algorithms.arraysStrings.array.problems;

public class AppleStocks {

	public static void main(String[] args) {
		
		int[] stockPrices = {10, 7, 5, 8, 11, 9};
		
		int maxProfit = getMaxProfit(stockPrices);
		System.out.println(maxProfit);
	}

	private static int getMaxProfit(int[] stockPrices) {
		
		int maxProfit = Integer.MIN_VALUE;
		
		int minBuy = stockPrices[0];
				
		for (int i = 1; i < stockPrices.length; i++) {
			
			int currProfit = stockPrices[i] - minBuy;
			
			maxProfit = Integer.max(currProfit, maxProfit);
			
			minBuy = Integer.min(stockPrices[i], minBuy); // checking min buy after calculating the current profit helps to keep sell always after buy.			
		}
		
		return maxProfit;
	}
	
}
