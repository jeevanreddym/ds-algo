package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;



public class TopologicalSort {
	
	private List<Vertex> vertices;
	
	public TopologicalSort() {	
		this.vertices = new ArrayList<>();		
	}
	
	public void addVertex(Vertex v) {
		this.vertices.add(v);
	}	
	
	private static class Vertex {
		
		String data;
		Set<Vertex> neighbours;
		 		
		public Vertex(String data) {
			this.data = data;
			this.neighbours = new HashSet<> ();
		}
		
		public void addNeighbour(Vertex v) {
			this.neighbours.add(v);
		}
				
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			Vertex other = (Vertex) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return this.data.toString();
		}
	}
	
	
	
	
	

	
	/**
	 *	Topological Sort/Ordering of a DAG : using bsf technique.
	 */
	public List<String> topologicalSort() {		
		
		List<String> topologicallySorted = new LinkedList<>();
		
		Queue<Vertex> q = new LinkedList<>();
				
		// preparing indegree for each vertex in the graph.
		Map<String, Integer> indegrees = new HashMap<>();
		for (Vertex v: this.vertices) {			
		
			int indegree = getIndegree(v);
			
			indegrees.put(v.data, indegree);
			
			if (indegree == 0) { // add vertices with 0 indegree to Q.
				q.offer(v);
			}
		}
		
		
		
		while (!q.isEmpty()) {
			
			Vertex curr = q.poll(); // get the next node from Q.
			
			topologicallySorted.add(curr.data); // process the node.
			
			for (Vertex neighbour: curr.neighbours) { // now reduce all the indegrees of neighbours of curr node by 1 as we have processed this node. 
				
				int updatedIndegree = indegrees.get(neighbour.data) - 1;				
				indegrees.put(neighbour.data, updatedIndegree);
				
				if (updatedIndegree == 0) { // add vertices with 0 updated indegree to Q to be processed next.
					q.offer(neighbour);
				}				
			}			
		}
		
		if (topologicallySorted.size() != this.vertices.size()) {
			throw new RuntimeException("this graph has a cycle.");
		}		
		
		return topologicallySorted;
	}
	
	
	
	/**
	 * 	get the indegree value for that vertex at that point of time.
	 */
	private int getIndegree(Vertex vertex) {		
		Vertex currSource = this.vertices.stream().filter(v -> v.data.equals(vertex.data)).findAny().get();
		if (currSource != null) {
			return (int) this.vertices.stream().filter(v -> !v.data.equals(currSource.data)? 
				vertex.neighbours.contains(currSource): false).count();
		}
		return 0;
	}
	
	
	
	
	/**
	 * 	This current implementation is wrong. Jeevan correct this.
	 * 
	 *	Topological Sort/Ordering of a DAG : using dfs technique.
	 */
	public List<String> topologicalSortDFS() {		
				
		List<String> sortedItems = new LinkedList<>();
		
		Vertex zeroIndegreeVertex = null;
		
		// preparing indegree for each vertex in the graph.
		Map<Vertex, Integer> indegrees = new HashMap<>();		
		for (Vertex v: this.vertices) {			
			
			int indegree = getIndegree(v);
			
			indegrees.put(v, indegree);
			
			if (indegree == 0 && zeroIndegreeVertex == null) {				
				zeroIndegreeVertex = v;	
			}
		}	
				
		if (zeroIndegreeVertex != null) {
			
			dfs(zeroIndegreeVertex, indegrees, sortedItems);	
		}
		
		if (sortedItems.size() != this.vertices.size()) {
			throw new RuntimeException("this graph has a cycle.");
		}		
		
		return sortedItems;
	}
	
	private void dfs(Vertex curr, Map<Vertex, Integer> indegrees, List<String> sortedItems) {
		
		sortedItems.add(curr.data); // process the node.
		
		for (Vertex neighbour: curr.neighbours) {
			
			int updatedIndegree = indegrees.get(neighbour) - 1;			
			
			indegrees.put(neighbour, updatedIndegree);
			
			if (updatedIndegree == 0) {				
				
				dfs(neighbour, indegrees, sortedItems);
			}				
		}				
	}
	
	
	
	
	public static void main(String[] args) {
			
		TopologicalSort graph = new TopologicalSort();
		
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B"); 		
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		Vertex g = new Vertex("G");
		Vertex h = new Vertex("H");
		Vertex i = new Vertex("I");
		
		a.addNeighbour(b);
		a.addNeighbour(c);
		a.addNeighbour(d);
		
		b.addNeighbour(d);
		b.addNeighbour(e);
		
		c.addNeighbour(e);
		
		d.addNeighbour(c);
		
		
		f.addNeighbour(g);
		f.addNeighbour(h);
		f.addNeighbour(i);
		
		g.addNeighbour(h);
		g.addNeighbour(i);
		
		h.addNeighbour(i);
		
				
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addVertex(g);
		graph.addVertex(h);
		graph.addVertex(i);
				
		
		System.out.println(Arrays.toString(graph.topologicalSort().toArray()));	
		
		//System.out.println(Arrays.toString(graph.topologicalSortDFS().toArray()));	
	}
	
}
