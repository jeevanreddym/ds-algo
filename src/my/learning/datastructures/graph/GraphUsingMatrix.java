package my.learning.datastructures.graph;

public class GraphUsingMatrix<V> {

	private V[][] adjMatrix;
	private int noOfVertices;
	
	@SuppressWarnings("unchecked")
	public GraphUsingMatrix(int noOfVertices) {
		this.noOfVertices = noOfVertices;
		this.adjMatrix = (V[][]) new Object[noOfVertices][noOfVertices];
	}
		
	public void addEdge(V v1, V v2) {
		//this.adjMatrix[v1][v2] = 1;
		//this.adjMatrix[v2][v1] = 1;
	}
	

	public void print() {
		for (int i = 0; i < noOfVertices; i++) {
			for (int j = 0; j < noOfVertices; j++) {
				// We only want to print the values of those positions that have been marked as set.
				//if (isSetMatrix[i][j])
					System.out.format("%8s", String.valueOf(adjMatrix[i][j]));
				//else
					//System.out.format("%8s", "/  ");
			}
			System.out.println();
		}
	}
	
	/*
	 * public boolean hasEdge(int source, int destination) { return
	 * adjMatrix[source][destination]; }
	 * 
	 * public Float getEdgeValue(int source, int destination) { if (!weighted ||
	 * !isSetMatrix[source][destination]) return null; return
	 * matrix[source][destination]; }
	 */
	
	
	public static void main(String[] args) {
		
		GraphUsingMatrix<Integer> graph = new GraphUsingMatrix<>(20);
				
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);
		graph.addEdge(4, 1);
		graph.addEdge(4, 2);
		graph.addEdge(4, 3);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 2);
		graph.addEdge(5, 3);
		graph.addEdge(5, 7);
		graph.addEdge(5, 8);
		graph.addEdge(5, 9);
		graph.addEdge(6, 5);
		graph.addEdge(6, 8);
		graph.addEdge(7, 1);
		graph.addEdge(7, 2);
		graph.addEdge(7, 3);
		graph.addEdge(8, 3);
		graph.addEdge(8, 4);
		graph.addEdge(8, 5);
		graph.addEdge(9, 5);
			
				
		graph.print();
	}
	
}
