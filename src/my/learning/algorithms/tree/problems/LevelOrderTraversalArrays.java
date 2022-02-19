package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import my.learning.algorithms.tree.problems.MaxDepthOfTree.BTNode;

public class LevelOrderTraversalArrays {

	public static void main(String[] args) {
		
		BTNode n1 = new BTNode(1);
		
		BTNode n2 = new BTNode(2);
		n1.left = n2;
		
		BTNode n3 = new BTNode(3);
		n1.right = n3;
		
		BTNode n4 = new BTNode(4);
		n2.left = n4;
		
		BTNode n5 = new BTNode(5);
		n2.right = n5;
		
		BTNode n6 = new BTNode(6);
		n4.right = n6;
		
		BTNode n7 = new BTNode(7);
		n6.right = n7;
		
		System.out.println("Using BFS.");
		levelOrderData(n1).forEach(levelElements -> System.out.println(Arrays.toString(levelElements.toArray())));
		
		System.out.println("Using DFS.");
		levelOrderDataDFS(n1).forEach(levelElements -> System.out.println(Arrays.toString(levelElements.toArray())));
	}
	
	/**
	 * 	Level by level elements as individual arrays.
	 */
	private static List<List<Integer>> levelOrderData(BTNode root) {
		
		List<List<Integer>> container = new LinkedList<>();
		
		Queue<BTNode> q = new LinkedList<>();
		
		q.add(root);
		
		while (!q.isEmpty()) {
			
			int currLevelSize = q.size();
			
			List<Integer> levelElements = new ArrayList<>(currLevelSize);
			for (int i = 1; i <= currLevelSize; i++) {
				
				BTNode curr = q.poll();
				
				if (curr.left != null) {
					q.offer(curr.left);
				}
				
				if (curr.right != null) {
					q.offer(curr.right);
				}
				
				levelElements.add(curr.data);
			}
			
			container.add(levelElements);
		}
		
		return container;
	}
	
	
	/**
	 * 	Using DFS.
	 */
	private static List<List<Integer>> levelOrderDataDFS(BTNode root) {		
		List<List<Integer>> result = new LinkedList<>();
		dfs(root, 0, result);
		return result;
	}
	
	private static void dfs(BTNode currNode, int currLvl, List<List<Integer>> results) {
		if (currNode == null) 
			return;
		
		if (results.size() < currLvl + 1) {
			results.add(new LinkedList<>());
		}
		
		List<Integer> levelElements = results.get(currLvl);
		levelElements.add(currNode.data);
		
		dfs(currNode.left, currLvl + 1, results);
		
		dfs(currNode.right, currLvl + 1, results);		
	}
	
}
