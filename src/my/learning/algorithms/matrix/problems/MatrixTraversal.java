package my.learning.algorithms.matrix.problems;

import java.util.ArrayList;
import java.util.List;

import my.learning.algorithms.matrix.problems.WallsAndGate;



public class MatrixTraversal {
	
	public static void main(String[] args) {
		
		int[][] grid = {
            {1, 1, 0, 3},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 0}
	    };
	    
	    dfs(grid);
	}
	
	static void dfs(int[][] grid) {
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		List<Integer[]> path = new ArrayList<>();
				
		dfs(grid, 0, 0, visited, path);
		
		path.forEach(cell -> System.out.println(String.format("[%s,%s] = %s", cell[0], cell[1], grid[cell[0]][cell[1]])));	
		
		int pathSum = path.stream().map(cell -> grid[cell[0]][cell[1]]).reduce(0, (a,b) -> a + b);				
		System.out.println("Path sum: " + pathSum);
	}
	
	static void dfs(int[][] grid, int row, int col, boolean[][] visited, List<Integer[]> path) {
				
		int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;
		
        
        // invalid coordinates or already visited cell or blocked cell. 
		if (row < 0 || row > maxRow || col < 0 || col > maxCol || visited[row][col] || grid[row][col] == 0) {			
			return;			
		}		
				
		else if (grid[row][col] == 3) { // found path to destination.
			
			visited[row][col] = true;			
			path.add(new Integer[] {row, col});
			return;			
		} 
		
		else { // continue dfs on adjacent cells.
		
			visited[row][col] = true;
			
			path.add(new Integer[] {row, col});
			
			for (int[] dir: WallsAndGate.directions) {				
				int newRow = row + dir[0];
				int newCow = col + dir[1];				
				dfs(grid, newRow, newCow, visited, path);
			}					
			
			visited[row][col] = false;
			path.remove(path.size() - 1); // backtrack.			
		}
		
	}
	
}
