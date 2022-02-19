package my.learning.problems.solving.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Amazon Prime Air is developing a system that divides shipping routes using flight optimization 
routing systems to a cluster of aircraft that can 
fulfill these routes. Each shipping route is identified by a unique integer identifier, requires a 
fixed non-zero amount of travel distance 
between airports, and is defined to be either a forward shipping route or a return shipping route. 
Identifiers are guaranteed to be unique 
within their own route type, but not across route types.

Each aircraft should be assigned two shipping routes at once: one forward route and one return route. 
Due to the complex scheduling of flight plans,
all aircraft have a fixed maximum operating travel distance, and cannot be scheduled to fly a shipping 
route that requires more travel distance than 
the prescribed maximum operating travel distance. The goal of the system is to optimize the total 
operating travel distance of a given aircraft. 
A forward/return shipping route pair is considered to be “optimal” if there does not exist another 
pair that has a higher operating travel distance
 than this pair, and also has a total less than or equal to the maximum operating travel distance of 
the aircraft.

For example, if the aircraft has a maximum operating travel distance of 3000 miles, a forward/return 
shipping route pair using a total of 2900 
miles would be optimal if there does not exist a pair that uses a total operating travel distance of 3000 miles, 
but would not be considered 
optimal if such a pair did exist.

Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow 
the aircraft to be optimally utilized, given a 
list of forward shipping routes and a list of return shipping routes.

Input

The input to the function/method consists of three arguments:
maxTravelDist: an integer representing the maximum operating travel distance of the given aircraft
forwardRouteList: a list of pairs of integers where the first integer represents the unique identifier 
of a forward shipping route and the second integer
 represents the amount of travel distance required by this shipping route
returnRouteList: a list of pairs of integers where the first integer represents the unique identifier 
of a return shipping route and the second 
integer represents the amount of travel distance required by this shipping route.

Output

Return a list of pairs of integers representing the pairs of IDs of forward and return shipping routes 
that optimally utilize the given aircraft.
If no route is possible, return an empty list.

Example :

Input:
maxTravelDist = 7000
forwardRouteList = [[1,2000],[2,4000],[3,6000]]
returnRouteList = [[1,2000]]

Output:
[[2,1]]

Explanation:

There are only three combinations, [1,1], [2,1], and [3,1], which have a total of 4000, 6000, and 8000 
miles, respectively. Since 6000 is the 
largest use that does not exceed 7000, [2,1] is the only optimal pair.

Example 2:

Input:

maxTravelDist = 10000
forwardRouteList = [[1, 3000], [2, 5000], [3, 7000], [4, 10000]]
returnRouteList = [[1, 2000], [2, 3000], [3, 4000], [4, 5000]]

Output:

[[2, 4], [3, 2]]

Explanation:

There are two pairs of forward and return shipping routes possible that optimally utilizes the given aircraft.

Shipping Route ID#2 from the forwardShippingRouteList requires 5000 miles travelled, and Shipping Route 
ID#4 from returnShippingRouteList also 
requires 5000 miles travelled. Combined, they add up to 10000 miles travelled.

Similarly, Shipping Route ID#3 from forwardShippingRouteList requires 7000 miles travelled, and 
Shipping Route ID#2 from returnShippingRouteList
requires 3000 miles travelled. These also add up to 10000 miles travelled.

Therefore, the pairs of forward and return shipping routes that optimally utilize the 
aircraft are [2, 4] and [3, 2].

 *
 */


public class AmazonPrimeAirShippingRoutes {

