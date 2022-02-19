package my.learning.algorithms.tree.problems;

public class MaxDepthOfTree {

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
		
		BTNode n6 = new BTNode(4);
		n4.right = n6;
		
		BTNode n7 = new BTNode(5);
		n6.right = n7;
		
		System.out.println(findMaxDepth(n1));
		
		System.out.println(findMaxDepth(n1, 0));
	}
	
	
	
	/**
	 * 	2 ways to approach this problem.
	 * 
	 * 		1) dont pass depth to function, but return "+ 1" after the computing the max b/w left & right children.   
	 * 
	 *   	2) pass the curr node depth to the function so that it can be added and returned.
	 */	
	private static int findMaxDepth(BTNode curr) {		
		
		if (curr == null)
			return 0;
		
		return 1 + Integer.max(findMaxDepth(curr.left), findMaxDepth(curr.right));
	}
	
	private static int findMaxDepth(BTNode curr, int currDepth) {		
		
		if (curr == null)
			return currDepth;
		
		currDepth++;
		
		return Integer.max(findMaxDepth(curr.left, currDepth), findMaxDepth(curr.right, currDepth));
	}
	
	static class BTNode {
		int data;
		BTNode left, right;
		public BTNode(int data) {
			this.data = data;
		}
	}
	
}
