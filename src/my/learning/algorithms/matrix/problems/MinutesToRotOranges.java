package my.learning.algorithms.matrix.problems;

import java.util.LinkedList;
import java.util.Queue;



public class MinutesToRotOranges {

	public static void main(String[] args) {
		
		int[][] grid1 = {
			{2,1,1,0,0},
			{1,1,1,0,0},
			{0,1,1,1,1},
			{0,1,0,1,1},
		};
		System.out.println("# of mins for all oranges to rot: " + minToRotAllOranges(grid1));
		
		
		int[][] grid2 = {
			{1,1,0,0,0},
			{2,1,0,0,0},
			{0,0,0,1,2},
			{0,1,0,0,1},
		};
		System.out.println("# of mins for all oranges to rot: " + minToRotAllOranges(grid2));
	}
	
	
	/**
	 * 	
	 */
	private static int minToRotAllOranges(int[][] grid) {
		
		Queue<Integer[]> q = new LinkedList<>();
		
		
		int noOfFreshOranges = 0;
		
		// sequential traversal to check each element.
		for (int row = 0; row < grid.length; row++) {
			
			for (int col = 0; col < grid[0].length; col++) {
				
				if (grid[row][col] == 1) { // found fresh orange.
					
					noOfFreshOranges++;		
				}		
				
				else if (grid[row][col] == 2) { // found rotten orange.
					
					q.offer(new Integer[] {row, col}); // add it to the Q.
				}
			}			
		}
		
		
		
		int[][] directions = {
			{-1, 0}, // up.
			{0, +1}, // rt.
			{+1, 0}, // down.
			{0, -1}, // left.
		};
		
		
		int minsToRot = 0;
		int currQSize = q.size();
		
		// from this coordinate do a bfs to find all the adjacent coordinates which are part of this same island & mark those coordinates as 0.
		while (!q.isEmpty()) {
			
			if (currQSize == 0) { // means current level of items are processed. Need to process next level of items now.
				
				minsToRot++; // increment mins.
				
				currQSize = q.size(); // new q size after adding elements from the next layer using bfs.
			}
			
			
			Integer[] currOrangeCoordinates = q.poll(); // deQ next element from q.
			int currRow = currOrangeCoordinates[0];
			int currCol = currOrangeCoordinates[1];
			
			currQSize--; // reduce current size.
			
			
			
			for (int[] direction: directions) {
				
				int nextRow = currRow + direction[0];
				int nextCol = currCol + direction[1];
				
				/**
				 * 	check if coordinate fits in the grid & is part of the current island.
				 * 	valid cell & cell contains fresh orange.
				 */
				if (nextRow >= 0 && nextRow < grid.length && nextCol >=0 && nextCol < grid[0].length && grid[nextRow][nextCol] == 1) {
					
					grid[nextRow][nextCol] = 2; // rot the fresh orange as it has come in contact with a rotten orange.
					
					q.offer(new Integer[] {nextRow, nextCol}); // push it on to Q to be processed.
					
					noOfFreshOranges--; // reduce # of fresh oranges.
				}	
			}						
		}
		
		
		if (noOfFreshOranges > 0) { // means all oranges didn't get rotten by the end of iteration.
			return -1;
		}
		
		return minsToRot;
	}
	
}
