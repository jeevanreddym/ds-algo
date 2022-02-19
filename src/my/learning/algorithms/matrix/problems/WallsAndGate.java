package my.learning.algorithms.matrix.problems;

import java.util.Arrays;

/**
 * 	 10 -> empty cell,
 * 	-1 -> wall,
 * 	 0 -> gate.
 * 
 * 	Find # of steps required from each empty cell to reach the nearest gate.
 * 
 */
public class WallsAndGate {

	public static void main(String[] args) {
		
		int[][] grid = {
			{90,-1,0,90},
			{90,90,90,-1},
			{90,-1,90,-1},
			{0,-1,90,90},
		};
		
		
		int[][] modifiedGrid = findMinPathsFromCellsToGates(grid);
		
		Arrays.stream(modifiedGrid).forEach(row -> System.out.println(Arrays.toString(row)));
	}

	private static int[][] findMinPathsFromCellsToGates(int[][] grid) {
		
		for (int row = 0; row < grid.length; row++) {
			
			for (int col = 0; col < grid[0].length; col++) {
				
				if (grid[row][col] == 0) { // find the gate's.
					
					dfs(grid, row, col, 0); // start dfs by passing curr dist as 1.
				}				
			}			
		}
		
		return grid;
	}

	private static void dfs(int[][] grid, int row, int col, int currDist) {
		
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] != -1) { // avoid overflows & walls.
			
			if (grid[row][col] != 0) { // non start cells.				
				if (currDist < grid[row][col]) {
					grid[row][col] = currDist;
					
					
					Arrays.stream(grid).forEach(arr -> System.out.println(Arrays.toString(arr)));
					System.out.println("\n");
				}				
			}			
			
			
			for (int[] dir: directions) {				
				
				int nextRow = row + dir[0];
				int nextCol = col + dir[1];
				
				System.out.println(nextRow + "," + nextCol);
				
				dfs(grid, nextRow, nextCol, currDist + 1);
			}
		}		
	}
	
	public static int[][] directions = {	                            
		{-1,0}, // up.
		//{-1, 1}, // top rt.
		{0,1}, // rt.
		//{1, 1}, // bottom rt.
		{1,0}, // down.
		//{1, 1}, // bottom rt.
		{0,-1}, // left.
		//{-1,-1}, // top left
	};
	
	public enum CellType {
		WALL(-1),
		GATE(0),
		EMPTY(Integer.MAX_VALUE);		
		private Integer val;
		private CellType(Integer val) {
			this.val = val;
		}
		public Integer getVal() {
			return this.val;
		}
	};
	
}
