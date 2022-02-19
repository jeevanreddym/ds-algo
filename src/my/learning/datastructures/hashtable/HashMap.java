package my.learning.datastructures.hashtable;



@SuppressWarnings("unchecked")
public class HashMap<K,V> {

	private SLLEntry<K,V>[] arr;
	private int size;
	private int capacity;
	private double loadFactor;
	
	
	
	public HashMap(int initialCapacity) {		
		this(initialCapacity, 0.75);
	}
	
	public HashMap(int initialCapacity, double loadFactor) {		
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = 0.75;
		this.arr = (SLLEntry<K,V>[]) new Object[this.capacity];		
	}
	
	/**
	 * 	most important function in the hash map implementation.
	 */
	private int getIndex(K key) {
		return key.hashCode() % capacity;
	}
	
	public void put(K key, V value) {
		
		SLLEntry<K,V> newNode = new SLLEntry<>(key, value);
		
		checkCapacity();
		
		int bucketIndex = getIndex(key);
		
		SLLEntry<K,V> head = arr[bucketIndex]; // data is stored in each bucket using a SLL data structure.
		
		if (head == null) {
			
			head = newNode;
			
		} else {
			
			SLLEntry<K,V> curr = head;
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
			
				curr.value = value;				
			}			
		}
		
		this.size++;
	}
	
	public V get(K key) {
		int bucketIndex = getIndex(key);
		SLLEntry<K,V> head = arr[bucketIndex];
		
		SLLEntry<K,V> curr = head;
		while (curr != null) {			
			if (curr.key.equals(key)) {
				break;
			}
			curr = curr.next;
		}
		
		return curr != null? curr.value: null;
	}
	
	public V remove(K key) {
		
		int arrayIndex = getIndex(key);
		SLLEntry<K,V> head = arr[arrayIndex];
		
		SLLEntry<K,V> prev = null;
		SLLEntry<K,V> curr = head;
		while (curr != null) {			
			if (curr.key.equals(key)) {
				break;
			}
			prev = curr;
			curr = curr.next;			
		}
		
		if (prev != null) {
			prev = curr.next; // thus removing the link b/w prev & curr node.
		}
		
		return curr.value;
	}
	
	/**
	 * 	checkCapacity().
	 */
	private void checkCapacity() {		
		if (this.size/this.capacity > this.loadFactor) {
			resize();
		}		
	}

	private void resize() {		
		int newCapacity = this.capacity * 2;		
		SLLEntry<K,V>[] newArr = (SLLEntry<K,V>[]) new Object[newCapacity];		
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
		
	static class SLLEntry<K,V> {		
		int hashcode;		
		K key;
		V value;
		SLLEntry<K,V> next;
		
		public SLLEntry(K key, V value) {
			this.key = key;
			this.value = value;
			this.hashcode = key.hashCode();;
		}
	}
	
}
