package my.learning.algorithms.tree.problems;



public class IsValidBST {
	
	public static void main(String[] args) {
	
		TreeNode a = new TreeNode(7);
		TreeNode b = new TreeNode(4);
		TreeNode c = new TreeNode(9);
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(6);
		TreeNode f = new TreeNode(8);
		TreeNode g = new TreeNode(10);
		TreeNode h = new TreeNode(20);
		
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		d.left = h;		
		
		IsValidBST bstChecker = new IsValidBST();
		System.out.println(bstChecker.isValidBST(a));	
				
		System.out.println(bstChecker.optimalIsBST(a));
	}	
	
	
	/**
	 * 	Brute force. time complexity: O(n^2).
	 */
	private boolean isValidBST(TreeNode curr) {
		if (curr == null) return true;
		return isLeftSubtreeLesser(curr.left, curr.data) && isValidBST(curr.left)
			&& isRightSubtreeGreater(curr.right, curr.data) && isValidBST(curr.right);
	}
	
	private boolean isLeftSubtreeLesser(TreeNode curr, int parentVal) {
		if (curr == null) return true;		
		return curr.data < parentVal
			&& isLeftSubtreeLesser(curr.left, parentVal)
			&& isLeftSubtreeLesser(curr.right, parentVal);
	}

	private boolean isRightSubtreeGreater(TreeNode curr, int parentVal) {
		if (curr == null) return true;
		return curr.data > parentVal
			&& isRightSubtreeGreater(curr.left, parentVal)
			&& isRightSubtreeGreater(curr.right, parentVal);
	}
	
	
	
	/**
	 * 	Optimal solution. 
	 * 	time complexity: O(n).
	 */
	private boolean optimalIsBST(TreeNode root) {
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}	

	private boolean isValidBST(TreeNode curr, int minRange, int maxRange) {
		if (curr == null) return true;
		return curr.data > minRange
			&& curr.data < maxRange 
			&& isValidBST(curr.left, minRange, curr.data)
			&& isValidBST(curr.right, curr.data, maxRange);
	}	
	
	
	
	
	static class TreeNode {
		int data;
		TreeNode left, right;
		public TreeNode(int data) {
			this.data = data;
		}
	}
	
}