	public static void main(String[] args) {
				
		List<Integer[]> optimalRoutes = findOptimalShippingRoutes(10000, 
				Arrays.asList(new Integer[]{1, 3000}, new Integer[]{2, 5000}, new Integer[]{3, 7000}, new Integer[]{4, 10000}),
				Arrays.asList(new Integer[]{1, 2000}, new Integer[]{2, 3000}, new Integer[]{3, 4000}, new Integer[]{4, 5000}));
		if (optimalRoutes != null) {
			for (Integer[] optimalRoute: optimalRoutes) {
				System.out.println(String.format("[%s,%s]", optimalRoute[0], optimalRoute[1]));
			}
		}	
				
		
		// sample input
		System.out.println(calculateOptimalRoute(10000,
				Arrays.asList(Arrays.asList(1, 3000), Arrays.asList(2, 5000), Arrays.asList(3, 7000), Arrays.asList(4, 10000)),
				Arrays.asList(Arrays.asList(1, 2000), Arrays.asList(2, 9000), Arrays.asList(3, 5000))));
		
		
		
		
		int[] foregroundTasks = {1, 7, 2, 4, 5, 6};
		int[] backgroundTasks = {3, 1, 2};
		int k = 6;
		
		List<List<Integer>> maxMemoryUsagePairs = calculateOptimalRoute(k, foregroundTasks, backgroundTasks);
		System.out.println(maxMemoryUsagePairs);		
	}

	
	/**
	 * 	O(nlog(n)) time complexity.
	 */
	private static List<List<Integer>> calculateOptimalRoute(final int maxTravelDist, final int[] foregroundTasks, final int[] backgroundTasks) {
					    
	    List<List<Integer>> optimalRoutes = new LinkedList<List<Integer>>();
	    	    
	    /**
	     * 	Converting 1-D array to 2-D to keep track of the original array index.
	     */
	    List<List<Integer>> foregroundTasksList = new ArrayList<>(foregroundTasks.length);
	    for (int i=0; i < foregroundTasks.length; i++) {
	    	foregroundTasksList.add(Arrays.asList(i, foregroundTasks[i]));
	    }
	    
	    List<List<Integer>> backgroundTasksList = new ArrayList<>(backgroundTasks.length);
	    for (int i=0; i < backgroundTasks.length; i++) {
	    	backgroundTasksList.add(Arrays.asList(i, backgroundTasks[i]));
	    }
	    
	    /**
	     * 	Sorting the 2 arrays.
	     */
	    foregroundTasksList.sort((l1,l2) -> Integer.compare(l1.get(1), l2.get(1)));
	    backgroundTasksList.sort((l1,l2) -> Integer.compare(l1.get(1), l2.get(1)));
	    	       
	    Integer max = 0;
	    int i = 0;
	    int j = backgroundTasks.length - 1;
		
		while (i < foregroundTasksList.size() && j >= 0) {
			int currSum = foregroundTasksList.get(i).get(1) + backgroundTasksList.get(j).get(1);
			if ( currSum <= maxTravelDist && currSum >= max) {
				max = currSum;				
				optimalRoutes.add(Arrays.asList(foregroundTasksList.get(i).get(0), backgroundTasksList.get(j).get(0), currSum));
				i++;
			} else {
				j--;
			}
		}				
		
		final int[] maxArr = {max};
		return optimalRoutes.stream().filter(list -> list.get(2) == maxArr[0]).collect(Collectors.toList());
	}
	
	
	/**
	 * 	O(nlog(n)) time complexity.
	 */
	public static List<List<Integer>> calculateOptimalRoute(
			final int maxTravelDist,
			final List<List<Integer>> forwardList, 
			final List<List<Integer>> returnList) {
				
		System.out.println(forwardList);
	    System.out.println(returnList);
	    
	    List<List<Integer>> result = new LinkedList<List<Integer>>();
	    
	    forwardList.sort((a1,a2) -> Integer.compare(a1.get(1), a2.get(1)));
	    returnList.sort((a1,a2) -> Integer.compare(a1.get(1), a2.get(1)));
	   
	    
	    int max = 0;
	    int i = 0;
	    int j = returnList.size() - 1;
		
		while (i < forwardList.size() && j >= 0) {
			
			int currSum = forwardList.get(i).get(1) + returnList.get(j).get(1);
			
			if (currSum <= maxTravelDist && currSum >= max) {
				
				max = currSum;
				
				result.add(Arrays.asList(forwardList.get(i).get(0), returnList.get(j).get(0)));
				
				i++;
				
			} else {
				
				j--;
				
			}
		}
		return result;
	}
	
	
	/**
	 * 	time complexity: O(n^2)
	 */
	public static List<Integer[]> findOptimalShippingRoutes(
			int maxTravelDist,
			List<Integer[]> forwardRouteList,
			List<Integer[]> returnRouteList) {	
		
		List<Integer[]> result = new LinkedList<>(); 
		
		int max = 0;
		
		for (Integer[] forwardRoute: forwardRouteList) {
			
			Integer[] optimalRoute = new Integer[] {0, 0, 0};	
			
			for (Integer[] returnRoute: returnRouteList) {
				
				int currSum = forwardRoute[1] + returnRoute[1];
				
				if (currSum <= maxTravelDist && currSum > max) {
					
					max = currSum;
					
					optimalRoute[0] = currSum;
					optimalRoute[1] = forwardRoute[0];
					optimalRoute[2] = returnRoute[0];
					
					result.add(optimalRoute);
				}				
			}
		}	
		
		final int[] maxArr = {max};
		return result.stream().filter(intArr -> intArr[0] == maxArr[0]).collect(Collectors.toList());
	}	

}
