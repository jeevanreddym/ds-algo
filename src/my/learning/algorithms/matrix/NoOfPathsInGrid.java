package my.learning.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 	Return # of paths that are available from start vertex(0,0) to end vertex(n-1,n-1).
 * 	u can only move down or right.
 */
public class NoOfPathsInGrid {

	public static void main(String[] args) {
		
		int[][] grid = {
			{1,1,1,1},
			{1,0,1,1},
			{1,1,0,1},
			{0,1,0,1}
		};
		
		int noOfPaths = noOfPaths(grid);
		System.out.println("# of paths: " + noOfPaths);
	}
	
	private static int noOfPaths(int[][] grid) {
		
		int[][] cache = new int[grid.length][grid[0].length];
		Arrays.stream(cache).forEach(row -> Arrays.fill(row, -1));
		
		List<List<String>> paths = new LinkedList<>();
		
		int noOfPaths = noOfPaths(grid, 0, 0, cache, new LinkedList<>(), paths);
		
		paths.forEach(path -> System.out.println(Arrays.toString(path.toArray())));
		
		return noOfPaths;
		//return noOfPaths(grid, 0, 0);
	}
	
	private static int noOfPaths(int[][] grid, int row, int col, int[][] cache, List<String> partial, List<List<String>> paths) {
		
		if (isInValidCell(grid, row, col)) {
			return 0;
		}
		
		if (row == grid.length - 1 && col == grid[0].length -1) {
			
			partial.add(row + "-" + col);
			paths.add(new ArrayList<>(partial));
			partial.remove(partial.size() - 1);
			
			return 1;
		}
		
		
		
				
		if (row < grid.length - 1 && col < grid[0].length - 1) {
			
			partial.add(row + "-" + col);
			int downPathSum = noOfPaths(grid, row + 1, col, cache, partial, paths); // down.
			//partial.remove(partial.size() - 1);
			
			//partial.add(row + "-" + col);
			int rtPathSum = noOfPaths(grid, row, col + 1, cache, partial, paths); // right.
			partial.remove(partial.size() - 1);			
			
			return downPathSum + rtPathSum;
		}
			
		if (row < grid.length - 1) {
			
			partial.add(row + "-" + col);
			int downPathSum = noOfPaths(grid, row + 1, col, cache, partial, paths); // down.
			partial.remove(partial.size() - 1);
			
			return downPathSum;
		}
		
		if (col < grid[0].length - 1) {
			
			partial.add(row + "-" + col);
			int rtPathSum = noOfPaths(grid, row, col + 1, cache, partial, paths); // right.
			partial.remove(partial.size() - 1);	
			
			return rtPathSum;
		}
		
		return 0;
	}

	
	
	private static boolean isInValidCell(int[][] grid, int row, int col) {
		return (row == grid.length || col == grid[0].length || grid[row][col] == 0);
	}
	
	private static boolean isEnd(int[][] grid, int row, int col) {
		return (row == grid.length - 1 && col == grid[0].length - 1);
	}	
	
	private static int noOfPaths(int[][] grid, int row, int col) {		
		
		if (isInValidCell(grid, row, col)) {
			return 0;
		} else if (isEnd(grid, row, col)) {
			return 1;
		} 
		
		int currNoOfPaths = 0;
		
		currNoOfPaths += noOfPaths(grid, row, col + 1); // right.
		
		currNoOfPaths += noOfPaths(grid, row + 1, col); // down.
		
		return currNoOfPaths;
	}
	

}
