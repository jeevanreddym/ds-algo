package my.learning.algorithms.recursion.backtracking;

import java.util.Arrays;

/**
 * 	On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. 
 * 	The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
	
	A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, 
	then one square in an orthogonal direction.
 *
 */
public class KnightsTour {

	
	public static void main(String[] args) {
		
		int n = 4; // n * n chess board.
		int k = 2; // # of moves.
		
		double[][][] dp = new double[k + 1][n][n];
		Arrays.stream(dp).forEach(cell -> Arrays.fill(cell, -1));
				
		double knightProbability = knightProbability(n, k, 2, 2, dp);
		System.out.println(String.format("probability the knigh is on the board after %s moves is %s", k, knightProbability));
	}
	
	
	public static double knightProbability(int n, int k, int row, int col, double[][][] dp) {
        
		if (row < 0 || row >= n || col < 0 || col >= n) {
			return 0.0;
		} else if (k == 0) {
			return 1.0;
		}
		
		if (dp[k][row][col] == -1) {
			
			double prob = 0.0;
			
			for (int[] dir: dirs) {
			
				prob += knightProbability(n, row + dir[0], col + dir[1], k - 1, dp)/8;
			}
			
			dp[k][row][col] = prob;			
		}
		
		return dp[k][row][col];
    }
	
	// 8 ways a night can move on a chess board.
	private static int[][] dirs = {
		{ - 2, - 1 }, // up left.
		{ - 2, + 1 }, // up right.
		{ - 1, + 2 }, // right up.
		{ + 1, + 2 }, // right down.
		{ + 2, - 1 }, // down left.
		{ + 2, + 1 }, // down right.
		{ - 1, - 2 }, // left up.
		{ + 1, - 2 }, // left down.
	};
	
}
