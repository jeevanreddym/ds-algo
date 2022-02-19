package my.learning.algorithms.matrix.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;



/**
 * 	@RatMaze
 */
public class SearchMazeForAnExit {

	
	public static void main(String[] args) {
		
		SearchMazeForAnExit mazeSolver = new SearchMazeForAnExit();
		
		Coordinate start = new Coordinate(0, 0);
	    Coordinate end = new Coordinate(0, 3);
		
		
		List<List<Color>> maze = Arrays.asList(
			Arrays.asList(Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE),
			Arrays.asList(Color.WHITE, Color.BLACK, Color.WHITE, Color.WHITE),
			Arrays.asList(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE)
		); 
		
		List<Coordinate> path = mazeSolver.findPath(maze, start, end);
		path.forEach(cell -> System.out.println(cell.row + "," + cell.col));		
	} 
	
	
	public List<Coordinate> findPath(List<List<Color>> maze, Coordinate start, Coordinate end) {
		
		List<Coordinate> path = new ArrayList<>();

		maze.get(start.row).set(start.col, Color.BLACK);
		path.add(start);

		if (!hasPathToEnd(maze, start, end, path)) { // dfs.			
			path.remove(path.size() - 1);
		}

		return path;
	}

	/**
	 * 	A standard DFS for the path that we want to find.
	 */
	private boolean hasPathToEnd(List<List<Color>> maze, Coordinate curr, Coordinate end, List<Coordinate> path) {
		
		if (curr.equals(end)) {
			return true;
		}

		final int[][] directions = { 
			{ 0, 1 }, // going right
			{ 1, 0 }, // going down
			{ 0, -1 }, // going left
			{ -1, 0 } // going up
		};

		for (int[] direction: directions) { // up, rt, down, left directions.
			
			Coordinate next = new Coordinate(curr.row + direction[0], curr.col + direction[1]);
			
			if (canTraverse(next, maze)) {

				maze.get(next.row).set(next.col, Color.BLACK);

				path.add(next);

				if (hasPathToEnd(maze, next, end, path)) {
					return true;
				}
				
				path.remove(path.size() - 1);
			}
		}

		return false;
	}

	/**
	 * Validates a given cell, it ensures it is in the maze and is white (can be "walked on")
	 */
	private static boolean canTraverse(Coordinate curr, List<List<Color>> maze) {
		return curr.row >= 0 && curr.row < maze.size() 
				&& curr.col >= 0 && curr.col < maze.get(curr.row).size()
				&& maze.get(curr.row).get(curr.col) == Color.WHITE;
	}

	public static class Coordinate {
		
		public int row, col;
		
		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}

			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Coordinate that = (Coordinate) o;
			if (row != that.row || col != that.col) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			return Objects.hash(row, col);
		}
	}

	public static enum Color {
		WHITE, BLACK
	}

}
