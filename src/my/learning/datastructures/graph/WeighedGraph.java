package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/**
 * 	In a Weighed graph an edge can have => weight, distance or cost (denoted by W). 
 */
public class WeighedGraph<V,W> {

	private Map<V, List<Edge<V,W>>> adjList;
	
	public WeighedGraph() {
		this.adjList = new HashMap<>();
	}
	
	public void addVertex(V vertex) {
		adjList.put(vertex, new ArrayList<>());
	}
	
	public void addEdge(V v1, V v2, W weight) {		
		adjList.get(v1).add(new Edge<>(v2, weight));
	}
	
	public void print() {
		for (V vertex: adjList.keySet()) {
			System.out.println(String.format("%s=%s", vertex, adjList.get(vertex)));
		}
	}
	
	/**
	 * 	Breadth first traversal of Graph.
	 */
	public List<V> bfs() {
		
		List<V> items = new LinkedList<>();
		
		Set<V> visited = new HashSet<>();
		
		Queue<V> q = new LinkedList<>();
				
		for (V v: this.adjList.keySet()) {		
			
			if (!visited.contains(v)) {
				
				q.offer(v);
									
				while (!q.isEmpty()) {
					
					V curr = q.poll();
					
					items.add(curr); // process curr node.
					visited.add(v); // mark it as visited.
					
					for (Edge<V,W> node: adjList.get(curr)) { // chk for all its neighbours.
						
						if (!visited.contains(node.vertex)) {							
						
							q.offer(node.vertex);					
						}
					}
				}				
			}			
		}			
		
		return items;
	}
		
	
	private static class Edge<V,W> {
		V vertex;
		W weight;
		public Edge(V vertex, W weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return String.format("%s(%s)", vertex, weight);
		}
	}
	
	public static void main(String[] args) {
		
		WeighedGraph<Integer, Integer> graph = new WeighedGraph<>();
		
		graph.addVertex(8);
		graph.addVertex(5);
		graph.addVertex(7);
		graph.addVertex(9);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(6);
				
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 3, 15);
		graph.addEdge(1, 4, 20);
		graph.addEdge(2, 3, 40);
		graph.addEdge(2, 4, 20);
		graph.addEdge(3, 4, 20);
		graph.addEdge(3, 5, 40);
		graph.addEdge(3, 6, 50);
		graph.addEdge(4, 1, 50);
		graph.addEdge(4, 2, 40);
		graph.addEdge(4, 3, 10);
		graph.addEdge(4, 5, 5);
		graph.addEdge(4, 6, 20);
		graph.addEdge(5, 2, 10);
		graph.addEdge(5, 3, 30);
		graph.addEdge(5, 7, 60);
		graph.addEdge(5, 8, 70);
		graph.addEdge(5, 9, 50);
		graph.addEdge(6, 5, 90);
		graph.addEdge(6, 8, 50);
		graph.addEdge(7, 1, 80);
		graph.addEdge(7, 2, 30);
		graph.addEdge(7, 3, 20);
		graph.addEdge(8, 3, 60);
		graph.addEdge(8, 4, 30);
		graph.addEdge(8, 5, 10);
		graph.addEdge(9, 5, 20);
								
		
		System.out.println(graph.bfs());
	}
	
}
