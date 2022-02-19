package my.learning.problems.solving.amazon;

import java.util.LinkedList;
import java.util.List;

/**
 * 
Amazon Online Assessment Questions 2019 OA2 -Move Obstacle
You are in charge of preparing a recently purchased lot for Amazon’s building. The lot is covered with trenches and has a single 
obstacle that needs to be taken down before the foundation is prepared for the building. The demolition robot must remove the 
obstacle before the progress can be made on the building.

Write an algorithm to determine the minimum distance required for the demolition robot to remove the obstacle.

Assumptions:
• The lot is flat, except the trenches and can be represented by a 2D grid.
• The demolition robot must start at the top left corner of the lot, which is always flat, and can move on block up, down, right, left
• The demolition robot cannot enter trenches and cannot leave the lot.
• The flat areas are indicated by 1, areas with trenches are indicated by 0, and the obstacle is indicated by 9

Input
The input of the function has 3 arguments: numRows – number of rows
numColumns – number of columns
lot – 2d grid of integers

Output
Return an integer that indicated the minimum distance traversed to remove the obstacle else return -1

Constraints
1<= numRows, numColumns <= 1000

Examples
Input:
numRows = 3
numColumns = 3
lot = [
[1, 0, 0],
[1, 0, 0],
[1, 9, 1]]

Output:
3

Explanation:
Starting from the top-left corner, the demolition robot traversed the cells (0,0) -> (1,0)-> (2,0)->(2,1)
The robot moves 3 times to remove the obstacle “9”
 *
 */

public class ObstacleDemolitionRobot {

	public static void main(String[] args) {
		
		int[][] lotGrid = {
		   {1, 0, 0},
		   {1, 0, 0},
		   {1, 9, 1}
		   
//		   { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
//           { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, 
//           { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
//           { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 
//           { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
//           { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
//           { 1, 0, 0, 0, 0, 9, 0, 0, 0, 1 }, 
//           { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
//           { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
		   
		}; 
		
		int minDist = findMinDistToRemoveObstacle(lotGrid);
		System.out.println(String.format("min dist required for demolotion robot is %s ", minDist - 1));
	}
	
	private static int findMinDistToRemoveObstacle(int[][] lotGrid) {
		
		List<Integer[]> route = new LinkedList<>();
		
		boolean[][] visited = new boolean[lotGrid.length][lotGrid.length];
		
		Integer minDist = findMinDistToRemoveObstacle(lotGrid, 0, 0, visited, route);
		
		for (Integer[] node: route) {
			System.out.println(String.format("(%s,%s)", node[0], node[1]));
		}
		
		return minDist;
	}
	
	private static int findMinDistToRemoveObstacle(int[][] lotGrid, int rowIndex, int colIndex, boolean[][] visited, List<Integer[]> route) {
		
		int minDist = 0;
		
		if (rowIndex < 0 || rowIndex > lotGrid.length - 1) { 	// out of grid.
			return 0;
		} else if (colIndex < 0 || colIndex > lotGrid[0].length - 1) { 	// out of grid.
			return 0;
		} else if (visited[rowIndex][colIndex]) { 	// don't visit already visited cell.
			return 0;
		} else if (lotGrid[rowIndex][colIndex] == 0) { 	// can't enter a trench.
			return 0;
		} else if (lotGrid[rowIndex][colIndex] == 9) { 	// found obstacle.
			
			visited[rowIndex][colIndex] = true;
			
			route.add(new Integer[] {rowIndex, colIndex});
			
			minDist++;
			
			return minDist;
		} else {
			
			visited[rowIndex][colIndex] = true;
			
			route.add(new Integer[] {rowIndex, colIndex});
			
			minDist++;
			
			minDist += findMinDistToRemoveObstacle(lotGrid, rowIndex, colIndex - 1, visited, route); 	// left.
			minDist += findMinDistToRemoveObstacle(lotGrid, rowIndex - 1, colIndex, visited, route); 	// up.
			minDist += findMinDistToRemoveObstacle(lotGrid, rowIndex, colIndex + 1, visited, route); 	// right.
			minDist += findMinDistToRemoveObstacle(lotGrid, rowIndex + 1, colIndex, visited, route); 	// down.		
					
		}	
		
		return minDist;		
	}
	
}
