package my.learning.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTIterative {

	private BSTNode root;
	
	
	public void insert(int data) {
		if (this.root == null) {
			this.root = new BSTNode(data);
		} else {
			
			BSTNode curr = this.root;			
			while (curr != null) {
				if (data > curr.data) {
					if (curr.right != null) {
						curr = curr.right;
					} else {
						curr.right = new BSTNode(data);
						break;
					}
				} else {
					if (curr.left != null) {
						curr = curr.left;
					} else {
						curr.left = new BSTNode(data);
						break;
					}
				}
			}			
		}
	}
	
	public boolean search(int data) {
		
		if (this.root == null) 
			return false;
		
		BSTNode curr = this.root;
		while (curr != null) {
			if (curr.data == data) {
				return true;
			} else if (data > curr.data) {
				if (curr.right != null) {
					curr = curr.right;
				} else {
					return false;
				}				
			} else {
				if (curr.left != null) {
					curr = curr.left;
				} else {
					return false;
				}
			}
		}		
		return false;
	}
	
	
	public void traverseBFT() {
		if (this.root != null) {
			Queue<BSTNode> q = new LinkedList<>();
			q.offer(this.root);
			while (!q.isEmpty()) {
				BSTNode curr = q.poll();
				System.out.println(curr.data);
				
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}
	
	/**
	 * 	Preorder DFT.
	 */
	public void traverseDFT() {
		if (this.root != null) {
			Stack<BSTNode> stack = new Stack<>();
			stack.push(this.root);
			while (!stack.isEmpty()) {
				BSTNode curr = stack.pop();
				System.out.println(curr.data);
				
				if (curr.left != null) {
					stack.push(curr.left);
				}
				if (curr.right != null) {
					stack.push(curr.right);
				}				
			}
		}
	}
	
	static class BSTNode {
		private int data;
		private BSTNode left, right;		
		public BSTNode(int data) {
			this.data = data;
		}		
	}
	
	
	public static void main(String[] args) {
		
		BSTIterative bst = new BSTIterative();
		
		bst.insert(25);
		bst.insert(2);
		bst.insert(30);
		bst.insert(20);
		bst.insert(15);		
		
		bst.traverseBFT();
		
		System.out.println(bst.search(20));
		System.out.println(bst.search(50));
		
		//bst.traverseDFT();
	}	
	
}
