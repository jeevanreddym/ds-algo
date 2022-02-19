package my.learning.datastructures.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BSTSet<K extends Comparable<K>> {

	private BSTSetNode<K> root;
		
	
	/**
	 * 	Iterative.
	 */
	public void insert(K key) {		
		
		BSTSetNode<K> newNode = new BSTSetNode<>(key);
		
		if (this.root == null) {
		
			this.root = newNode;
		
		} else {
			
			BSTSetNode<K> curr = this.root;
			while (curr != null) {
		
				int compareResult = key.compareTo(curr.key);
				
				if (compareResult <= 0) {
				
					if (curr.left == null) {
						curr.left = newNode;
						break;
					} else {
						curr = curr.left;
					}					
				} 
				
				else {
					
					if (curr.right == null) {
						curr.right = newNode;
						break;
					} else {
						curr = curr.right;
					}						
				}
				
			}
		}
	}
	
	/**
	 * 	Recursive.
	 */
	public void insertRecursive(K key) {		
		this.root = insertRecursive(this.root, key);
	}
	
	private BSTSetNode<K> insertRecursive(BSTSetNode<K> curr, K key) {
		
		if (curr == null)
			return new BSTSetNode<>(key);
		
		int compareResult = key.compareTo(curr.key);
		if (compareResult < 0) {
			
			curr.left = insertRecursive(curr.left, key);
			
		} else if (compareResult > 0) {
			
			curr.right = insertRecursive(curr.right, key);
		}
		
		return curr;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(K key) {
		BSTSetNode<K> node = findNode(key);
		return node != null? true: false;
	}
	
	public BSTSetNode<K> findNode(K key) {		
		
		if (this.root == null)
			return null;
		
		BSTSetNode<K> curr = this.root;
		while (curr != null) {
			
			int result = key.compareTo(curr.key);
			
			if (result == 0) {
				break;				
			} 
			
			else {
				if (result > 0) {
					curr = curr.right;
				} else {
					curr = curr.left;
				}
			}
		}
		
		return curr;
	}
	
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public K remove(K key) {
		
		if (this.root == null)
			return null;
		
		BSTSetNode<K> parent = null;
		BSTSetNode<K> curr = this.root;
		while (curr != null) {
			
			int result = key.compareTo(curr.key);
			
			if (result != 0) {
				
				if (result > 0) {
					
					curr = curr.right;	
					
				} else {
					
					curr = curr.left;
				}
				
				parent = curr; // keep track of the parent node.
			}
			
			else if (result == 0) {	// found node to delete;							
				
				// case 1: node to delete has no children.
				if (curr.left == null && curr.right == null) {
					
					if (parent == null) {
						this.root = null;
					} else if (parent.left != null && parent.left.key.equals(curr.key)) {
						parent.left = null;
					} else if (parent.right != null && parent.right.key.equals(curr.key)) {
						parent.right = null;
					}					
				} 
				
				// case 2: node to delete has 1 child.
				else if (curr.left == null) {						
					if (parent.left != null && parent.left.key.equals(curr.key)) {
						parent.left = curr.right;
					} else if (parent.right != null && parent.right.key.equals(curr.key)) {
						parent.right = curr.right;
					}
				} else if (curr.right == null) {
					if (parent.left != null && parent.left.key.equals(curr.key)) {
						parent.left = curr.left;
					} else if (parent.right != null && parent.right.key.equals(curr.key)) {
						parent.right = curr.left;
					}
				}
				
				// case 3: node to delete has 2 children.
				else if (curr.left != null && curr.right != null) {
						
					BSTSetNode<K> inorderSuccessor = curr.right.getMinNode();
					// or (inorder predecessor.)
					// inorderPredecessor = nodeToDelete.left.getMaxNode();
					
					remove(inorderSuccessor.key); // removing the inorder successor from its original position & putting it in the place of curr node.
					
					curr.key = inorderSuccessor.key;						
				}
				
			}
		}
		
		return curr != null? curr.key: null;
	}
	
	/**
	 * 	Tree traversals.
	 */	
	public List<K> dftInorder() {
		List<K> items = new ArrayList<>();
		dftInorder(this.root, items);
		return items;
	}
	
	private void dftInorder(BSTSetNode<K> node, List<K> items) {
		if (node == null) return;		
		
		dftInorder(node.left, items);		
		items.add(node.key);		
		dftInorder(node.right, items);
	}
	
	public List<K> dftPreorder() {
		List<K> items = new ArrayList<>();
		dftPreorder(this.root, items);
		return items;
	}
	
	private void dftPreorder(BSTSetNode<K> node, List<K> items) {		
		if (node == null) return;
		items.add(node.key);
		dftInorder(node.left, items);		
		dftInorder(node.right, items);
	}
	
	public List<K> dftPostorder() {
		List<K> items = new ArrayList<>();
		dftPostorder(this.root, items);
		return items;
	}
	
	private void dftPostorder(BSTSetNode<K> node, List<K> items) {
		if (node == null) return;
		dftInorder(node.left, items);		
		dftInorder(node.right, items);
		items.add(node.key);
	}
	

	public List<K> bft() {		
		
		List<K> items = new ArrayList<>();
		
		Queue<BSTSetNode<K>> q = new LinkedList<>();
		q.offer(this.root);
		
		while (!q.isEmpty()) {
			
			BSTSetNode<K> node = q.poll();
			
			items.add(node.key);
			
			if (node.left != null)
				q.offer(node.left);
			
			if (node.right != null)
				q.offer(node.right);
		}
		return items;		
	}
	
	/**
	 * 	Iterative preorder dfs.
	 */
	public List<K> dftIterative() {
		
		List<K> items = new ArrayList<>();
		
		Stack<BSTSetNode<K>> stack = new Stack<>();
		
		stack.push(this.root);
				
		while (!stack.isEmpty()) {
			
			BSTSetNode<K> curr = this.root;	
			
			items.add(curr.key); // process curr node.
			
			if (curr.right != null) {
				stack.push(curr.right);
			}
			
			if (curr.left != null) {
				stack.push(curr.left);
			}            
		}
		return items;
	}
	
	/**
	 * 	All tree traversal types.
	 */
	public void traverse() {
		System.out.println("bfs: " + bft());
		System.out.println("Inorder: " + dftInorder());
		System.out.println("Preorder: " + dftPreorder());
		System.out.println("Postorder: " + dftPostorder());		
		System.out.println("PreOrder iterative: " + dftIterative());
	}
		
	public int height(BSTSetNode<K> node) {
		return node != null? node.height(): 0;
	}
	
	public boolean isSymmetric() {
		return this.root == null || isSymmetric(this.root.left, this.root.right);
	}
	
	private boolean isSymmetric(BSTSetNode<K> leftNode, BSTSetNode<K> rightNode) {
		
		if (leftNode == null || rightNode == null)
			return leftNode == rightNode;
		
		return (leftNode.key.equals(rightNode.key)
			&& isSymmetric(leftNode.left, rightNode.right) 
			&& isSymmetric(leftNode.right, rightNode.left));
	}
	

	/**
	 *	Is BST Balanced (absolute diff b/w leftSubtree & rtSubtree is a max of 1).
	 */
	public boolean isBalanced() {
		return isBalanced(this.root);
	}	
	
	private boolean isBalanced(BSTSetNode<K> curr) {
		
        if (curr == null) return true; 
		
        int leftSubTreeHt = height(curr.left);
        int rtSubTreeHt = height(curr.right);
        
        if ((Math.abs(leftSubTreeHt - rtSubTreeHt) <= 1)
        		&& isBalanced(curr.left)
        		&& isBalanced(curr.right)) {
        	return true;
        }
		
        /* If we reach here then tree is not height-balanced */
		return false;
	}
	
	
	
	
	static class BSTSetNode<K extends Comparable<K>> {
		
		K key;		
		BSTSetNode<K> left, right;
		
		public BSTSetNode(K key) {
			this.key = key;
		}	
				
		public int height() {
			int leftSubTreeHeight = this.left != null? this.left.height(): 0;
			int rightSubTreeHeight = this.right != null? this.right.height(): 0;
			return 1 + Integer.max(leftSubTreeHeight, rightSubTreeHeight);
		}
		
		public BSTSetNode<K> getMinNode() {
			BSTSetNode<K> curr = this;
			while (curr != null && curr.left != null) {
				curr = curr.left;
			}
			return curr;
		}		
		
		public BSTSetNode<K> getMaxNode() {
			BSTSetNode<K> curr = this;
			while (curr != null && curr.right != null) {
				curr = curr.right;
			}
			return curr;
		}
			
	}
	
	
	public static void main(String[] args) {
		
		BSTSet<Integer> bst = new BSTSet<>();
		bst.insertRecursive(5);
		bst.insertRecursive(12);
		bst.insertRecursive(4);
		bst.insertRecursive(8);
		bst.insertRecursive(2);
		bst.insertRecursive(16);
		bst.insertRecursive(1);
		bst.insertRecursive(9);
				
//		for (Integer data: bst.dftInorder()) {
//			System.out.println(data);
//		}
		
		//bst.traverse();
		
		System.out.println(bst.isBalanced());
	}
	
}
