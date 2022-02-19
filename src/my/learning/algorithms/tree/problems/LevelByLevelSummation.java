package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LevelByLevelSummation {

	
	public static void main(String[] args) {
		
		BTNode<Integer> a = new BTNode<>(1);
		BTNode<Integer> b = new BTNode<>(2);
		BTNode<Integer> c = new BTNode<>(3);
		BTNode<Integer> d = new BTNode<>(4);
		BTNode<Integer> e = new BTNode<>(5);
		BTNode<Integer> f = new BTNode<>(6);
		BTNode<Integer> g = new BTNode<>(7);
		BTNode<Integer> h = new BTNode<>(8);
		BTNode<Integer> i = new BTNode<>(9);
		BTNode<Integer> j = new BTNode<>(10);
		
		a.left = b;
		a.right = c;		
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		d.left = h;
		d.right = i;
		e.left = j;
		
		
		
		
		iterative(a);
		
		recursive(a);
	}
	
	/**
	 * 	iterative.
	 */
	static void iterative(BTNode<Integer> source) {
		
		List<Integer[]> levelWiseSum = new LinkedList<>();
		
		
		Map<Integer, List<Integer>> levelWiseItems = new HashMap<>();
		
		Queue<BTNode<Integer>> q = new LinkedList<>();
		q.offer(source);
		
		int currLevel = -1;
		while (!q.isEmpty()) {
			
			currLevel++;
			
			int levelSize = q.size();	
			List<Integer> levelItems = new ArrayList<>(levelSize);
			
			for (int i=0; i < levelSize; i++) {
				
				BTNode<Integer> curr = q.poll();
				if (curr != null) {
					
					levelItems.add(curr.data);
					
					if (curr.left != null) {
						q.offer(curr.left);
					}
					if (curr.right != null) {
						q.offer(curr.right);
					}
				}
			}
			levelWiseItems.put(currLevel, levelItems);
		}
		
		int[] levelWiseSummation = new int[levelWiseSum.size()];
		for (Map.Entry<Integer, List<Integer>> entry: levelWiseItems.entrySet()) {	
			
			System.out.println(entry);
			//levelWiseSummation[index++] = pair[0]/pair[1];
		}
		
		System.out.println("Level wise average of elements: ");
		System.out.println(Arrays.toString(levelWiseSummation));
	}
	
	
	
	
	/**
	 * 	Recursive.
	 */
	static void recursive(BTNode<Integer> source) {		
		Map<Integer, List<Integer>> levelWiseItems = new HashMap<>();
		dfs(source, 0, levelWiseItems);		
		System.out.println(Arrays.toString(levelWiseItems.entrySet().toArray()));		
	}
	
	static void dfs(BTNode<Integer> curr, int currLevel, Map<Integer, List<Integer>> levelItemsMap) {
		
		if (curr == null)
			return;
		
		// create new List to hold items at each level. 
		if (!levelItemsMap.containsKey(currLevel)) {
			levelItemsMap.put(currLevel, new LinkedList<>());
		}
		
		// add item to list.
		levelItemsMap.get(currLevel).add(curr.data);
		
		
		dfs(curr.left, currLevel + 1, levelItemsMap);
		
		dfs(curr.right, currLevel + 1, levelItemsMap);		
	}
	
	
	
	private static class BTNode<T> {
		T data;
		BTNode<T> left, right;
		public BTNode(T data) {
			this.data = data;
		}
	}
	
}
