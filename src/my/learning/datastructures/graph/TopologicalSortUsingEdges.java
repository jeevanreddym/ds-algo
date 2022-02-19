package my.learning.datastructures.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TopologicalSortUsingEdges {

	
	public static void main(String[] args) {
				
		List<List<Character>> connections = Arrays.asList(
				
			Arrays.asList('1','0'), // 1 depends on 0. 0 -> 1
			Arrays.asList('2','1'), // 2 depends on 1. 1 -> 2
			Arrays.asList('2','5'),
			Arrays.asList('0','3'),
			Arrays.asList('4','3'),
			Arrays.asList('3','5'),
			Arrays.asList('4','5')
			
//			,
//			Arrays.asList('8','6'),
//			Arrays.asList('6','7'),
//			Arrays.asList('7','8')
		);
		
		List<Character> order = topologicalSort(connections);
		System.out.println(Arrays.toString(order.toArray()));
	}

	private static List<Character> topologicalSort(List<List<Character>> connections) {
		
		List<Character> order = new LinkedList<>(); // result ordered list.
		
		
		
		// preparing the adjacency list (each vertex & its neighbours)
		
		Map<Character, Set<Character>> vertices = new LinkedHashMap<>();		
		connections.forEach(edge -> {
			
			Set<Character> neighbours = vertices.containsKey(edge.get(0))? vertices.get(edge.get(0)): new LinkedHashSet<>();
			vertices.put(edge.get(0), neighbours);
			
			Set<Character> neighbours2 = vertices.containsKey(edge.get(1))? vertices.get(edge.get(1)): new LinkedHashSet<>();
			neighbours2.add(edge.get(0));
			vertices.put(edge.get(1), neighbours2);			
			
		});
		
		
		Queue<Character> q = new LinkedList<>(); // Q for doing a bfs. 
		
		
		// compute the indegree of each vertex in the graph (adjacency list representation).
		Map<Character, Integer> indegrees = new HashMap<>();
		for (Character vertex: vertices.keySet()) {
			
			int indegree = getIndegree(vertex, vertices);
			
			indegrees.put(vertex, indegree);
			
			if (indegree == 0) { // enqueue nodes which have 0 indegree, as these nodes are the ones that have highest dependency.
				
				q.offer(vertex);
			}			
		}
		
		
		// perform bfs taking each vertex enqued.
		
		while (!q.isEmpty()) {
			
			Character curr = q.poll();
			
			order.add(curr); // add the vertex to the ordered o/p.
			
			
			for (Character neighbour: vertices.get(curr)) { // chk for next vertices to be enqued after curr vertex has been processed.
				
				int updatedIndegree = indegrees.get(neighbour) - 1; // since curr node is processed so reduce the indegree of its neighbours.
				
				indegrees.put(neighbour, updatedIndegree);
				
				if (updatedIndegree == 0) { // enQ vertices with update indegree = 0.
					
					q.offer(neighbour);
				}
			}			
		}
		
		
		if (order.size() != vertices.size()) {			
			throw new RuntimeException("this graph has a cycle.");
		}
				
		return order;
	}

	private static int getIndegree(Character vertex, Map<Character, Set<Character>> vertices) {
		return (int) vertices.entrySet().stream().filter(e -> e.getKey() != vertex? e.getValue().contains(vertex): false).count();
	}
		
}
