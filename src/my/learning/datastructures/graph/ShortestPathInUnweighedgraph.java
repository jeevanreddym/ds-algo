package my.learning.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;



/**
 * 	To find shortest path in an unweighed graph we can use the BFS algorithm.
 *
 */
public class ShortestPathInUnweighedgraph {

	
	private static class Node {
		public String name;
		public List<Node> neighbours = new ArrayList<>();
		public int minDist = Integer.MAX_VALUE;
		public Node predecessor;
		
		public Node(String name) {
			this.name = name;
		}
		
		public void addNeighbour(Node neighbour) {
			this.neighbours.add(neighbour);
		}
	}
	
	private static class UnweighedGraph {
		
		public Map<String, Node> vertices = new HashMap<>();
		
		public void addVertex(Node station) {
			this.vertices.put(station.name, station);			
		}
		
		public List<String> shortestPath(String from, String to) {
			
			Queue<Node> q = new LinkedList<>();
			Node source = this.vertices.get(from);
			source.minDist = 0;
			q.offer(source);
			
			while (!q.isEmpty()) { // bfs.
				
				Node curr = q.poll();
				
				for (Node neighbour: curr.neighbours) {
					
					if (neighbour.minDist > curr.minDist + 1) {
												
						neighbour.minDist = curr.minDist + 1;
						neighbour.predecessor = curr;
						
						q.offer(neighbour);
					}
				}				
			}
			
			List<String> path = new ArrayList<>();
			Node target = this.vertices.get(to);
			while (target != null) {
				path.add(target.name);
				target = target.predecessor;
			}
			Collections.reverse(path);			
			return path;
		}
	}
	
	public static void main(String[] args) {
		
		Node a = new Node("A");		
		Node b = new Node("B");		
		Node c = new Node("C");		
		Node d = new Node("D");		
		Node e = new Node("E");		
		Node f = new Node("F");		
		Node g = new Node("G");		
		Node h = new Node("H");		
		Node i = new Node("I");		
				
		
		a.addNeighbour(b);
		a.addNeighbour(c);
		a.addNeighbour(d);
		
		b.addNeighbour(f);
		
		c.addNeighbour(a);
		c.addNeighbour(g);
		
		d.addNeighbour(a);
		d.addNeighbour(e);
		
		e.addNeighbour(d);
		e.addNeighbour(i);
		
		f.addNeighbour(b);
		f.addNeighbour(g);
		
		g.addNeighbour(c);
		g.addNeighbour(f);
		g.addNeighbour(h);
		g.addNeighbour(i);
		
		h.addNeighbour(g);
		
		
		
		UnweighedGraph unweighedGraph = new UnweighedGraph();
		unweighedGraph.addVertex(a);
		unweighedGraph.addVertex(b);
		unweighedGraph.addVertex(c);
		unweighedGraph.addVertex(d);
		unweighedGraph.addVertex(e);
		unweighedGraph.addVertex(f);
		unweighedGraph.addVertex(g);
		unweighedGraph.addVertex(h);
		unweighedGraph.addVertex(i);
		
		List<String> shortestPath = unweighedGraph.shortestPath("A", "G");
		System.out.println(Arrays.toString(shortestPath.toArray()));		
	}
	
}
