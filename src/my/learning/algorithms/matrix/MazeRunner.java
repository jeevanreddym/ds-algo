package my.learning.algorithms.matrix;



/**
 * 	Maze runner uses DFS to find a path from source to destination.
 * 	Maze runner is a connection routing method that represents the entire routing space as a grid.
 *  Parts of this grid are blocked by components, specialized areas or already present wiring.
 *  The grid size corresponds to the wiring pitch of the area. The goal is to find a chain of grid cells that go from point A to point B.
 *  A maze runner may use the Lee algorithm (It always gives an optimal solution, if one exists, but is slow and requires considerable memory). 
 *  It uses a wave propagation style (a wave are all cells that can be reached in n steps) throughout the routing space. 
 *  The wave stops when the target is reached, and the path is determined by backtracking through the cells.
 */
public class MazeRunner {
	
	static int minDist = 0;
	
	public static void main(String[] args) {
		
		int[][] maze = { 
			{ 1, 1, 1, 1, 1 }, 
			{ 0, 2, 0, 0, 1 }, 
			{ 0, 1, 1, 1, 0 }, 
			{ 0, 0, 0, 1, 3 },
			{ 1, 1, 0, 0, 0 }, 
		};		
		
		boolean foundAWay = findWayOut(maze, 1, 1);
		System.out.println("foundAWay: " + foundAWay);
	}
	
	public static boolean findWayOut(int[][] maze, int startRow, int startCol) {		
		
		boolean[][] visited = new boolean[maze.length][maze[0].length];	
				
		return findaWayOut(maze, startRow, startCol, visited, 0);
	}
	
	private static boolean findaWayOut(int[][] maze, int row, int col, boolean[][] visited, int currDist) {
		
		if (row < 0 || row > maze.length - 1 || col < 0 || col > maze[0].length - 1) { // out of maze.
			return false;
		} else if (visited[row][col]) { // already visited cell.
			return false;		
		} else if (maze[row][col] == 1) { // if its a wall then we can't go in that direction.
			return false;			
		} else if (maze[row][col] == 3) { // Exit condition.
			System.out.println("min dist: " + currDist);
			return true;
		} 
		
		
		visited[row][col] = true;
		
		currDist++;
		
		
		return 
		
		findaWayOut(maze, row + 1, col, visited, currDist) // down.	
		
		||
		
		findaWayOut(maze, row, col + 1, visited, currDist) // right.	
		
		||
		
		findaWayOut(maze, row, col - 1, visited, currDist) // left.	
		
		||
		
		findaWayOut(maze, row - 1, col, visited, currDist); // up.
		
	}
		

	static void findWayOut(int[][] maze, int row, int col, boolean[][] visited) throws RuntimeException {
		
		if (row < 0 || row > maze.length - 1 || col < 0 || col > maze[0].length - 1) { // out of maze.
			return;
		} else if (visited[row][col]) { // already visited cell.
			return;		
		} else if (maze[row][col] == 1) { // if its a wall then we can't go in that direction.
			return;			
		} else if (maze[row][col] == 3) { // Exit condition.
			throw new RuntimeException();
		} else {
			
			visited[row][col] = true;
			
			minDist++;
			
			findWayOut(maze, row + 1, col, visited); // going down.	
			findWayOut(maze, row, col + 1, visited); // going right.	
			findWayOut(maze, row, col - 1, visited); // going left.	
			findWayOut(maze, row - 1, col, visited); // going up.	
						
			//visited[row][col] = false;
		}
	}
	
}
