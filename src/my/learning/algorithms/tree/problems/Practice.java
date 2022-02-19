package my.learning.algorithms.tree.problems;

import java.util.LinkedList;
import java.util.Queue;

import my.learning.algorithms.tree.problems.Practice.BinarySearchTree.BSTNode;

public class Practice {

	
	public static void main(String[] args) {
		
//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//		
//		bst.insert(10);		
//		bst.insert(20);		
//		bst.insert(30);		
//		bst.insert(40);
//		
//		bst.insertRec(1);		
//		bst.insertRec(2);		
//		bst.insertRec(3);		
//		bst.insertRec(4);
//		
//		print(bst.root);
//		System.out.println("\n");
//		
//		
//		System.out.println(bst.find(20));
//		System.out.println(bst.findRec(20));
//		System.out.println("\n");
		
		
		
		
		
		BSTNode<Integer> n1 = new BSTNode<>(10);		
		BSTNode<Integer> n2 = new BSTNode<>(20);		
		BSTNode<Integer> n3 = new BSTNode<>(30);		
		BSTNode<Integer> n4 = new BSTNode<>(40);		
		BSTNode<Integer> n5 = new BSTNode<>(50);		
		BSTNode<Integer> n6 = new BSTNode<>(60);		
		BSTNode<Integer> n7 = new BSTNode<>(70);		
		BSTNode<Integer> n8 = new BSTNode<>(80);		
		BSTNode<Integer> n9 = new BSTNode<>(90);
		
		
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		n3.left = n6;
		n3.right = n7;
		
		n4.left = n8;
		n4.right = n9;
			
		
		System.out.println(height(n1));
		
		System.out.println(height(n1, 0));
		
		System.out.println(maxSumOfPaths(n1));
		
		System.out.println(maxSumOfPaths(n1, 0));
		
		
		print(n1);
		System.out.println("\n");
		
		invert(n1);
		
		print(n1);
	}

	
	
	static int height(BSTNode<Integer> curr) {
		
		if (curr == null)
			return 0;
		
		return 1 + Integer.max(
					height(curr.left), 
					height(curr.right)
				);
	}
	
	static int height(BSTNode<Integer> curr, int currHt) {
		
		if (curr == null)
			return currHt;
		
		currHt++;
		
		return  Integer.max(
					height(curr.left, currHt), 
					height(curr.right, currHt)
				);
	}
	
	static int maxSumOfPaths(BSTNode<Integer> curr) {
		
		if (curr == null)
			return 0;
		
		return curr.data + Integer.max(
								maxSumOfPaths(curr.left), 
								maxSumOfPaths(curr.right)
							);
	}
	
	static int maxSumOfPaths(BSTNode<Integer> curr, int currPathSum) {
		
		if (curr == null)
			return currPathSum;
		
		currPathSum += curr.data;
		
		return Integer.max(
					maxSumOfPaths(curr.left, currPathSum), 
					maxSumOfPaths(curr.right, currPathSum)
				);
	}
	
	/**
	 * 	Invert a binary tree.
	 */
	static void invert(BSTNode<Integer> curr) {
		
		if (curr == null)
			return;
				
		// swap left & right nodes.
		BSTNode<Integer> temp = curr.left;		
		curr.left = curr.right;		
		curr.right = temp;
		
		
		invert(curr.left); // recurse & do the same for each child node.
		
		invert(curr.right);		
	}
	
	
	static void print(BSTNode<Integer> curr) {
		
		Queue<BSTNode<Integer>> q = new LinkedList<>();
		
		q.offer(curr);
		
		while (!q.isEmpty()) {
			
			curr = q.poll();
			System.out.print(curr.data + " ");
			
			if (curr.left != null)
				q.offer(curr.left);
			
			if (curr.right != null)
				q.offer(curr.right);
		}		
	}
	
	
	
	
	
	
	
	
	
	static class BinarySearchTree<K extends Comparable<K>> {
		
		BSTNode<K> root;
		
		public void insert(K data) {
			
			BSTNode<K> newNode = new BSTNode<>(data);
			
			if (this.root == null) {
				this.root = newNode;
				return;
			}
			
			BSTNode<K> curr = this.root;
			
			while (curr != null) {
				
				int res = newNode.data.compareTo(curr.data);
				
				if (res > 0) {
					
					if (curr.right == null) {						
						curr.right = newNode;
						break;						
					} else {
						curr = curr.right;
					}
					
				} else {
					
					if (curr.left == null) {
						curr.left = newNode;
						break;
					} else {
						curr = curr.left;
					}					
				}				
			}			
		}
		
		void insertRec(K data) {
			
			BSTNode<K> newNode = new BSTNode<>(data);
			
			if (this.root == null) {
				this.root = newNode;
				return;
			}
			
			insertRec(this.root, newNode);
		}
		
		void insertRec(BSTNode<K> curr, BSTNode<K> newNode) {			
			
			int res = newNode.data.compareTo(curr.data);	
			
			if (res > 0) {
				
				if (curr.right == null) {
					curr.right = newNode;
				} else {
					insertRec(curr.right, newNode);	
				}				
			}
			
			else {
				
				if (curr.left == null) {
					curr.left = newNode;
				} else {
					insertRec(curr.left, newNode);	
				}
			}
		}
		
		
		public boolean find(K data) {
			
			BSTNode<K> curr = this.root;
			
			while (curr != null) {
				
				int res = data.compareTo(curr.data);
				
				if (res == 0) {
					return true;
				} else if (res > 0) {
					curr = curr.right;
				} else {
					curr = curr.left;
				}
			}
			
			return false;
		}
		
		public boolean findRec(K key) {
			return findRec(this.root, key);
		}
		
		public boolean findRec(BSTNode<K> curr, K key) {
			if (curr == null) return false;			
			int res = key.compareTo(curr.data);			
			if (res == 0) {
				return true;
			} else if (res > 0) {
				return findRec(curr.right, key);
			} else {
				return findRec(curr.left, key);
			}
		}
		
		
		public boolean remove(K data) {
			
			return false;
		}
		
		static class BSTNode<K extends Comparable<K>> {
			K data;
			BSTNode<K> left, right;
			public BSTNode(K data) {
				this.data = data;
			}			
		}
		
	}
	
}
