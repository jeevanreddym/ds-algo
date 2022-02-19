package my.learning.datastructures.array;


@SuppressWarnings("unchecked")
public class DynamicArray<T> {

	private T[] arr;
	private int size;
	private int capacity;
	
	public DynamicArray(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity > 0? initialCapacity: 15;
		this.arr = (T[]) new Object[this.capacity];
	}
	
	public DynamicArray() {
		this(15);
	}
	
	public void add(T data) {
		checkCapacity();		
		this.arr[this.size++] = data;
	}
	
	public void add(int index, T data) {
		checkCapacity();
		
		for (int i = index; i < this.size; i++) {
			this.arr[i+1] = this.arr[i];
		}
		this.arr[index] = data;
		
		this.size++;
	}
	
	private void checkCapacity() {
		if (this.size == this.capacity)
			resize();
	}
	
	private void resize() {
		this.capacity = this.capacity * 2;
		T[] newArr = (T[]) new Object[this.capacity];
		for (int i=0; i < this.size; i++) {
			newArr[i] = this.arr[i];
		}
		this.arr = newArr;
	}
	
	public T lookup(int index) {
		return this.arr[index];
	}
	
	public T remove(int index) {		
		T toBeRemoved = this.arr[index];		
		for (int i = index; i < this.size; i++) {
			this.arr[i] = this.arr[i + 1];
		}		
		this.size--;
		return toBeRemoved;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < this.size; i++) {
			sb.append(this.arr[i] + ",");
		}
		return sb.toString();
	}
	
	 
	
	public static void main(String[] args) {
		
		DynamicArray<Integer> arr = new DynamicArray<>(5);
		arr.add(10);
		arr.add(20);
		arr.add(30);
		arr.add(40);
		arr.add(50);
		arr.add(60);
		arr.add(70);
		arr.add(80);
		
		arr.remove(3);
		arr.remove(4);
		
		System.out.println(arr);
		System.out.println(arr.lookup(2));		
	}
	
}
