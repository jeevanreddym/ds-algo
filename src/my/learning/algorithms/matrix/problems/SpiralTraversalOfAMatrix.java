package my.learning.algorithms.matrix.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralTraversalOfAMatrix {

	public static void main(String[] args) {
		
		int[][] matrix = {
			{ 1, 2, 3},
			{ 4, 5, 6},
			{ 7, 8, 9},
		};
		
		System.out.println(Arrays.toString(spiralTraversal(matrix).toArray()));
	}

	private static List<Integer> spiralTraversal(int[][] matrix) {
		
		List<Integer> spiralOrder = new ArrayList<>();
		
		// Our restricting boundaries - imagine a "fence"
	    int topFence = 0;
	    int bottomFence = matrix.length - 1; // last row index
	    int leftFence = 0;
	    int rightFence = matrix[0].length - 1; // last col index
		
	    while (topFence <= bottomFence && leftFence <= rightFence) {
	    	
	    	 // Walk top fence
	        for (int col = leftFence; col <= rightFence; col++) {
	        	
	        	spiralOrder.add(matrix[topFence][col]);
	        	
	        }
	        topFence++; // Push top fence in
	        
	        
	        // Walk right fence
	        for (int row = topFence; row <= bottomFence; row++) {
	        	
	        	spiralOrder.add(matrix[row][rightFence]);
	        }
	        rightFence--; // Close right fence in
	        
	        
	        /**
	         * If center is a horizontal line, prevent the bottom from rereading what the top row already read.
	         */
			if (topFence <= bottomFence) {
				// Walk bottom fence
				for (int col = rightFence; col >= leftFence; col--) {

					spiralOrder.add(matrix[bottomFence][col]);
				}
			}
	        bottomFence--; // Close the bottom fence in
	        
	        
			/**
			 * If center is a vertical line, prevent the left from rereading what the right col already read.
			 */
			if (leftFence <= rightFence) {
				// Walk left fence
				for (int row = bottomFence; row >= topFence; row--) {
					
					spiralOrder.add(matrix[row][leftFence]);
				}
				leftFence++; // Push to left fence in
			}

	    }
		
		return spiralOrder;
	}
	
}
