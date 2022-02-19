package my.learning.datastructures.heap;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 	A Complete Binary Tree implementation whose underlying dataset is an Array (DynamicArray).
 * 	Objects inserted into a Heap need to be Comparable (override compareTo()) to one another. 
 * 	
 */
public abstract class Heap<T extends Comparable<T>> implements Iterable<T> {

	protected T[] queue;
	protected int size;
	protected int capacity;
	
	
	@SuppressWarnings("unchecked")
	public Heap(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity > 0? initialCapacity: 15; 
		this.queue = (T[]) new Comparable[this.capacity];
	}
	
	public Heap() {
		this(15);
	}
	
	/**
	 * 	O(log(n)) time.
	 * 	Insert new element at the end of the array & then heapifyUp (compare with its parents) 
	 * 	to determine where it needs to be placed exactly. If new key is smaller than its parent, 
	 * 	then we don’t need to do anything. Otherwise, we need to traverse up to fix the violated heap property.
	 */
	public void offer(T data) {
		chkForResize();
		this.queue[this.size++] = data;
		heapifyUp();
	}
	
	protected abstract void heapifyUp();
	
	
	
	/**
	 * 	O(log(n)) time.
	 * 	extractMax() returns the MAX element (array[0]) in the dataset.
	 * 	Last & first elements in the array are swapped & then the element at array[0] is compared to its children
	 * 	to place it in the correct position. we need to traverse down to fix the violated heap property.
	 */
	public T extractMax() {
		T max = this.queue[0];
		swap(0, this.size - 1);
		this.queue[this.size - 1] = null;
		this.size--;
		heapifyDown();
		return max;		
	}
	
	protected abstract void heapifyDown();
	
	
	/**
	 * 	O(1) time.	
	 */
	public T peek() {		
		return this.queue[0];		
	}
	
	
	protected int parentIndex(int index) {
		return (index - 1)/2;
	}
	
	protected int leftChildIndex(int index) {
		return (index * 2) + 1;
	}
	
	protected int rightChildIndex(int index) {
		return (index * 2) + 2;
	}
	
	protected T parent(int index) {
		return this.queue[parentIndex(index)];
	}
	
	protected T leftChild(int index) {
		return this.queue[leftChildIndex(index)];
	}
	
	protected T rightChild(int index) {
		return this.queue[rightChildIndex(index)];
	}
	
	protected T dataAtNode(int index) {
		return this.queue[index];
	}
	
	protected boolean hasParent(int index) {
		return parentIndex(index) >= 0;
	}
	
	protected boolean hasLeftChild(int index) {
		return leftChildIndex(index) < this.size;
	}
	
	protected boolean hasRightChild(int index) {
		return rightChildIndex(index) < this.size;
	}
	
	protected void swap(int i, int j) {
		T temp = this.queue[i];
		this.queue[i] = this.queue[j];
		this.queue[j] = temp;
	}	

	@Override
	public Iterator<T> iterator() {
		return new HeapIterator<>(this.queue, this.size);
	}
	
	static class HeapIterator<E> implements Iterator<E> {

		private int cursor = 0;
		private E[] queue;
		private int size;
		
		public HeapIterator(E[] queue, int size) {
			this.queue = queue;
			this.size = size;
		}
		
		@Override
		public boolean hasNext() {			
			return this.cursor < this.size;
		}

		@Override
		public E next() {
			return this.queue[this.cursor++];
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < this.size; i++) {
			sb.append(this.queue[i] + ",");
		}
		return sb.toString();
	}	
	
	public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
	
	private void chkForResize() {
		if (this.size == this.capacity) {
			resize();
		}		
	}

	private void resize() {
		// Create a new array with double the capacity & copy over
		// the old elements to the new array.
	}
	
}