package my.learning.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import my.learning.algorithms.matrix.problems.WallsAndGate;



/**
 * 	bfs, dfs & sequential order.
 *
 */
public class MatrixTraversals {

	public static void main(String[] args) {
		
		int[][] grid = {
			{1,2,3,4,5},
			{6,7,8,9,10},
			{11,12,13,14,15},
			{16,17,18,19,20},
		};
		
		
		System.out.println("DFS");
		dfs(grid).forEach(element -> System.out.print(element + ","));
		
		
		System.out.println("\n\nBFS");
		bfs(grid).forEach(element -> System.out.print(element + ","));	
		
		
		System.out.println("\n\nSequential order");
		Arrays.stream(grid).forEach(row -> System.out.println(Arrays.toString(row)));
	}
	
	
	/**
	 * 	dfs. O(n) time & O(n) space.	
	 */	
	private static List<Integer> dfs(int[][] grid) {
		
		List<Integer> path = new ArrayList<>();
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		dfs(grid, 0, 0, visited, path);
		
		return path;
	}
	
	private static void dfs(int[][] grid, int row, int col, boolean[][] visited, List<Integer> path) {
		
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col]) { // chk to avoid grid overflow & already visited nodes.
			return;
		}
		
		
		path.add(grid[row][col]); 	// process the node.
		visited[row][col] = true; 	// mark the node as visited.
				
		
		for (int[] dir: WallsAndGate.directions) {
			
			int newRow = row + dir[0];
			int newCow = col + dir[1];				
			
			dfs(grid, newRow, newCow, visited, path);
			
			
		}	
	}


	/**
	 * 	bfs. O(n) time & O(n) space.	
	 */	
	private static List<Integer> bfs(int[][] grid) {		
		
		List<Integer> path = new ArrayList<>();
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		
		Queue<Integer[]> q = new LinkedList<>();
		
		q.offer(new Integer[] {2,2}); // starting point.
		
		while (!q.isEmpty()) {
			
			Integer[] coordinates = q.poll(); 
			int row = coordinates[0];
			int col = coordinates[1];
						
			
			if (row >= 0 && row <= grid.length - 1 
					&& col >= 0 && col <= grid[0].length - 1
					&& !visited[row][col]) {
				
				
				path.add(grid[row][col]); // process this node.
				visited[row][col] = true;	 // mark the processed node as visited.
				
				
				
				q.offer(new Integer[] {row - 1, col}); // up.
				q.offer(new Integer[] {row, col + 1}); // right.
				q.offer(new Integer[] {row + 1, col}); // down.
				q.offer(new Integer[] {row, col - 1}); // left.				
			}					
		}	
		
		return path;
	}

}
