package my.learning.datastructures.tree;

public class BSTMap<K extends Comparable<K>, V> {

	private BSTEntry<K,V> root;
	
	public BSTMap() {
		this.root = null;
	}
	
	/**
	 * 	Iterative method.
	 */
	public void insert(K key, V value) {		
		BSTEntry<K,V> newNode = new BSTEntry<>(key, value);
		if (this.root == null) {
			this.root = newNode;
		} else {
			BSTEntry<K,V> curr = this.root;
			while (true) {
				int compareResult = key.compareTo(curr.key);
				if (compareResult <= 0) {
					if (curr.left == null)
						curr.left = newNode;
					else
						curr = curr.left;
				} else {
					if (curr.right == null)
						curr.right = newNode;
					else
						curr = curr.right;
				}
			}
		}
	}
		
	
	public BSTEntry<K,V> findNode(K key) {
		return null;
	}
	
	public V lookup(K key) {
		return null;
	}
	
	public V remove(K key) {
		return null;
	}
	
	
	static class BSTEntry<K extends Comparable<K>,V> implements Comparable<K> {		
		K key;	
		V value;
		
		BSTEntry<K,V> left, right;
		
		public BSTEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}		
		@Override
		public int compareTo(K other) {			
			return key.compareTo(other);
		}
	}
	
}
