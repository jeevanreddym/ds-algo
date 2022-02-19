package my.learning.algorithms.arraysStrings.array.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	public static void main(String[] args) {		

		int[] arr1 = {1,3,5,7,9};
		int[] arr2 = {2,4,6,8,10};
		System.out.println(Arrays.toString(merge2SortedArrays(arr1, arr2)));	
		
		
		int[][] input = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };		
		System.out.println(Arrays.toString(mergeKSortedArrays(input)));
	}

	private static int[] merge2SortedArrays(int[] arr1, int[] arr2) {
		
		int[] merged = new int[arr1.length + arr2.length];		
		int i=0,j=0,k=0;
		
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j] ) {
				merged[k++] = arr1[i++];
			} else {
				merged[k++] = arr2[j++];
			}
		}
		
		while (i < arr1.length) {
			merged[k++] = arr1[i++];
		}
		
		while (j < arr2.length) {
			merged[k++] = arr2[j++];
		}
		
		return merged; 
	}
	
	
	public static int[] mergeKSortedArrays(int[][] arrays) {
				
		
		int mergedArraySize = 0;
		for (int i = 0; i < arrays.length; i++) {
			mergedArraySize += arrays[i].length;
        }
		int[] merged = new int[mergedArraySize]; // k * n.
		
		
		PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(arrays.length, (n1,n2) -> n1.value - n2.value);
		
		// add first element of each array to this heap.
        for (int i = 0; i < arrays.length; i++) {
            minHeap.offer(new HeapNode(i, 0, arrays[i][0]));
        }
		
        // Complexity O(n * k * log k)        
        for (int i = 0; i < mergedArraySize; i++) {
        	
        	HeapNode minNode = minHeap.poll();
        	
        	if (minNode != null) {
        		
        		int currValue = minNode.value;        		
        		int currArrayNum = minNode.arrayNum;
        		int currArrayIndex = minNode.arrayIndex;
        		
        		merged[i] = currValue; // add the min value from heap to output merged array.
        		
        		int nextArrayIndex = currArrayIndex + 1; // pick the next element from the array from where the min element was picked.
        		
        		if (nextArrayIndex < arrays[currArrayNum].length) {        			      			
        			minHeap.offer(new HeapNode(currArrayNum, nextArrayIndex, arrays[currArrayNum][nextArrayIndex]));
        		}        		
        	}
        }
        
		return merged;
	}
	
	
	static class HeapNode {
		public int arrayNum;
        public int arrayIndex;
        public int value;
 
        public HeapNode(int arrayNum, int arrayIndex, int value) {
            this.arrayNum = arrayNum;
            this.arrayIndex = arrayIndex;
            this.value = value;
        }
	}
	
}
