package my.learning.datastructures.linkedlist.problems;

public class SLLProblems {

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
				
		SLLNode sll2 = new SLLNode(2);
		SLLNode sll4 = new SLLNode(4);
		sll2.next = sll4;
		SLLNode sll6 = new SLLNode(6);
		sll4.next = sll6;
		SLLNode sll8 = new SLLNode(8);
		sll6.next = sll8;
		SLLNode sll10 = new SLLNode(10);
		sll8.next = sll10;
				
		SLLNode merged = mergeSortedSLLs(sll1, sll2);
		SLLNode curr = merged;
		while (curr != null) {
			System.out.println(curr.data);
		}		
	}
	
	static SLLNode mergeSortedSLLs(SLLNode s1, SLLNode s2) {
		
		return null;
	}
	
	static SLLNode intersectionPointOf2SLLs(SLLNode s1, SLLNode s2) {
		
		return null;
	}
	

	static SLLNode reverse(SLLNode head) {
		SLLNode prev = null; 
		SLLNode current = head; 
		SLLNode next = null; 
        while (current != null) { 
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next; 
        } 
        
        head = prev; 
        return head; 
	}
	
	/**
	 * 	fast & slow pointer technique.
	 */	
	static SLLNode findKthLastNode(SLLNode head, int k) {
		SLLNode fast = head;
		SLLNode slow = head;
		int start = 1;
		while (fast.next != null) {
			fast = fast.next;
			start++;
			if (start > k) {
				slow = slow.next;
			}
		}
		return slow;		
	}
		
	static boolean hasCycle(SLLNode head) {
		SLLNode slow = head;
		SLLNode fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				return true; // found the cycle
		}
		return false;
	}
	
	static int findCycleLength(SLLNode head) {
		SLLNode slow = head;
		SLLNode fast = head;
	    while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast) {
				SLLNode current = slow;
				int cycleLength = 0;
				do {
					current = current.next;
					cycleLength++;
				} while (current != slow);
				return cycleLength;
			}
	    }
	    return 0;
	}
		
	static SLLNode findCycleStart(SLLNode head) {
		
		SLLNode slow = head;
		SLLNode fast = head;
		
		int cycleLength = 0;
	    while (fast != null && fast.next != null) {
	      fast = fast.next.next;
	      slow = slow.next;
			if (slow == fast) {
				SLLNode current = slow;
				
				do {
					current = current.next;
					cycleLength++;
				} while (current != slow);				
			}
	    }
	    
	    SLLNode ptr1 = head;
	    SLLNode ptr2 = head;
	    while (cycleLength > 0) {
	      
	    	ptr2 = ptr2.next;
	    	cycleLength--;
	    }

	    while (ptr1 != ptr2){
	    	ptr1 = ptr1.next;
	    	ptr2 = ptr2.next;
	    }
	    return ptr1;
	}
	
	
	static class SLLNode {
		int data;
		SLLNode next;
		public SLLNode(int data) {
			this.data = data;
		}
	}
	
}
