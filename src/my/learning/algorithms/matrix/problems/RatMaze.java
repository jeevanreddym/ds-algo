package my.learning.algorithms.matrix.problems;

import java.util.ArrayList;
import java.util.List;

/**
 *  solve Rat in a Maze problem using backtracking.
 *  @SearchMazeForAnExit
 */
public class RatMaze {

	public static void main(String args[]) {
		
		int[][] maze = { 
			{ 1, 0, 0, 0 }, 
			{ 1, 1, 0, 1 }, 
			{ 0, 1, 0, 0 }, 
			{ 1, 1, 1, 2 } 
		};
		
		solveMaze(maze);
	}
	
	/**
	 * This function solves the Maze problem using Backtracking. 
	 * It mainly uses solveMazeUtil() to solve the problem. It returns false if no path is
	 * possible, otherwise return true and prints the path in the form of 1s. 
	 * Please note that there may be more than one solutions, this function prints one of
	 * the feasible solutions.
	 */
	static boolean solveMaze(int[][] maze) {
		
		int[][] sol = new int[maze.length][maze.length];
		
		List<Integer[]> path = new ArrayList<>();

		if (!solveMazeUtil(maze, 0, 0, sol, path)) {
			System.out.print("Solution doesn't exist");
			return false;
		}

		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[0].length; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
		
		path.forEach(cell -> System.out.println(cell[0] + "," + cell[1]));
		
		
		return true;
	}

	/**
	 * 	A recursive utility function to solve Maze problem
	 */
	static boolean solveMazeUtil(int[][] maze, int row, int col, int[][] sol, List<Integer[]> path) {
				
		// if (row, col is goal) return true
		if (isValid(maze, row, col) && maze[row][col] == 2) {
			sol[row][col] = 1;
			path.add(new Integer[] {row, col});
			return true;
		}
		
		
		final int[][] directions = { 
			//{-1,0}, // going up
			{0,1}, // going right
			{1,0}, // going down
			//{0,-1}, // going left			
		};

		

		// Check if maze[row][col] is valid
		if (isSafe(maze, row, col)) {
			
			// mark row, col as part of solution path
			sol[row][col] = 1;
			path.add(new Integer[] {row, col});
						
			// Move forward in all possible directions.
			for (int[] dir: directions) { // up, rt, down, left directions.
				
				int nextRow = row + dir[0];
				int nextCol = col + dir[1];				
				
				if (solveMazeUtil(maze, nextRow, nextCol, sol, path))
					return true;			
			}
			
			// If none of the above movements works then BACKTRACK: unmark row, col as part of solution path.
			sol[row][col] = 0;
			path.remove(path.size() - 1);
			return false;
		}

		return false;
	}

	/**
	 * 	a utility function to check if row, col is valid index for N*N maze
	 */
	static boolean isSafe(int[][] maze, int row, int col) {
		return isValid(maze, row, col) && maze[row][col] == 1;
	}
	
	static boolean isValid(int[][] maze, int row, int col) {
		return (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length);
	}
	
}