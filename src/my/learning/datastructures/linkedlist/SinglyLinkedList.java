package my.learning.datastructures.linkedlist;

public class SinglyLinkedList<T> {

	SLLNode<T> head;
	int size;
	
	public SinglyLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	public void insertFirst(T data) {
		SLLNode<T> newNode = new SLLNode<T>(data);
		if (head != null)
			newNode.next = head;		
		head = newNode;			
		this.size++;
	}
	
	public void insertLast(T data) {
		SLLNode<T> newNode = new SLLNode<T>(data);		
		SLLNode<T> curr = this.head;
		while (curr != null && curr.next != null) {
			curr = curr.next;
		}		
		
		if (curr != null)
			curr.next = newNode;
		else
			head = newNode;
		this.size++;
	}
	
	public T removeLast() {
		SLLNode<T> nodeToDelete = null;
		SLLNode<T> prev = null;
		SLLNode<T> curr = this.head;
		
		while (curr != null && curr.next != null) {
			prev = curr;
			curr = curr.next;
		}
		
		if (prev != null) {
			nodeToDelete = curr;
			prev.next = null;
			this.size--;
		} else if (curr != null) {
			nodeToDelete = curr;
			this.head = null;
			this.size--;
		}
		return nodeToDelete != null? nodeToDelete.data: null;
	}
	
	public T removeFirst() {
		SLLNode<T> nodeToDelete = null;		
		if (this.head != null) {
			nodeToDelete = this.head;
			head = head.next;
			this.size--;
		}	
		return (nodeToDelete != null)? nodeToDelete.data: null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		SLLNode<T> curr = this.head;
		while (curr != null) {
			sb.append(curr.data + ",");
			curr = curr.next;
		}	
		sb.append("]");
		return sb.toString();
	}
	
	public int size() {
		return this.size;
	}
	
	
	static class SLLNode<T> {
		T data;
		SLLNode<T> next;
		public SLLNode(T data) {
			this.data = data;
		}
	}
	
	public static void main(String[] args) {
	
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
		sll.insertFirst(10);
		
	}
	
}
