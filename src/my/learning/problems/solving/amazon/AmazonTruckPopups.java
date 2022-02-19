package my.learning.problems.solving.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 
 * 	amazon plans to open treasure truck pop-ups at x. x is represented by m*n blocks. each block is a park area rep by 1 or commercial area by 0. 
 * 	adjacent blocks with value 1 are considered as contigous park. 
 * 	Diagonal blocks with 1 are not part of same contiguity. they plan to have truck pop in each contigous park. 
 * 	write an algo to find the no of treasure truck pop-up they can open.
 * 
 */
public class AmazonTruckPopups {

	
	
	public static void main(String[] args) {
				
		List<List<Integer>> optimalRoutes = new ArrayList<>();
//		optimalRoutes.add(Arrays.asList(1,1,0,0));
//		optimalRoutes.add(Arrays.asList(0,0,0,0));
//		optimalRoutes.add(Arrays.asList(0,0,1,1));
//		optimalRoutes.add(Arrays.asList(0,0,0,0));
		
		
		optimalRoutes.add(Arrays.asList(1,0,0,0,0,0,1,1));
		optimalRoutes.add(Arrays.asList(0,1,0,0,0,0,0,0));
		optimalRoutes.add(Arrays.asList(0,0,1,0,1,1,1,1));
		optimalRoutes.add(Arrays.asList(1,0,0,1,1,0,0,1));
		optimalRoutes.add(Arrays.asList(0,0,0,0,1,0,0,0));
		optimalRoutes.add(Arrays.asList(0,0,0,0,0,1,0,1));
		optimalRoutes.add(Arrays.asList(1,1,0,0,0,0,1,1));
		
		
		System.out.println(numberAmazonTreasureTrucks(7,8, optimalRoutes));		
	}
	
	static int numberOfTrucks = 0;

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	private static int numberAmazonTreasureTrucks(int rows, int columns, List<List<Integer>> grid) {
		numberOfTrucks = 0;

		boolean[][] visited = new boolean[rows][columns];

		dfs(0, 0, grid, visited);

		return numberOfTrucks;
	}
	// METHOD SIGNATURE ENDS

	private static void dfs(int row, int column, List<List<Integer>> grid, boolean[][] visited) {
		
		if (row < 0 || row > grid.size() - 1
				|| column < 0 || column > grid.get(0).size() - 1
				|| visited[row][column]) {
			return;
		}

		visited[row][column] = true;

		if (grid.get(row).get(column) == 1) {

			if (row - 1 == -1 || (grid.get(row - 1).get(column) == 1 && !visited[row - 1][column]) || (grid.get(row - 1).get(column) == 0)) {
				if (row + 1 == grid.size() || (grid.get(row + 1).get(column) == 1 && !visited[row + 1][column]) || (grid.get(row + 1).get(column) == 0)) {

					if (column - 1 == -1 || (grid.get(row).get(column - 1) == 1 && !visited[row][column - 1]) || (grid.get(row).get(column - 1) == 0)) {
						if (column + 1 == grid.get(0).size() || (grid.get(row).get(column + 1) == 1 && !visited[row][column + 1]) || (grid.get(row).get(column + 1) == 0)) {
							numberOfTrucks++;
						}
					}
				}
			}
		}

		dfs(row + 1, column, grid, visited);
		dfs(row, column + 1, grid, visited);
		dfs(row - 1, column, grid, visited);
		dfs(row, column - 1, grid, visited);
	}

}
