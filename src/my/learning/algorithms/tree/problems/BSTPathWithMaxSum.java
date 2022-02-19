package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import my.learning.algorithms.tree.problems.BSTprintAllPaths.TreeNode;

/**
 * 	Max sum path from root to leaf.
 */
public class BSTPathWithMaxSum {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(9);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(16);
	    
	    List<Integer> maxSumPath = findPathwithMaxSum(root);
	    System.out.println("Tree paths : " + Arrays.toString(maxSumPath.toArray()));
	}

	private static List<Integer> findPathwithMaxSum(TreeNode curr) {
		List<PathWithSum> maxSumPath = new LinkedList<>();
		int maxSum = findPathwithMaxSum(curr, new LinkedList<>(), 0, maxSumPath);
		System.out.println(String.format("max sum: %s", maxSum));
		return maxSumPath.get(0).getPath();
	}

	private static int findPathwithMaxSum(TreeNode curr, List<Integer> runningPath, int runningSum, List<PathWithSum> maxSumPath) {
		
		if (curr == null) return 0;
		
		runningSum += curr.val;
		runningPath.add(curr.val);
		
		if (curr.left == null && curr.right == null) {
			if (maxSumPath.size() == 0 || maxSumPath.get(0).getPathSum() < runningSum) {		
				maxSumPath.clear();
				maxSumPath.add(new PathWithSum(runningPath, runningSum));
			}			
			return runningSum;
		}
		
		int leftPathSum = 0;
		//if (curr.left != null) {
			leftPathSum = findPathwithMaxSum(curr.left, runningPath, runningSum, maxSumPath);
			
			if (curr.left != null) {
				runningPath.remove(runningPath.size() - 1); // backtracking (to remove recently visited node from path).	
			}
			
			
		//}
		
		int rightPathSum = 0;
		//if (curr.right != null) {
			rightPathSum = findPathwithMaxSum(curr.right, runningPath, runningSum, maxSumPath);
			
			
			if (curr.right != null) {
				runningPath.remove(runningPath.size() - 1); // backtracking (to remove recently visited node from path).	
			}
			
			//runningPath.remove(runningPath.size() - 1); // backtracking (to remove recently visited node from path).
		//}		
		
		return Integer.max(leftPathSum, rightPathSum);
	}
	
	


	static class PathWithSum {
		List<Integer> path;
		Integer pathSum;
		public PathWithSum(List<Integer> path, Integer pathSum) {
			this.path = new ArrayList<>(path);
			this.pathSum = pathSum;
		}
		List<Integer> getPath() {
			return path;
		}
		Integer getPathSum() {
			return pathSum;
		}
	}
	
}



