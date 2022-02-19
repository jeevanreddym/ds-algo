package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 	In a Weighed graph an edge can have => weight, distance or cost. 
 * 
 * 	time: O(E * Vlog(V))
 */
public class DijkstrasWeighedGraphShortestPath {

	private Set<Vertex> vertices; 
		
	public DijkstrasWeighedGraphShortestPath() {
		this.vertices = new HashSet<>();
	}
	
	
	/**
	 * 	Shortest path tree from a source to all other vertices in the graph using bfs.
	 */
	private void computeAllMinimumPathsFrom(Vertex source) {
		
		/**
		 * 	Using Priority Queue (min heap) here to get the next vertex with min distance. 
		 */
		PriorityQueue<Vertex> pq = new PriorityQueue<>((v1,v2) -> v1.minDist - v2.minDist);
		
		source.minDist = 0; // setting to 0 as dist from node to itself is 0.
		pq.offer(source);
		
		while (!pq.isEmpty()) {
			
			Vertex curr = pq.poll(); // gives the next vertex with min dist.
			
			for (Edge neighbourEdge: curr.neighbours) {
			
				Vertex neighbour = neighbourEdge.target;
				
				/**
				 * 	new dist = min dist so far to the curr node + dist from curr node to target node.  
				 */				
				int newDist = curr.minDist + neighbourEdge.distance;
				
				if (newDist < neighbour.minDist) { // update if new min dist is less that existing min distance for the neighbour node.
					
					pq.remove(neighbour);
					
					neighbour.minDist = newDist;
					
					neighbour.predecessor = curr;
					
					pq.offer(neighbour); // removing, updating min dist & adding back to Priority Queue. 
				}				
			}			
		}		
	}
	
	/**
	 * 	Get shortest path from source to one of the requested target vertex.
	 */
	private List<Vertex> getShortestPathTo(Vertex target) {		
		List<Vertex> shortestPathFromSourceToTarget = new ArrayList<>();				
		Vertex curr = target;
		while (curr != null) {
			shortestPathFromSourceToTarget.add(curr);	
			curr = curr.predecessor;
		}		
		Collections.reverse(shortestPathFromSourceToTarget); // reverse to make the path from source to target vertex.		
		return shortestPathFromSourceToTarget;
	}
	
	/**
	 * 	Get shortest path from source to target vertex.
	 */
	public List<String> getShortestPathBetween(Vertex source, Vertex target) {
				
		computeAllMinimumPathsFrom(source); // compute shortest paths from source node to all other nodes in graph.	
		
		List<Vertex> shortestPath = getShortestPathTo(target); // find the shortest path from the target node to the source node.
		
		return shortestPath.stream().map(vertex -> vertex.name).collect(Collectors.toList());
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
		a.addNeighbour(d, 115);
		
		b.addNeighbour(d, 35);
		b.addNeighbour(e, 2);
		
		c.addNeighbour(b, 6);
		c.addNeighbour(e, 6);
		
		d.addNeighbour(c, 8);
		
		
		DijkstrasWeighedGraphShortestPath weighedGraph = new DijkstrasWeighedGraphShortestPath();
		weighedGraph.addVertex(a);
		weighedGraph.addVertex(b);
		weighedGraph.addVertex(c);
		weighedGraph.addVertex(d);
		weighedGraph.addVertex(e);
		
		
		List<String> shortestPath = weighedGraph.getShortestPathBetween(a, b);
			
		System.out.println("shortest path from node a to b:");
		shortestPath.forEach(node -> System.out.print(node + " -> "));
	}	
	
}
