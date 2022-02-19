package my.learning.problems.solving.amazon;

import java.util.PriorityQueue;

public class ConnectingRopes {

	public static void main(String[] args) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((x1,x2) -> x1 - x2); // min heap.
		pq.offer(10);
		pq.offer(3);
		pq.offer(6);
		pq.offer(1);
				
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x1,x2) -> x2 - x1); // max heap.
		maxHeap.offer(10);
		maxHeap.offer(3);
		maxHeap.offer(6);
		maxHeap.offer(1);
				
		while (!maxHeap.isEmpty()) {
			System.out.println(maxHeap.poll());
		}
		
	}
	
}
