package my.learning.algorithms.recursion.backtracking;



/**
 * 
 * 	Prints out the placement of n queens on a NXN chess board such that no queen can attack other queen. 
 * 	If there is no solution, it prints out no solution.
 * 
 */
public class NQueensPlacement {

	public static void main(String[] args) {		
		nQueens(4);
	}	

    public static void nQueens(int n){
        int[] board = new int[n];
        if(!nQueens(board,0)){
            System.out.println("No solution");
        }
    }

	public static boolean nQueens(int[] board, int i) {
		
		if (i == board.length) {			
			printOutput(board);
			return true;
		}		

		for (int c = 0; c < board.length; c++) {
			
			boolean flag = false;
			for (int r = 0; r < i; r++) {
				if (board[r] == c || Math.abs(board[r] - c) == (i - r)) {
					flag = true;
					break;
				}
			}
			
			if (flag)
				continue;
			
			board[i] = c;
			if (nQueens(board, i + 1)) {
				return true;
			}			
		}
		
		return false;
	}
	
	private static void printOutput(int[] board) {
		for (int row: board) {				
			for (int c = 0; c < board.length; c++) {					
				if (c == row) {						
					System.out.println(" 0 ");
				} else {
					System.out.println(" X ");
				}
			}
			System.out.println("");
		}
	}
	
}