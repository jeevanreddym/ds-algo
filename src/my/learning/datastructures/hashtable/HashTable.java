package my.learning.datastructures.hashtable;

@SuppressWarnings("unchecked")
public class HashTable<K> {
	
	private SLLNode<K>[] arr;
	private int size;
	private int capacity;
	private double loadFactor;
	
	
	
	public HashTable(int initialCapacity) {		
		this(initialCapacity, 0.75);
	}

	public HashTable(int initialCapacity, double loadFactor) {		
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = 0.75;
		this.arr = (SLLNode<K>[]) new Object[capacity];		
	}
	
	private int getIndex(K key) {
		return key.hashCode() % capacity;
	}
	
	public void put(K key) {
		
		SLLNode<K> newNode = new SLLNode<>(key);
		
		checkCapacity();
		
		int bucketIndex = getIndex(key);
		
		SLLNode<K> head = arr[bucketIndex];
		
		if (head == null) {
		
			head = newNode;
			
		} else {
			
			SLLNode<K> curr = head;
			while (curr != null) { // chk if a SLL node already exists with the same key.			
				if (curr.key.equals(key)) {
					break;
				}
				curr = curr.next;
			}
			
			if (curr == null) { // insert the new node in linked list.
				
				newNode.next = head;
				head = newNode;
				
			} else { // replace the existing node with the new value.
			
				curr = newNode;				
			}			
		}
		
		this.size++;
	}
	
	public K get(K key) {
		int bucketIndex = getIndex(key);
		SLLNode<K> head = arr[bucketIndex];
		
		SLLNode<K> curr = head;
		while (curr != null) {			
			if (curr.key.equals(key)) {
				break;
			}
			curr = curr.next;
		}
		
		return curr != null? curr.key: null;
	}
	
	public K remove(K key) {
		
		int bucketIndex = getIndex(key);
		SLLNode<K> head = arr[bucketIndex];
		
		SLLNode<K> prev = null;
		SLLNode<K> curr = head;
		while (curr != null) {			
			if (curr.key.equals(key)) {
				break;
			}
			prev = curr;
			curr = curr.next;			
		}
		
		if (prev != null) {
			prev = curr.next;
		}
		
		return curr.key;
	}
	
	/**
	 * 	checkCapacity().
	 */
	private void checkCapacity() {		
		if (this.size / this.capacity > this.loadFactor) {
			resize();
		}		
	}

	private void resize() {		
		int newCapacity = this.capacity * 2;		
		SLLNode<K>[] newArr = (SLLNode<K>[]) new Object[newCapacity];		
		for (int i=0; i < this.arr.length; i++) {
			newArr[i] = this.arr[i];
		}
		this.arr = newArr;
	}

	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	
	static class SLLNode<K> {
		K key;
		SLLNode<K> next;
		public SLLNode(K key) {
			this.key = key;
		}
	}
	
}
