package my.learning.datastructures.heap;



/**
 * 	MinHeap <=> PriorityQueue (parent < lt child, parent < rt child).
 */
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

	
	public MinHeap(int initialCapacity) {
		super(initialCapacity);
	}
	
	public MinHeap() {
		this(15);
	}
	
	protected void heapifyUp() {
		int currIndex = this.size - 1;
		while (hasParent(currIndex) && parent(currIndex).compareTo(dataAtNode(currIndex)) > 0) {
			int parentIndex = parentIndex(currIndex);
			swap(currIndex, parentIndex);			
			currIndex = parentIndex;
		}
	}
		
	protected void heapifyDown() {
		int currIndex = 0;
		while (hasLeftChild(currIndex)) {
			int lesserChildIndex = leftChildIndex(currIndex);
			if (hasRightChild(currIndex) && rightChild(currIndex).compareTo(leftChild(currIndex)) < 0) {
				lesserChildIndex = rightChildIndex(currIndex);
			}
			
			if (dataAtNode(currIndex).compareTo(dataAtNode(lesserChildIndex)) > 0) {
				swap(currIndex, lesserChildIndex);
			}
			
			currIndex = lesserChildIndex;
		}
	}
	
	public static void main(String[] args) {
		
		//MinHeap<Person> q = new MinHeap<>(20);
		
		MinHeap<Integer> priorityQ = new MinHeap<>(20);
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
		
}
