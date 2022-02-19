package my.learning.algorithms.tree.problems;

public class BSTpathSum {

	
	public static void main(String[] args) {
	
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		
		System.out.println("Tree has path: " + 	hasPathWithSum(root, 23, 0));
		System.out.println("Tree has path: " + hasPathWithSum(root, 16, 0));
	}

	private static boolean hasPathWithSum(TreeNode curr, int targetSum, int runningSum) {
		if (curr == null) return false;
			
		runningSum += curr.val;
			
		if (runningSum == targetSum && curr.left == null && curr.right == null) {
			return true;
		} 
		
		boolean hasLeftPath = false;
		if (runningSum < targetSum && curr.left != null) {
			hasLeftPath = hasPathWithSum(curr.left, targetSum, runningSum);
		}
		
		boolean hasRightPath = false;
		if (runningSum < targetSum && curr.right != null) {
			hasRightPath = hasPathWithSum(curr.right, targetSum, runningSum);
		}
		
		return (hasLeftPath || hasRightPath);		
//		return hasPathWithSum(curr.left, targetSum, curr.val + runningSum) 
//				|| hasPathWithSum(curr.right, targetSum, curr.val + runningSum);
	}
		
	static boolean hasPathWithSum1(TreeNode curr, int runningSum) {
		if (curr == null) return false;
			
		runningSum -= curr.val;
			
		if (runningSum == 0 && curr.left == null && curr.right == null) {
			return true;
		} 
		
		boolean hasLeftPath = false;
		if (runningSum > 0 && curr.left != null) {
			hasLeftPath = hasPathWithSum1(curr.left, runningSum);
		}
		
		boolean hasRightPath = false;
		if (runningSum > 0 && curr.right != null) {
			hasRightPath = hasPathWithSum1(curr.right, runningSum);
		}
		
		return (hasLeftPath || hasRightPath);
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) {
			this.val = x;
		}
	};
	
}
