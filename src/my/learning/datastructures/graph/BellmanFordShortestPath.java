package my.learning.datastructures.graph;

import java.util.HashSet;
import java.util.Set;


/**
 * 	In a Weighed graph an edge can have => weight, distance or cost. 
 */
public class BellmanFordShortestPath {

	private Set<Vertex> vertices; 
		
	public BellmanFordShortestPath() {
		this.vertices = new HashSet<>();
	}
	
	
	

	public void addVertex(Vertex vertex) {
		this.vertices.add(vertex);
	}
	
	private static class Vertex implements Comparable<Vertex> {
		
		String name;		
		Set<Edge> neighbours;		
		Vertex predecessor; // previous node in the shortest path route.
		int minDist = Integer.MAX_VALUE; // total min dist needed to travel from a specific source node in the graph to this node.
			
		
		public Vertex(String name) {
			this.name = name;
			this.neighbours = new HashSet<>();
		}
		
		public void addNeighbour(Vertex target, int weight) {
			this.neighbours.add(new Edge(target, weight));
		}
		
		@Override
		public String toString() {
			return String.format("%s", name);
		}

		@Override
		public int compareTo(Vertex other) {			
			return this.minDist - other.minDist;
		}		
	}
	
	private static class Edge {
		
		Vertex target;
		int distance;
		
		public Edge(Vertex target, int distance) {
			this.target = target;
			this.distance = distance;
		}
		
		@Override
		public String toString() {
			return String.format("%s(%s)", target, distance);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
				
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
			
				
		a.addNeighbour(b, 110);
		a.addNeighbour(c, 20);
		a.addNeighbour(d, -115);
		
		b.addNeighbour(d, 35);
		b.addNeighbour(e, 2);
		
		c.addNeighbour(b, -6);
		c.addNeighbour(e, -6);
		
		d.addNeighbour(c, -8);
		
		
		BellmanFordShortestPath weighedGraph = new BellmanFordShortestPath();
		weighedGraph.addVertex(a);
		weighedGraph.addVertex(b);
		weighedGraph.addVertex(c);
		weighedGraph.addVertex(d);
		weighedGraph.addVertex(e);
		
		
//		List<String> shortestPath = weighedGraph.getShortestPathBetween(a, b);
//			
//		System.out.println("shortest path from node a to b:");
//		shortestPath.forEach(node -> System.out.print(node + " -> "));
	}	
	
}
