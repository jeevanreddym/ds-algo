package my.learning.algorithms.tree.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertBSTtoBalancedBST {

	
	public static void main(String[] args) {
		
		TreeNode<Integer> a = new TreeNode<>(1);
		TreeNode<Integer> b = new TreeNode<>(2);
		TreeNode<Integer> c = new TreeNode<>(3);
		TreeNode<Integer> d = new TreeNode<>(4);
		TreeNode<Integer> e = new TreeNode<>(5);
		TreeNode<Integer> f = new TreeNode<>(6);
		TreeNode<Integer> g = new TreeNode<>(7);
		TreeNode<Integer> h = new TreeNode<>(8);
		TreeNode<Integer> i = new TreeNode<>(9);
		
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		g.right = h;	
		h.right = i;
		
        System.out.println(isBalanced(a)); 
        
        
        
        
        
		List<TreeNode<Integer>> inorderList = inorderList(a);
		
		// Constucts BST from inorderList. 
        int n = inorderList.size(); 
        TreeNode<Integer> root = constructBalancedBST(inorderList, 0, n - 1);
        List<TreeNode<Integer>> preorder = preorder(root);
        System.out.println(Arrays.toString(preorder.toArray()));
        
        
        System.out.println(isBalanced(root)); 
	} 
	
	
	/* Recursive function to construct binary tree */
    static TreeNode<Integer> constructBalancedBST(List<TreeNode<Integer>> nodes, int start, int end) { 
        
    	// base case. 
        if (start > end) 
            return null; 
  
        /* Get the middle element and make it root */
        int mid = (start + end) / 2; 
        TreeNode<Integer> node = nodes.get(mid); 
  
        /* Using index in Inorder traversal, construct left and right subtress */
        node.left = constructBalancedBST(nodes, start, mid - 1); 
        node.right = constructBalancedBST(nodes, mid + 1, end); 
  
        return node; 
    } 
	
	
	
	public static List<TreeNode<Integer>> inorderList(TreeNode<Integer> source) {
		List<TreeNode<Integer>> inorderList = new ArrayList<>();
		inorder(source, inorderList);
		return inorderList;
	}
	
	private static void inorder(TreeNode<Integer> curr, List<TreeNode<Integer>> inorderList) {
		if (curr == null) return;
		inorder(curr.left, inorderList);
		inorderList.add(curr);
		inorder(curr.right, inorderList);
	}
	
	public static List<TreeNode<Integer>> preorder(TreeNode<Integer> source) {
		List<TreeNode<Integer>> inorderList = new ArrayList<>();
		preorder(source, inorderList);
		return inorderList;
	}
	
	private static void preorder(TreeNode<Integer> curr, List<TreeNode<Integer>> datalist) {
		if (curr == null) return;
		datalist.add(curr);
		preorder(curr.left, datalist);		
		preorder(curr.right, datalist);
	}
		
	private static boolean isBalanced(TreeNode<Integer> curr) {
		/* If tree is empty then return true */
        if (curr == null) 
            return true; 
		
        int leftSubTreeHt = getHeight(curr.left);
        int rtSubTreeHt = getHeight(curr.right);
        
        if ((Math.abs(leftSubTreeHt - rtSubTreeHt) <= 1)
        		&& isBalanced(curr.left)
        		&& isBalanced(curr.right)) {
        	return true;
        }
		
        /* If we reach here then tree is not height-balanced */
		return false;
	}
	
	public static int getHeight(TreeNode<Integer> node) {
		return node != null? node.getHeight(): 0;
	}

	private static class TreeNode<T extends Comparable<T>> {
		T data;
		TreeNode<T> left, right;
		public TreeNode(T data) {
			this.data = data;
		}
		@Override
		public String toString() {		
			return data.toString();
		}
		public int getHeight() {
			int leftSubtreeHt = left != null? left.getHeight(): 0;
			int rtSubtreeHt = right != null? right.getHeight(): 0;
			return 1 + Integer.max(leftSubtreeHt, rtSubtreeHt);
		}
	}
	
}