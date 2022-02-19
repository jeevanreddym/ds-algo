package my.learning.datastructures.linkedlist;

public class CircularLinkedList<T> {

	CLLNode<T> head;
	int size;
	
	public CircularLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	void insert(T data) {
		CLLNode<T> newNode = new CLLNode<>(data); 
		
		this.head = newNode;
		
	}
	
	boolean find(T data) {
		
		return false;
	}
	
	T remove(T data) {
		
		return null;
	}
	
	static class CLLNode<E> {
		E data;
		CLLNode<E> next;
		public CLLNode(E data) {
			this.data = data;
		}
	}
	

	public static void main(String[] args) {
		
		CircularLinkedList<Integer> cll = new CircularLinkedList<>();
		
		cll.insert(1);
		cll.insert(2);
		cll.insert(3);
		cll.insert(4);
		
	}
		
}
