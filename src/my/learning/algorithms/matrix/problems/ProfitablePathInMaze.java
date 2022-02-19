package my.learning.algorithms.matrix.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfitablePathInMaze {

	public static void main(String[] args) {
		
		int grid[][] = {
			{ 0,1,0,5 },
			{ 0,1,1,1 },
			{ 2,0,0,0 },
		};
				
		// starting at (2,0) and ending at (0,3).
		for (int row = grid.length - 1; row >= 0; row--) {
			
			for (int col = 0; col < grid[0].length; col++) {
				
				int ltCellVal = getCellVal(grid, row, col - 1); // left cell. 
				int downCellVal = getCellVal(grid, row + 1, col); // down cell.
				
				grid[row][col] += Integer.max(ltCellVal, downCellVal);
			}
		}
		Arrays.stream(grid).forEach(row -> System.out.println(Arrays.toString(row)));
		
		
		// find the profitable path.
		List<Integer[]> path = new ArrayList<>();		
		dfs(grid, 2, 0, path);
		path.forEach(cell -> System.out.println(Arrays.toString(cell)));		
	}
	
	private static void dfs(int[][] grid, int row, int col, List<Integer[]> path) {
		
		if (isValid(grid, row, col)) {
			
			path.add(new Integer[] {row, col});	
			
			if (row == 0 && col == grid[0].length - 1) { // end.				
				
				return;				
			} else {
						
				int topCellVal = getCellVal(grid, row - 1, col); // top cell. 
				int rtCellVal = getCellVal(grid, row, col + 1); // right cell.
				
				if (topCellVal > rtCellVal) {
					
					dfs(grid, row - 1, col, path); // go up.
					
				} else {
					
					dfs(grid, row, col + 1, path); // go right.
				}
			}			
		}		
	}

	private static int getCellVal(int grid[][], int row, int col) {
		if (isValid(grid, row, col)) {
			return grid[row][col];
		}
		return 0;
	}
	
	private static boolean isValid(int grid[][], int row, int col) {
		return (row >= 0 && row < grid.length && col >=0 && col < grid[0].length);
	}

}
