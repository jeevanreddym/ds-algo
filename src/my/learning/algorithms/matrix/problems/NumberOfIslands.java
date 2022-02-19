package my.learning.algorithms.matrix.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 	Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

	An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	You may assume all four edges of the grid are all surrounded by water.
	
	
	@NumberOfBattleships - same type of problem.
	
 */
public class NumberOfIslands {

	public static void main(String[] args) {
		
		int[][] grid1 = {
			{1,1,1,1,0},
			{1,1,0,1,0},
			{1,1,0,0,1},
			{0,0,0,1,1},
		};
		
		int[][] grid2 = {
			{0,1,0,1,0},
			{1,0,1,0,1},
			{0,1,1,1,0},
			{1,0,1,0,1},
		};
		
		System.out.println("# of islands bfs: " + findNumberOfIslands(grid1));
		System.out.println("# of islands bfs: " + findNumberOfIslands(grid2));
		
		
		
		int[][] grid3 = {
			{1,1,1,1,0},
			{1,1,0,1,0},
			{1,1,0,0,1},
			{0,0,0,1,1},
		};
		
		int[][] grid4 = {
			{0,1,0,1,0},
			{1,0,1,0,1},
			{0,1,1,1,0},
			{1,0,1,0,1},
		};
		
		System.out.println("# of islands dfs: " + findNumberOfIslandsDFS(grid3));
		System.out.println("# of islands dfs: " + findNumberOfIslandsDFS(grid4));
	}


	
	
	
	/**
	 * 	dfs solution, time: (n -> sequential traversal + n -> bfs = 2n ~ n) , space: O(rowCnt * colCnt).
	 */
	public static int findNumberOfIslandsDFS(int[][] grid) {
		
		int noOfIslands = 0;
		
		/**
		 * 	first start of doing a sequential traversal to each cell to find the start of an island (cell with val = 1)
		 * 	once found then we can do a dfs/bfs from that cell to all the adjacent cells to find the perimeter of the island.
		 * 	So all the 1's that r adjacent form 1 island & we increment our count for this island.
		 * 
		 * 	Next continue the sequential traversal to find the first 1 of the next island which was not connected to the previous island.
		 */
		
		for (int row = 0; row < grid.length; row++) {
			
			for (int col = 0; col < grid[0].length; col++) {
				
				if (grid[row][col] == 1) { // found an island. now chk all the 1's adjacent to it using bfs.
					
					noOfIslands++; // increment island count.
					
					dfs(grid, row, col); //  go chk all the adjacent 1's for this island & mark them accordingly. 
				}
			}
		}
		
		return noOfIslands;
	}
	
	/**
	 * 	dfs().
	 */
	private static void dfs(int[][] grid, int row, int col) {
	
		final int[][] directions = {
			{-1, 0}, // up.
			{0, +1}, // rt.
			{+1, 0}, // down.
			{0, -1}, // left.
		};
		
		if (row >= 0 && row < grid.length && col >=0 && col < grid[0].length && grid[row][col] == 1) {
			
			grid[row][col] = 0; // marking the cell as visited by making the val as 0, we could have also used a boolean[][] array to keep track.
			
			for (int[] direction: directions) {
				
				int nextRow = row + direction[0];
				int nextCol = col + direction[1];
				
				dfs(grid, nextRow, nextCol);
			}
		}
	}	
	
	
	
	
	
	
	
	
	/**
	 * 	bfs solution, time: (n -> sequential traversal + n -> bfs = 2n ~ n) , space: O(min(rowCnt, colCnt)).
	 */
	public static int findNumberOfIslands(int[][] grid) {
		
		int noOfIslands = 0;
		
		
		int[][] directions = {
			{-1, 0}, // up.
			{0, +1}, // rt.
			{+1, 0}, // down.
			{0, -1}, // left.
		};
		
		Queue<Integer[]> q = new LinkedList<>();
		
		// sequential traversal to check each element.
		for (int row = 0; row < grid.length; row++) {
			
			for (int col = 0; col < grid[0].length; col++) {
				
				if (grid[row][col] == 1) { // found an island. now chk all the 1's adjacent to it using bfs.
					
					noOfIslands++; // increment island count.
					
					q.offer(new Integer[] {row, col}); // start of island coordinates.
					
					// from this coordinate do a bfs to find all the adjacent coordinates which are part of this same island & mark those coordinates as 0.
					while (!q.isEmpty()) {
						
						Integer[] coordinates = q.poll(); // itself & all adjacent 1's.
						int currRow = coordinates[0];
						int currCol = coordinates[1];
						
						grid[currRow][currCol] = 0; // marking the cell as visited by making the val as 0, we could have also used a boolean[][] array to keep track.
						
						
						
						for (int[] direction: directions) {
							
							int nextRow = currRow + direction[0];
							int nextCol = currCol + direction[1];
							
							/**
							 * 	check if coordinate fits in the grid & is part of the current island.
							 */
							if (nextRow >= 0 && nextRow < grid.length && nextCol >=0 && nextCol < grid[0].length && grid[nextRow][nextCol] == 1) {
								
								q.offer(new Integer[] {nextRow, nextCol}); // push it on to Q to be processed.
							}	
						}												
					}					
				}				
			}			
		}
		
		return noOfIslands;
	}
	
}
