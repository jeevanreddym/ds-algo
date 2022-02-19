package my.learning.problems.solving.amazon;

import java.util.Arrays;


/**
 * 
 * Amazon Online Assessment Questions 2019 OA2 - Prioritize Orders
Amazon is planning to release a new order prioritization algorithm to optimize fulfilling Prime deliveries on time. 
All orders (Prime or otherwise) are given an alphanumeric ID code. However, Prime orders are given additional metadata that consists of a 
space delimited list of lowercase English letters, whereas non-Prime orders are given metadata that consists only Of a space delimited string Of 
positive integers. Each order is therefore defined as their alphanumeric id code, followed by a space, followed by their space delimited metadata.

You have been tasked with sorting a list of all orders in the order queue to assist in prioritization of fulfillment. They should be sorted according 
to the following order:
1. The Prime orders should be returned first, sorted by lexicographic sort of the alphabetic metadata.
2. Only in the case of ties, the identifier should be used as a backup sort.
3. The remaining non-Prime orders must all come after, in the original order they were given in the input.
Write a function or method to sort the orders according to this system.

Input:
The input to the function/method consists of two arguments:
numOrders, an integer representing the number of orders.
orderl_ist, a list Of strings representing all Of the orders.

Output: Return a list of strings representing the correctly prioritized orders.

Note The order identifier consists of only lower case English character and numbers.

Examples
Input:
numOrders = 6
orderList
[Zid 93 12]
[fp kindle book]
[IOa echo show]
[17g 12 256]
[abl kindle book]
[125 echo dot second generation]

Output:
[125 echo dot second generation]
[IOa echo show] 
[abl kindle book]
[fp kindle book]
[Zid 93 12]
[17g 12 256]

Explanation:
There are four Prime orders (lines with words) in this order list. Because "echo" comes before "kindle", those lines should come first, 
with "dot" coming before "show". Because two lines have the metadata "kindle book", their tie should be broken by the identifier, where
 "abl" comes before "fp". Finally, the non-Prime numeric orders should come last, in the original order, they were in the input.
 *
 */


public class PrioritizeOrders {

	public static void main(String[] args) {
		
		String[] orders = {
			"Zid 93 12",
			"fp kindle book",			
			"17g 12 256",
			"abl kindle book",
			"125 echo dot second generation",	
			"IOa echo show",
		};		
		
		String[] prioritized = prioritizeOrders(orders);
		for (String item: prioritized) {
			System.out.println(item);
		}
	}
	
	private static String[] prioritizeOrders(String[] orders) {	
		
		Arrays.sort(orders, (o1, o2) -> {
			
			String[] o1SplitStrs = o1.split(" ");
			String[] o2SplitStrs = o2.split(" ");
						
			if (Character.isDigit(o1SplitStrs[1].charAt(0)) && Character.isDigit(o2SplitStrs[1].charAt(0))) {
				
				return 0;				
			} 
			
			else if (Character.isDigit(o1SplitStrs[1].charAt(0)) || Character.isDigit(o2SplitStrs[1].charAt(0))) {				
			
				return !Character.isDigit(o1SplitStrs[1].charAt(0))? -1: 1;
			} 
			
			else if (!Character.isDigit(o1SplitStrs[1].charAt(0)) && !Character.isDigit(o2SplitStrs[1].charAt(0))) {
				
				int strComp = o1SplitStrs[1].compareTo(o2SplitStrs[1]);
				if (strComp != 0) {
					return strComp;
				} else {
					strComp = o1SplitStrs[2].compareTo(o2SplitStrs[2]);
					if (strComp != 0) {
						return strComp;
					} else {
						strComp = o1SplitStrs[0].compareTo(o2SplitStrs[0]);
						return strComp;
					}
				}
			}
			return 0;
		});
		
		return orders;
	}
	
}
