package my.learning.datastructures.linkedlist;



/**
 * 	Linked List based Stack 
 * 	
 * 	with max() method which gives max item in stack in constant time O(1).
 * 
 */
public class StackMax<T extends Comparable<T>> {

	private Node<T> head;
	private int size;
	
	private NodeFrequency<T> maxHead;
	
	public StackMax() {
		this.head = null;
		this.size = 0;
		
		this.maxHead = null;	
	}
	
	
	public void push(T data) {
		
		Node<T> newNode = new Node<>(data);
		
		// handling max stack.
		NodeFrequency<T> newMaxNode = new NodeFrequency<>(data);
		if (this.maxHead == null) {
			this.maxHead = newMaxNode;
		} else if (this.maxHead != null && data.compareTo(this.maxHead.data) > 0) {			
			newMaxNode.next = this.maxHead;
			this.maxHead = newMaxNode;
		} else if (this.maxHead != null && data.compareTo(this.maxHead.data) == 0) {
			this.maxHead.incrementFreq();
		}
		
		
		// inserting data into stack.
		if (this.head == null) {
			this.head = newNode;
		} else {
			newNode.next = this.head;
			this.head = newNode;
		}
		this.size++;
	}
	
	public T pop() {
		
		Node<T> nodeToPop = null;
		
		if (this.head != null) {
			nodeToPop = this.head;
			this.head = this.head.next;
			this.size--;
		}
		
		if (nodeToPop != null && this.maxHead != null && this.maxHead.data.equals(nodeToPop.data)) {
			if (this.maxHead.frequency == 1) {
				this.maxHead = this.maxHead.next;	
			} else {
				this.maxHead.decrementFreq();
			}			
		}		
		
		return nodeToPop != null? nodeToPop.data: null;
	}

	public T peek() {		
		return this.head != null? this.head.data: null;
	}
	
	/**
	 * 	return max element in constant time O(1).
	 */
	public T max() {		
		return this.maxHead != null? this.maxHead.data: null;
	}
	
	public boolean isEmpty() {
		return this.size() <= 0;
	}

	public int size() {
		return this.size;
	}

	private static class Node<T> {
		T data;
		Node<T> next;
		public Node(T data) {
			this.data = data;
		}
	}
	
	private static class NodeFrequency<T> {
		T data;
		NodeFrequency<T> next;
		int frequency;		
		public NodeFrequency(T data) {
			this.data = data;
			this.frequency = 1;
		}
		public void incrementFreq() {
			this.frequency++;
		}
		public void decrementFreq() {
			this.frequency--;
		}
	}
	
	
	public static void main(String[] args) {
		
		StackMax<Integer> s = new StackMax<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(3);
		s.push(2);
		s.push(0);
		s.push(4);
		s.push(4);
		
		// 1,2,3,4
		
		System.out.println(s.max()); // 4
		System.out.println(s.pop()); // 4
		System.out.println(s.max()); // 4
		
		
		System.out.println(s.pop()); // 4
		System.out.println(s.max()); // 3
		System.out.println(s.pop()); // 0
		
		System.out.println(s.peek()); // 2
		System.out.println(s.pop()); // 2
		System.out.println(s.max()); // 3
		
		System.out.println(s.pop()); // 3
		
		System.out.println(s.max()); // 3
		System.out.println(s.pop()); // 3		
				
		System.out.println(s.max()); // 2
		System.out.println(s.pop()); // 2
		System.out.println(s.pop()); // 1
		
		System.out.println(s.max()); // null
	}
	
	
}
