package my.learning.algorithms.tree.problems;

public class LowestCommonAncestor {

	public static void main(String[] args) {
		
		TreeNode<Character> a = new TreeNode<>('A');
		TreeNode<Character> b = new TreeNode<>('B');
		TreeNode<Character> c = new TreeNode<>('C');
		TreeNode<Character> d = new TreeNode<>('D');
		TreeNode<Character> e = new TreeNode<>('E');
		TreeNode<Character> f = new TreeNode<>('F');
		TreeNode<Character> g = new TreeNode<>('G');
		TreeNode<Character> h = new TreeNode<>('H');
		
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		d.left = h;
		
		System.out.println(lowestCommonAncestor(a, 'H', 'E').data);
		System.out.println(lowestCommonAncestor(a, 'F', 'E').data);
		System.out.println(lowestCommonAncestor(a, 'B', 'G').data);
		System.out.println(lowestCommonAncestor(a, 'D', 'C').data);
		System.out.println(lowestCommonAncestor(a, 'F', 'G').data);
	}
		
	
	private static <T> TreeNode<T> lowestCommonAncestor(TreeNode<T> curr, T x, T y) {
		
		if (curr == null) 
			return null;
		
		if (curr.data.equals(x) || curr.data.equals(y)) 
			return curr;
		
		
		TreeNode<T> leftSearch = lowestCommonAncestor(curr.left, x, y);
		TreeNode<T> rightSearch = lowestCommonAncestor(curr.right, x, y);
				
		if (leftSearch == null && rightSearch == null) {
			return null;
		} else if (leftSearch == null && rightSearch != null) {
			return rightSearch;
		} else if (leftSearch != null && rightSearch == null) {
			return leftSearch;
		} else if (leftSearch != null && rightSearch != null) {
			return curr;
		}
		
		return null;
	}
	
	private static class TreeNode<T> {
		T data;
		TreeNode<T> left, right;
		public TreeNode(T data) {
			this.data = data;
		}		
	}
	
}
