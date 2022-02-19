package my.learning.datastructures.heap;

/**
 * 	MaxHeap <=> PriorityQueue (parent > lt child, parent > rt child).
 */
public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

	
	public MaxHeap(int initialCapacity) {
		super(initialCapacity);
	}
	
	public MaxHeap() {
		this(15);
	}
	
	protected void heapifyUp() {
		int currIndex = this.size - 1;
		
		/**
		 * 	does current node have parent & is curr val > parent val then swap the 2 values, else stop as the order of max heap is satisfied. 
		 */
		while (hasParent(currIndex) && dataAtNode(currIndex).compareTo(parent(currIndex)) > 0) {
			int parentIndex = parentIndex(currIndex);
			swap(currIndex, parentIndex);			
			currIndex = parentIndex;
		}
	}
		
	protected void heapifyDown() {
		int currIndex = 0;
		
		/**
		 * 	check among the 2 children which has the greater value & swap with that nodes value.
		 */
		while (hasLeftChild(currIndex)) {
			int greaterChildIndex = leftChildIndex(currIndex);
			if (hasRightChild(currIndex) && rightChild(currIndex).compareTo(leftChild(currIndex)) > 0) {
				greaterChildIndex = rightChildIndex(currIndex);
			}
			
			if (dataAtNode(greaterChildIndex).compareTo(dataAtNode(currIndex)) > 0) {
				swap(currIndex, greaterChildIndex);
			}
			
			currIndex = greaterChildIndex;
		}
	}
	
	public static void main(String[] args) {
		
		//MaxHeap<Person> q = new MaxHeap<>(20);
		
		MaxHeap<Integer> priorityQ = new MaxHeap<>(20);
		priorityQ.offer(10);
		System.out.println(priorityQ);
		
		priorityQ.offer(20);
		System.out.println(priorityQ);
		
		
		priorityQ.offer(30);
		System.out.println(priorityQ);
		
		priorityQ.offer(40);
		System.out.println(priorityQ);
		
		priorityQ.offer(50);
		System.out.println(priorityQ);
		priorityQ.offer(60);
		System.out.println(priorityQ);
		priorityQ.offer(70);
		System.out.println(priorityQ);
		priorityQ.offer(80);
		
		System.out.println(priorityQ);
		
		
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		System.out.println(priorityQ.extractMax());
		System.out.println(priorityQ);
		
		for (Integer data: priorityQ) {
			System.out.println(data);
		}
				
	}
	
	static class Person implements Comparable<Person> {
		
		String name;
		int age;

		@Override
		public int compareTo(Person other) {			
			return (age > other.age && name.compareTo(other.name) > 0)? +1: -1;
		}
		
	}
	
	
}
