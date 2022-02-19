package my.learning.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinCostPathInGrid {

	public static void main(String[] args) {
		
		int[][] grid = {
			{1,3,5},
			{2,1,2},
			{4,3,1}
		};		
		int minCost = findMinCostPath(grid);
		System.out.println("min cost: " + minCost);
	}
	
	static int findMinCostPath(int[][] grid) {	
		List<List<Integer>> allPaths = new LinkedList<>();
		int minCost = findMinCostPath(grid, 0, 0, 0, new LinkedList<>(), allPaths);
		allPaths.forEach(path -> System.out.println(Arrays.toString(path.toArray())));
		return minCost;
	}
	
	static int findMinCostPath(int[][] grid, int row, int col, int pathSum, List<Integer> partial, List<List<Integer>> allPaths) {
		
//		if (row == grid.length || col == grid[0].length) {
//			return pathSum;
//		}
		
		if (row == grid.length - 1 && col == grid[0].length -1) {
			
			partial.add(grid[row][col]);
			allPaths.add(new ArrayList<>(partial));
			partial.remove(partial.size() - 1);
			
			return pathSum + grid[row][col];
		}
				
		if (row < grid.length - 1 && col < grid[0].length - 1) {
			
			partial.add(grid[row][col]);
			int downPathSum = findMinCostPath(grid, row + 1, col, pathSum + grid[row][col], partial, allPaths); // down.
			partial.remove(partial.size() - 1);
			
			partial.add(grid[row][col]);
			int rtPathSum = findMinCostPath(grid, row, col + 1, pathSum + grid[row][col], partial, allPaths); // right.
			partial.remove(partial.size() - 1);			
			
			return Integer.min(downPathSum, rtPathSum);
		}
			
		if (row < grid.length - 1) {
			
			partial.add(grid[row][col]);
			int downPathSum = findMinCostPath(grid, row + 1, col, pathSum + grid[row][col], partial, allPaths); // down.
			partial.remove(partial.size() - 1);
			
			return downPathSum;
		}
		
		if (col < grid[0].length - 1) {
			
			partial.add(grid[row][col]);
			int rtPathSum = findMinCostPath(grid, row, col + 1, pathSum + grid[row][col], partial, allPaths); // right.
			partial.remove(partial.size() - 1);	
			
			return rtPathSum;
		}
		
		return 0;
	}
	
}
