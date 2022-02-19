package my.learning.datastructures.linkedlist.problems;

import my.learning.datastructures.linkedlist.problems.SLLProblems.SLLNode;

/**
 * 	Reverse a linked list from position m to n. Do it in one-pass.
 */
public class PartiallyReverseSLL {

	public static void main(String[] args) {
		
		SLLNode sll1 = new SLLNode(1);
		SLLNode sll3 = new SLLNode(3);
		sll1.next = sll3;
		SLLNode sll5 = new SLLNode(5);
		sll3.next = sll5;
		SLLNode sll7 = new SLLNode(7);
		sll5.next = sll7;
		SLLNode sll9 = new SLLNode(9);
		sll7.next = sll9;
		
		System.out.print("SLL as is: ");
		SLLNode curr = sll1;
		while (curr != null) {
			System.out.print(curr.data + ",");
			curr = curr.next;
		}
		
		
//		System.out.print("SLL reversed:");
//		curr = reverseSLL(sll1);
//		while (curr != null) {
//			System.out.print(curr.data + ",");
//			curr = curr.next;
//		}
		
		
		System.out.print("\nSLL partial reversed: ");
		curr = reverseBetween(sll1, 2, 4);
		while (curr != null) {
			System.out.print(curr.data + ",");
			curr = curr.next;
		}
		
	}
	
	static SLLNode reverseSLL(SLLNode head) {
		
		SLLNode prev = null;
		SLLNode curr = head;
		SLLNode next = null;
		
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;			
		}
				
		return prev;
	}
	
	private static SLLNode reverseBetween(SLLNode head, int from, int to) {
		
		int currIndex = 1;
		
		SLLNode prevBeforeRev = null;
		SLLNode curr = head;
		while (currIndex < from) {
			prevBeforeRev = curr;
			curr = curr.next;
			++currIndex;
		}
		
		
		
		SLLNode tailAfterRev = curr;
		SLLNode prev = null;
		SLLNode next = null;		
		while (currIndex <= from && currIndex <= to) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;	
			++currIndex;
		}
		
		prevBeforeRev.next = prev;
		tailAfterRev.next = curr;		
				
		return head;
	}
	
}
