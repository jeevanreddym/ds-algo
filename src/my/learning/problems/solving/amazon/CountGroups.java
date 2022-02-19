package my.learning.problems.solving.amazon;

import java.util.Arrays;
import java.util.List;

import my.learning.algorithms.matrix.problems.NumberOfIslands;



/**
 * 
	first of all I have converted the input list of Strings to a 2d matrix of book exchanges so that I can traverse through the matrix to find relations.

	then I do a dfs from each of the rows to check if the adjacent people are related (having gifted/received gift) by checking the status of the cell. By this I know if 2 people are related to each other or not. when i start over on the next row if I find that people in that row are not related (visited[row] == false) then I increment the count of groups & start finding relations from there. visited[] is used to avoid checking relations again and again.

	Time complexity: O(V + E) where V is number of vertices(number of people) & E is number of edges (2 uni directional relation between 2 people).

	Space complexity: O(V), V is number of vertices because of the dfs implicit stack max depth.
 *
 */
public class CountGroups {

	public static void main(String[] args) {
		
		List<String> related = Arrays.asList("110", "101", "001");
		
		System.out.println(countGroups(related));		
	}
	
	public static int countGroups(List<String> related) {
		if (related == null || related.size() == 0) {
			return 0;
		}
		int[][] grid = strTo2DMatrix(related);		
		return countGroups(grid);
		//return NumberOfIslands.findNumberOfIslands(grid);		
	}

	private static int countGroups(int[][] grid) {
		int noOfGroups = 0;

		boolean[] visited = new boolean[grid.length];
		for (int row = 0; row < grid.length; row++) {
			if (!visited[row]) { // chk if row is not visited.
				noOfGroups++;
				dfs(grid, row, visited); // dfs
			}
		}
		
		return noOfGroups;
	}

	private static void dfs(int[][] grid, int row, boolean[] visited) {
		
		visited[row] = true;
		
		for (int col = 0; col < grid.length; col++) {
			if (grid[row][col] == 1 && !visited[col]) {
				visited[col] = true;
				dfs(grid, col, visited);
			}
		}
	}

	
	
	
	private static int[][] strTo2DMatrix(List<String> related) {
		int[][] grid = new int[related.size()][related.get(0).length()];
		int currRow = 0;
		for (String rowDetails: related) {
            
			String[] items = rowDetails.split("");
			
			int currCol = 0;
			for (String item: items) {
				grid[currRow][currCol++] = Integer.valueOf(item);
			}			
			
			++currRow;
        }
		
		//Arrays.stream(grid).forEach(row -> System.out.println(Arrays.toString(row)));
		return grid;
	}

}

