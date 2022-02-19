package my.learning.datastructures.linkedlist.problems;

/**
 * 	You are given a doubly linked list which in addition to the next and previous pointers, 
 * 	it could have a child pointer, which may or may not point to a separate doubly linked list. 
 * 	These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
	Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
	
	Input: head = [1,2,3,4,5,6]
					  [7,8,9,10]
					  	[11,12]
	Output: [1,2,3,7,8,11,12,9,10,4,5,6]	
	
 */
public class DLLProblems {

	public static void main(String[] args) {
		
		DLLNode d1 = new DLLNode(1);
		
		DLLNode d2 = new DLLNode(2);		
		d1.next = d2;
		d2.prev = d1;
				
		DLLNode d3 = new DLLNode(3);
		d2.next = d3;
		d3.prev = d2;
				
		DLLNode d4 = new DLLNode(4);
		d3.next = d4;
		d4.prev = d3;
		
		DLLNode d5 = new DLLNode(5);
		d4.next = d5;
		d5.prev = d4;
		
		DLLNode d6 = new DLLNode(6);
		d5.next = d6;
		d6.prev = d5;
		
		
		DLLNode d7 = new DLLNode(7);
		d3.child = d7;
				
		DLLNode d8 = new DLLNode(8);
		d7.next = d8;
		d8.prev = d7;
		
		DLLNode d9 = new DLLNode(9);
		d8.next = d9;
		d9.prev = d8;
		
		DLLNode d10 = new DLLNode(10);
		d9.next = d10;
		d10.prev = d9;
		
		DLLNode d11 = new DLLNode(11);
		d8.child = d11;
				
		DLLNode d12 = new DLLNode(12);
		d11.next = d12;
		d12.prev = d11;
		
		

		
		
		flattenChildren(d1);
		
		DLLNode curr = d1;
		while (curr != null) {
			System.out.print(curr.data + (curr.next != null? ",": ""));
			curr = curr.next;
		}
		
	}
	
	public static DLLNode flattenChildren(DLLNode curr) {
     
		DLLNode prev = null;
		
		while (curr != null) {
			
			//System.out.print(curr.data + ",");
			
			if (curr.child != null) {
				
				DLLNode tempNext = curr.next;
				
				curr.next = curr.child;
				curr.child.prev = curr;
				
				curr.child = null;
				
				curr = flattenChildren(curr.next);
				
				curr.next = tempNext;
				tempNext.prev = curr;				
			}
			
			prev = curr;
			curr = curr.next;
		}		
		
		return prev;
    }
	
	
	public static class DLLNode {
		int data;
		DLLNode next, prev, child;
		public DLLNode(int data) {
			this.data = data;
		}
	}
	
}
