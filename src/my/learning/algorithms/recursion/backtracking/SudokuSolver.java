package my.learning.algorithms.recursion.backtracking;

import java.util.Arrays;

public class SudokuSolver {

	public static void main(String[] args) {
		
		// Suduko board.
		int[][] sudukoBoard = { 
            {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
	    }; 
		
		solveSudoku(sudukoBoard);
		
		Arrays.stream(sudukoBoard).forEach(row -> System.out.println(Arrays.toString(row)));		
	}

	static int[][] solveSudoku(int[][] board) {		
		solvedFromCell(board, 0, 0);
		return board;
	}

	static boolean solvedFromCell(int[][] board, int row, int col) {
		
		int maxCol = board[row].length;
		
		if (col == maxCol) {
			
			col = 0; // reset column to 0th column of next row.
			row++; // move to next row.

			if (row == board.length) { // end of last row on board.
				return true;
			}
		}

		// Skip entries already filled out. They already have a value in them.
		if (board[row][col] != 0) {
			return solvedFromCell(board, row, col + 1);
		}

		for (int numToPlace = 1; numToPlace <= board.length; numToPlace++) {
							
			if (canPlaceValue(board, row, col, numToPlace)) {
			
				board[row][col] = numToPlace;

				if (solvedFromCell(board, row, col + 1)) {
					return true;
				}

				board[row][col] = 0;
			}
		}

		return false;
	}

	static boolean canPlaceValue(int[][] board, int row, int col, int numToPlace) {
		
		// Check column of the placement
		for (int[] placementRow : board) {
			if (numToPlace == placementRow[col]) {
				return false;
			}
		}

		// Check row of the placement
		for (int i = 0; i < board[row].length; i++) {
			if (numToPlace == board[row][i]) {
				return false;
			}
		}

		// Check region constraints - get the size of the sub-box
		int regionSize = (int) Math.sqrt(board.length);

		int verticalBoxIndex = row / regionSize;
		int horizontalBoxIndex = col / regionSize;

		int topLeftOfSubBoxRow = regionSize * verticalBoxIndex;
		int topLeftOfSubBoxCol = regionSize * horizontalBoxIndex;

		for (int i = 0; i < regionSize; i++) {
			for (int j = 0; j < regionSize; j++) {
				if (numToPlace == board[topLeftOfSubBoxRow + i][topLeftOfSubBoxCol + j]) {
					return false;
				}
			}
		}

		return true;
	}
	
}