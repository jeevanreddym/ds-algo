package my.learning.algorithms.tree.problems;

import my.learning.algorithms.tree.problems.BSTpathSum.TreeNode;

public class InvertBinaryTree {

	
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		
		invertTree(root);
		printTree(root);
	}

	/**
	 * 	dfs().
	 */
	private static void invertTree(TreeNode curr) {
		if (curr == null) {
			return;
		}
		
		TreeNode temp = curr.left;
		curr.left = curr.right;
		curr.right = temp;
		
		invertTree(curr.left);
		invertTree(curr.right);
	}
	
	/**
	 *	preorder dfs. 
	 */
	private static void printTree(TreeNode curr) {
		if (curr == null) {
			return;
		}
		
		System.out.print(curr.val + ",");
		
		printTree(curr.left);
		printTree(curr.right);
	}

	
}
