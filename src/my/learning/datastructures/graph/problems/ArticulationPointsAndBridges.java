package my.learning.datastructures.graph.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ArticulationPointsAndBridges {

        
    public static void main(String[] args) {
    	List<List<Integer>> connections = Arrays.asList(
			Arrays.asList(1,2),
			Arrays.asList(1,3),
			Arrays.asList(3,4),
			Arrays.asList(1,4),
			Arrays.asList(4,0)
		);
    	System.out.println(findCriticalConnections(connections));
	}


    public static List<List<Integer>> findCriticalConnections(List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        Graph graph = new Graph(connections.size());
        for (List<Integer> connection : connections) {
            graph.addConnection(connection.get(0), connection.get(1));
        }
        System.out.println(graph.connections);
        for (List<Integer> connection : connections) {
            graph.removeConnection(connection.get(0), connection.get(1));
            int numberOfConnectedComponents = getConnectedComponents(graph, connections.size());
            System.out.println("Number of components: " + numberOfConnectedComponents);
            if (numberOfConnectedComponents > 1)
                result.add(connection);
            graph.addConnection(connection.get(0), connection.get(1));
        }
        return result;
    }

    
	/**
	 * 	chk if all vertices in the graph are connected using bfs traversal.
	 */
	private static int getConnectedComponents(Graph graph, int n) {
		
		int numComponents = 0;
		
		boolean[] visited = new boolean[n];
		
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
		
			if (!visited[i]) {
				q.add(i);
				numComponents++;
			}
			
			while (!q.isEmpty()) {
			
				Integer node = q.poll();
				
				if (!visited[node]) {
				
					Set<Integer> sets = graph.connections[node];
					for (int data : sets) {
						if (!visited[data])
							q.add(data);
					}
				}
				
				visited[node] = true;
			}
		}
		return numComponents;
	}
	
	
	/**
	 * 	Graph Data Structure.
	 */
	private static class Graph {        
		
		/**
         * This set will be working as adjacency list.
         */
        Set<Integer>[] connections;

        /**
         * Create a graph data structure
         */
        @SuppressWarnings("unchecked")
		Graph(int noOfVertices) {
            this.connections = new HashSet[noOfVertices];
            for (int i = 0; i < noOfVertices; i++) {
            	this.connections[i] = new HashSet<>();
            }
        }

        void addConnection(int u, int v) {
        	this.connections[u].add(v);
        	this.connections[v].add(u);
        }

        void removeConnection(int u, int v) {
        	this.connections[u].remove(v);
        	this.connections[v].remove(u);
        }
    }
        
}