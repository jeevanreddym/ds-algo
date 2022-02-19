package my.learning.datastructures.tree;

public class BSTRecursive {

	private BSTNode root;
	
	
	public void insert(int data) {
		if (this.root != null) {
			this.root.insert(data);			
		} else {
			this.root = new BSTNode(data);
		}
	}
	
	public boolean search(int data) {
		if (this.root != null) {
			return this.root.search(data);
		}		
		return false;
	}
	
	/**
	 * 	Preorder DFT.
	 */
	public void traverse() {
		
	}
	
	static class BSTNode {
		private int data;
		private BSTNode left, right;
		
		public BSTNode(int data) {
			this.data = data;
		}
		
		public void insert(int data) {			
			if (data > this.data) {
				if (right != null) {
					right.insert(data);	
				} else {
					right = new BSTNode(data);
				}				
			} else if (data <= this.data) {
				if (left != null) {
					left.insert(data);
				} else {
					left = new BSTNode(data);
				}
			}			
		}
		
		public boolean search(int data) {
			if (this.data == data) {
				return true;
			} else if (data > this.data) {				
				return right != null? right.search(data): false;
			} else if (data < this.data) {				
				return left != null? left.search(data): false;
			}
			return false;
		}
		
	}
	
	
	public static void main(String[] args) {
		
		BSTRecursive bst = new BSTRecursive();
		
		bst.insert(25);
		bst.insert(2);
		bst.insert(30);
		bst.insert(20);
		bst.insert(15);		
		
		System.out.println(bst.search(20));
		System.out.println(bst.search(50));
		
		bst.traverse();		
	}	
	
}
