package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.List;

public class BSTprintAllPaths {

	
	static List<List<Integer>> findPaths(TreeNode root) {		
		List<List<Integer>> allPaths = new ArrayList<>();				
		findPaths(root, new ArrayList<>(), allPaths);		
		return allPaths;
	}
	
	
	/**
	 * 	dfs - backtracking.
	 */
	static void findPaths(TreeNode curr, List<Integer> runningPath, List<List<Integer>> allPaths) {		
		
		if (curr == null) 
			return;
		
		runningPath.add(curr.val);
		
		if (curr.left == null && curr.right == null) {
			allPaths.add(new ArrayList<>(runningPath));			
			return;
		} 
		
		if (curr.left != null) {
			findPaths(curr.left, runningPath, allPaths);
			runningPath.remove(runningPath.size() - 1);	
		}
		
		if (curr.right != null) {
			findPaths(curr.right, runningPath, allPaths);
			runningPath.remove(runningPath.size() - 1);	
		}
	}
	
	public static void main(String[] args) {
	
		TreeNode root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    
	    List<List<Integer>> result = findPaths(root);
	    System.out.println("Tree paths : " + result);
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
}
