package my.learning.datastructures.graph.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

	public static void main(String[] args) {
		
		GraphVertex v1 = new GraphVertex(1);
		GraphVertex v2 = new GraphVertex(2);
		GraphVertex v3 = new GraphVertex(3);
		GraphVertex v4 = new GraphVertex(4);
		GraphVertex v5 = new GraphVertex(5);
		GraphVertex v6 = new GraphVertex(6);
		
		v1.addNeighbour(v2);
		v1.addNeighbour(v3);
		v1.addNeighbour(v4);
		
		v2.addNeighbour(v3);
		v2.addNeighbour(v4);
		
		v3.addNeighbour(v5);
		v3.addNeighbour(v6);
		
		
		GraphVertex v1Clone = clone(v1);
		printGraph(v1Clone);
	}
	

	private static GraphVertex clone(GraphVertex start) {

		if (start == null) {
			return null;
		}
		 
		/*
	     * vertexMap: Map the original node reference to its clone
	     * queue: Our queue for Breadth First Search
	     */
	    Map<GraphVertex, GraphVertex> vertexMap = new HashMap<>();
	    Queue<GraphVertex> queue = new LinkedList<>();
	    
	    
	    vertexMap.put(start, new GraphVertex(start.data));
	    queue.offer(start);
	    
	    
	    /*
	     * The breadth first search continues until we have processed all vertices in
	     * the original graph. We know this is done when the queue is empty
	     */
	    while (!queue.isEmpty()) {
	    	
	    	// We grab a node. We will express all of the edges coming off of this node.
	    	GraphVertex currVertex = queue.poll();
	      
	    	// Iterate over all adjacents.
	        for (GraphVertex neighbor: currVertex.neighbors) {
	        	
	        	// Has this neighbor been given a clone?
	        	if (!vertexMap.containsKey(neighbor)) {
	        		
	        		/*
	                 * No? Give it a mapping and add the original neighbor to the search queue so we
	                 * can express ITS edges later
	                 */
	        		vertexMap.put(neighbor, new GraphVertex(neighbor.data));	
	        		queue.add(neighbor);	        		                
	        	}
	        	
	        	
	        	/*
	             * 	Draw the edge from currVertex's clone to neighbor's clone. 
	             * 	Do you see how our hashtable makes this quick access possible?
	             */
	            vertexMap.get(currVertex).addNeighbour(vertexMap.get(neighbor));
	        }
	    }
	    
	    // Return the clone of the start. This is the entry point for the cloned graph section.
	    return vertexMap.get(start);
	}
	

	private static void printGraph(GraphVertex start) {
		if (start == null) return;		 
		
	    Queue<GraphVertex> queue = new LinkedList<>();
	    start.visited = true;
	    queue.offer(start);
	    
	    while (!queue.isEmpty()) {
	    	
	    	// We grab a node. We will express all of the edges coming off of this node.
	    	GraphVertex currVertex = queue.poll();
	    	System.out.println(currVertex.data); 
	      
	    	// Iterate over all adjacents.
	        for (GraphVertex neighbor: currVertex.neighbors) {	        	
	        	if (!neighbor.visited) {	
	        		neighbor.visited = true;
	        		queue.add(neighbor);	                
	        	}	        	
	        }
	    }
	}
	
	static class GraphVertex {
		int data;
		boolean visited;
		List<GraphVertex> neighbors;
		public GraphVertex(int data) {
			this.data = data;
			this.neighbors = new LinkedList<>();
		}
		public void addNeighbour(GraphVertex v) {
			this.neighbors.add(v);
		}
	}
	
}