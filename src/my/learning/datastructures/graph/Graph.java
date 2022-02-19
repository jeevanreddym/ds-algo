package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<V> {
	
	private Map<V, List<V>> adjList;
	
	public Graph() {
		this.adjList = new HashMap<>();
	}
		
	public void addVertex(V vertex) {		
		if (adjList.get(vertex) == null)
			adjList.put(vertex, new ArrayList<>());		
	}
	
	public void addEdge(V v1, V v2) {		
		adjList.get(v1).add(v2);		
		adjList.get(v2).add(v1);
	}
	
	public void print() {
		for (V vertex: adjList.keySet()) {
			System.out.println(String.format("%s=%s", vertex, adjList.get(vertex)));
		}
	}
	
	public Set<V> bft(V start) {
		
		Set<V> visited = new LinkedHashSet<>();
		
		Queue<V> q = new LinkedList<>();
		
		q.offer(start);
				
		while (!q.isEmpty()) {
		
			V vertex = q.poll();
			visited.add(start);
			
			for (V adjVertex: adjList.get(vertex)) {
			
				if (!visited.contains(adjVertex)) {
		
					q.offer(adjVertex);
				}
			}
		}	
		return visited;
	}
	
	/**
	 * 	Depth first traversal, recursive.
	 */
	public Set<V> dft(V start) {		
		
		Set<V> visited = new LinkedHashSet<>();
		
		for (V vertex: adjList.keySet()) {
			
			if (!visited.contains(vertex)) {
			
				dft(adjList, start, visited);
			}			
		}
		return visited;
	}	
	
	private void dft(Map<V, List<V>> adjList, V v, Set<V> visited) {
		
		visited.add(v);
		
		for (V adjVertex: adjList.get(v)) {
		
			if (!visited.contains(adjVertex)) {				
			
				dft(adjList, adjVertex, visited);
			}
		}
	}
	
	public Set<V> dftIterative(V start) {
		
		Set<V> visited = new LinkedHashSet<>();
		
		Stack<V> s = new Stack<>();
		
		s.push(start);
		visited.add(start);
		
		while (!s.isEmpty()) {
		
			V vertex = s.pop();			
			
			if (!visited.contains(vertex)) {
				
				visited.add(vertex);
				
				for (V adjVertex: adjList.get(vertex)) {
					s.push(adjVertex);
				}
			}			
		}		
		return visited;
	}
	
	public boolean hasEdge(V source, V destination) {
	    return adjList.containsKey(source) && adjList.get(source).contains(destination);
	}
	
	public static void main(String[] args) {
		
		Graph<Integer> graph = new Graph<>();
		
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(6);
		graph.addVertex(8);
		graph.addVertex(5);
		graph.addVertex(7);
		graph.addVertex(9);
		
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
		
				
		System.out.println("BFT: " + Arrays.toString(graph.bft(1).toArray()));
		
		System.out.println("DFT: " + Arrays.toString(graph.dft(1).toArray()));
	}
	
}
