package my.learning.algorithms.matrix.problems;

public class TransposeOfAMatrix {

	public static void main(String[] args) {
		
		int[][] matrix = {
			{ 1, 2, 3},
			{ 4, 5, 6},
			{ 7, 8, 9},
		};
		
		transpose(matrix);
	}

	private static void transpose(int[][] matrix) {
		
		int size = matrix.length - 1;
		
		for (int layer = 0; layer < matrix.length / 2; layer++) {
			
			for (int i = layer; i < size - layer; i++) {
				
				int top = matrix[layer][i];
				int right = matrix[layer][size - layer];
				int bottom = matrix[size - layer][size - i];
				int left = matrix[size - i][layer];
				
			}
			
		}
		
		
	}
	
}
