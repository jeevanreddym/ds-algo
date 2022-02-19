package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderAvgOfTree {
	
	
	/**
	 * 	Iterative.
	 */
	public static List<Double> findLevelAverages(TreeNode root) {
		
		List<Double> result = new LinkedList<>();

		List<Double[]> levelByLevelTotals = new ArrayList<>();
		
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		
		levelByLevelTotals.add(new Double[] { 0.0d, 0.0d });

		while (!q.isEmpty()) {
			
			TreeNode curr = q.poll();

			if (curr == null) {
				if (!q.isEmpty()) {
					levelByLevelTotals.add(new Double[] { 0.0d, 0.0d });
					q.offer(null);
					continue;
				} else {
					break;
				}
			} else {
				int currLevel = levelByLevelTotals.size() - 1;
				Double[] tuple = levelByLevelTotals.get(currLevel);
				tuple[0] += curr.val;
				tuple[1]++;
				levelByLevelTotals.remove(currLevel);
				levelByLevelTotals.add(currLevel, tuple);
			}

			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
		}

		for (Double[] levelTuple : levelByLevelTotals) {
			result.add(levelTuple[0] / levelTuple[1]);
		}

		return result;
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int x) {
			val = x;
		}
	};

	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		List<Double> result = findLevelAverages(root);
		System.out.print("Level averages are: " + result);
	}
	
}