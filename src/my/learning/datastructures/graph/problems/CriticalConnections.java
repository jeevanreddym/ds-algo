package my.learning.datastructures.graph.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalConnections {
	
	public static void main(String[] args) {
		
		CriticalConnections c = new CriticalConnections();
		
		int n = 5;
		List<List<Integer>> edges = Arrays.asList(
			Arrays.asList(1,2),
			Arrays.asList(1,3),
			Arrays.asList(3,4),
			Arrays.asList(1,4),
			Arrays.asList(4,5)
		);
		
		List<List<Integer>> criticalConnections = c.findCriticalConnections(n, edges);
		criticalConnections.forEach(edge -> System.out.println(edge.get(0) + "->" + edge.get(1)));		
	}

	/**
	 * 	findCriticalConnections().
	 */
	public List<List<Integer>> findCriticalConnections(int n, List<List<Integer>> connections) {

		List<List<Integer>> criticalConnections = new ArrayList<>();
		
		
		
		Map<Integer, Set<Integer>> graph = constructGraph(connections);
		
		// loop through input connections.
		for (List<Integer> connection: connections) {
			
			// remove this connection from graph.			
			int from = connection.get(0);
			int to = connection.get(1);

			
			// remove the edge and reverse edge from graph.
			if (graph.containsKey(from)) {
				Set<Integer> fromNeighbors = graph.get(from);
				fromNeighbors.remove(to);
			}
			if (graph.containsKey(to)) {
				Set<Integer> toNeighbors = graph.get(to);
				toNeighbors.remove(from);
			}
			

			
			
			// check if the graph is still connected.
			boolean isConnected = isGraphConnected(n, graph);
			if (!isConnected) {
				criticalConnections.add(connection); // if not, mark the connection as critical.
			}

			// add the edge and reverse edge back to graph -- start
			if (graph.containsKey(from)) {
				Set<Integer> fromNeighbors = graph.get(from);
				fromNeighbors.add(to);
			}

			if (graph.containsKey(to)) {
				Set<Integer> fromNeighbors = graph.get(to);
				fromNeighbors.add(from);
			}

			// add the edge and reverse edge back to graph -- end
		}
		
		return criticalConnections;
	}

	/**
	 * 	chk if all vertices in the graph are connected using dfs traversal.
	 */
	private boolean isGraphConnected(int n, Map<Integer, Set<Integer>> graph) {

		boolean[] marked = new boolean[n];
		
		dfs(0, graph, marked);
		
		for (int i = 0; i < n; i++) {
			if (!marked[i]) {
				return false;
			}
		}
		return true;
	}

	private void dfs(int src, Map<Integer, Set<Integer>> graph, boolean[] marked) {
		
		marked[src] = true;
		
		Set<Integer> neighbors = graph.get(src);
		if (neighbors != null) {
			for (Integer neighbor : neighbors) {
				if (!marked[neighbor]) {
					dfs(neighbor, graph, marked);
				}
			}
		}
	}
	
	/**
	 * 	construct graph from List<List>
	 */
	private Map<Integer, Set<Integer>> constructGraph(List<List<Integer>> connections) {
		
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		
		for (List<Integer> connection : connections) {
		
			int from = connection.get(0);
			int to = connection.get(1);

			
			// update from neighbors.
			if (!graph.containsKey(from)) {
				graph.put(from, new HashSet<>());
			}
			Set<Integer> fromNeighbors = graph.get(from);
			fromNeighbors.add(to);
			graph.put(from, fromNeighbors);

			
			// update to neighbors.
			if (!graph.containsKey(to)) {
				graph.put(to, new HashSet<>());
			}			
			Set<Integer> toNeighbors = graph.get(to);
			toNeighbors.add(from);
			graph.put(to, toNeighbors);
		}
		
		return graph;
	}

}
