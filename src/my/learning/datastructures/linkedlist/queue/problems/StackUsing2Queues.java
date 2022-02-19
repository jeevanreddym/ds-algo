package my.learning.datastructures.linkedlist.queue.problems;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsing2Queues {

	// Two inbuilt queues 
    Queue<Integer> q1; 
    Queue<Integer> q2; 
    
    // To maintain current number of elements. 
    int size; 
	
    public StackUsing2Queues() { 
    	size = 0; 
    	q1 = new LinkedList<>();
    	q2 = new LinkedList<>();
    } 
    
    void push(int x) { 
    	
    	size++; 

        // Push x first in empty q2 
        q2.add(x); 

        // Push all the remaining elements in q1 to q2. 
        while (!q1.isEmpty()) { 
            q2.add(q1.peek()); 
            q1.remove(); 
        } 

        // swap the names of two queues 
        Queue<Integer> q = q1; 
        q1 = q2; 
        q2 = q; 
    } 

	void pop() {

		// if no elements are there in q1
		if (q1.isEmpty())
			return;
		
		q1.remove();
		size--;
	}

	int top() {
		if (q1.isEmpty())
			return -1;
		return q1.peek();
	}

	int size() {
		return size;
	}
    
	public static void main(String[] args) {
		
		StackUsing2Queues s = new StackUsing2Queues(); 
        s.push(1); 
        s.push(2); 
        s.push(3); 
  
        System.out.println("current size: " + s.size()); 
        System.out.println(s.top()); 
        s.pop(); 
        System.out.println(s.top()); 
        s.pop(); 
        System.out.println(s.top()); 
  
        System.out.println("current size: " + s.size()); 
		
	}
	
}
