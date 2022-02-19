package my.learning.datastructures.linkedlist;

public class SLLProblems {

	public static void main(String[] args) {
		
		SinglyLinkedList<Integer> sll1 = new SinglyLinkedList<>();
		sll1.insertFirst(10);
		sll1.insertFirst(20);
		sll1.insertFirst(30);
		sll1.insertFirst(40);
		sll1.insertFirst(50);
		
		SinglyLinkedList<Integer> sll2 = new SinglyLinkedList<>();
		sll2.insertFirst(15);
		sll2.insertFirst(25);
		sll2.insertFirst(35);
		sll2.insertFirst(45);
		sll2.insertFirst(55);
		
		
		SinglyLinkedList<Integer> sll3 = new SinglyLinkedList<>();
		
		Integer data = null;
		while (sll1.size() > 0 && sll2.size() > 0) {
			
			data = sll1.removeFirst();
			sll3.insertLast(data);
			
			data = sll2.removeLast();
			sll3.insertLast(data);			
		}
		
		while (sll1.size() > 0) {			
			data = sll1.removeFirst();
			sll3.insertLast(data);	
		}
		
		while (sll2.size() > 0) {
			data = sll2.removeLast();
			sll3.insertLast(data);			
		}
		
		System.out.println(sll3);
	}
	
}
