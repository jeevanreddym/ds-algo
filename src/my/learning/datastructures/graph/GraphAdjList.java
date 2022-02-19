package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjList<T> {
	
	private List<Vertex<T>> vertices;
	
	public GraphAdjList() {	
		this.vertices = new ArrayList<>();		
	}
	
	
	
	public void addVertex(Vertex<T> v) {
		this.vertices.add(v);
	}	
	
	static class Vertex<T> {
		
		T data;
		List<Vertex<T>> neighbours;
		boolean visited;
		
		public Vertex(T data) {
			this.data = data;
			this.neighbours = new LinkedList<>();
		}
		
		public void addNeighbour(Vertex<T> v) {
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
			@SuppressWarnings("unchecked")
			Vertex<T> other = (Vertex<T>) obj;
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
	 * 	Breadth first traversal of Graph.
	 */
	public List<Vertex<T>> bft() {
		
		List<Vertex<T>> items = new LinkedList<>();
		
		Queue<Vertex<T>> q = new LinkedList<>();
				
		for (Vertex<T> v: this.vertices) {		
			
			if (!v.visited) {
				
				q.offer(v);	
				
				while (!q.isEmpty()) { // chk if q is not empty.
					
					Vertex<T> curr = q.poll(); // pull the next element in Q.
										
					items.add(curr);	// process the node & then mark it as visited.
					v.visited = true;
					
					for (Vertex<T> neighbour: curr.neighbours) { // check for the elements neighbors & enQ those which r not visited already. 
						
						if (!neighbour.visited) {
						
							q.offer(neighbour);					
						}
					}
				}				
			}			
		}		
		
		return items;
	}
	

	/**
	 * 	Depth first traversal of Graph (Iterative).
	 */
	public List<Vertex<T>> dfsIterative() {
		
		List<Vertex<T>> items = new LinkedList<>();
		
		Stack<Vertex<T>> stack = new Stack<>();
				
		for (Vertex<T> v: this.vertices) {		
			
			if (!v.visited) {
				
				v.visited = true;
				stack.push(v);	
				
				while (!stack.isEmpty()) {
					
					Vertex<T> curr = stack.pop();
					items.add(curr);
					
					for (Vertex<T> neighbour: curr.neighbours) {
						if (!neighbour.visited) {
							neighbour.visited = true;
							stack.push(neighbour);					
						}
					}
				}
				
			}			
		}				
		return items;
	}
	
	
	
	/**
	 * 	Depth first traversal of Graph.
	 */
	public List<Vertex<T>> dft(Vertex<T> source) {		
		
		List<Vertex<T>> items = new LinkedList<>();		
		
		for (Vertex<T> v: this.vertices) { // check each node sequentially.
			
			if (!v.visited) { // if not visited (processed) already, then do a dfs from that node. 
				
				dft(v, items);
			}			
		}
		return items;
	}
	
	private void dft(Vertex<T> curr, List<Vertex<T>> items) {		
								
		items.add(curr); // process the node.
		curr.visited = true; // mark it as visited.
		
		for (Vertex<T> neighbour: curr.neighbours) {  // check for the nodes neighbors & run a dfs from the nodes which r not visited. 
			
			if (!neighbour.visited) {				
			
				dft(neighbour, items);			
			}
		}
	}
	
	
	/**
	 *	Topological Sort/Ordering of a DAG : using DFT technique.
	 */
	public List<T> topologicalSort() {		
		
		Stack<Vertex<T>> stack = new Stack<>();		
		
		for (Vertex<T> v: this.vertices) {			
		
			if (!v.visited) {
				
				topologicalSort(v, stack);
			}
		}
		
		List<T> items = new ArrayList<>(stack.size());
		while (!stack.isEmpty())
			items.add(stack.pop().data); 		
		return items;
	}
	
	private void topologicalSort(Vertex<T> curr, Stack<Vertex<T>> stack) {
		
		curr.visited = true;
		
		for (Vertex<T> neighbour: curr.neighbours) {
			
			if (!neighbour.visited) {
				
				topologicalSort(neighbour, stack);
			}					
		}
		
		stack.push(curr);		
	}
		
	
	public static void main(String[] args) {
		
		GraphAdjList<String> graph = new GraphAdjList<>();
		
		Vertex<String> a = new Vertex<>("A");
		Vertex<String> b = new Vertex<>("B");
		Vertex<String> c = new Vertex<>("C");
		Vertex<String> d = new Vertex<>("D");
		Vertex<String> e = new Vertex<>("E");
		Vertex<String> f = new Vertex<>("F");
		Vertex<String> g = new Vertex<>("G");
		Vertex<String> h = new Vertex<>("H");
		Vertex<String> i = new Vertex<>("I");
		
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
		
		
		//System.out.println(graph.bft(a));
		System.out.println(graph.dft(a));
		//System.out.println(graph.dfsIterative());
	}
	
}
