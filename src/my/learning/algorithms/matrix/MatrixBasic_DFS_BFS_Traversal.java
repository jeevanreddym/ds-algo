package my.learning.algorithms.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 	BFS returns the shortest path and DFS returns just a path.
 */

public class MatrixBasic_DFS_BFS_Traversal {
	
	public static void main(String[] args) {
				
		int[][] grid = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
	    };
		    
		System.out.println("DFS");
	    dfs(grid);		
	    
	    System.out.println("\n BFS");
	    bfs(grid);
	}
	
	static void dfs(int[][] grid) {
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		List<Integer[]> path = new ArrayList<>();
				
		dfs(grid, 0, 0, visited, path);
		
		path.forEach(cell -> System.out.println(String.format("[%s,%s] = %s", cell[0], cell[1], grid[cell[0]][cell[1]])));		
	}
	
	static void dfs(int[][] grid, int row, int col, boolean[][] visited, List<Integer[]> path) {
				
		int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;
		
		if (row < 0 || row > maxRow || col < 0 || col > maxCol || visited[row][col]) {			
			return;			
		} else {
		
			// mark the cell visited.
			visited[row][col] = true;
			path.add(new Integer[] { row, col });
						
			dfs(grid, row - 1, col, visited, path); // up.
			dfs(grid, row, col + 1, visited, path); // right.
			dfs(grid, row + 1, col, visited, path); // bottom.
			dfs(grid, row, col - 1, visited, path); // left.
					
		}
	}
	
	
	static void bfs(int[][] grid) {
		
		int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		List<Integer[]> path = new ArrayList<>();
		
		Queue<Integer[]> q = new LinkedList<>();
		
		q.offer(new Integer[] {0, 0}); // start from top left.
				
		while (!q.isEmpty()) {
			
			Integer[] cell = q.poll();			
			int row = cell[0];
			int col = cell[1];
			
			if (row < 0 || col < 0 || row > maxRow || col > maxCol || visited[row][col]) {
				continue;	
			}
                			
			visited[row][col] = true;
			path.add(new Integer[] { row, col});
			
			q.offer(new Integer[] { row - 1, col}); // up.
			q.offer(new Integer[] { row, col + 1}); // right.
			q.offer(new Integer[] { row + 1, col}); // bottom.
			q.offer(new Integer[] { row, col - 1}); // left.
			
		}	
		
		path.forEach(cell -> System.out.println(String.format("[%s,%s] = %s", cell[0], cell[1], grid[cell[0]][cell[1]])));		
	}
	
}
