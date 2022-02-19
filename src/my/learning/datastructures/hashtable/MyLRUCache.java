package my.learning.datastructures.hashtable;

import java.util.HashMap;
import java.util.Map;


/**
 * 	LRU Cache implementation using Map & DLL.
 */
public class MyLRUCache<K,V> {

	Map<K, LRUCacheNode<K,V>> map = new HashMap<>();
	int capacity;
	LRUCacheNode<K,V> head, tail;
		
	
	public MyLRUCache(int capacity) {
		this.capacity = capacity;
		this.head = new LRUCacheNode<>();
		this.tail = new LRUCacheNode<>();
		join(this.head, this.tail);
	}
	

	/**
	 * 	O(1).
	 */
	private V get(int key) {
		
		if (!map.containsKey(key))
			return null;
				
		LRUCacheNode<K,V> node = map.get(key);		
		remove(node); // remove the node from DLL and...
		moveToHead(node); // move this most recently accessed node to the head.
		
		return node.value;
	}

	/**
	 * 	O(1).
	 */
	private void put(K key, V value) {		
		
		if (map.containsKey(key)) { // if key already present in cache, then jus replace the value.
			
			LRUCacheNode<K,V> node = map.get(key);
			node.value = value;
			remove(node);
			moveToHead(node);
			
		} else {

			if (map.size() == this.capacity) { // remove least recently used element from the cache by removing an element from the tail of the DLL.
				if (tail.prev != head) {
					map.remove(tail.prev.key);
					remove(tail.prev);
				}
			}
			
			LRUCacheNode<K,V> node = new LRUCacheNode<>(key, value);
			map.put(key, node);
			moveToHead(node);
		}		
	}
	
	private void remove(LRUCacheNode<K,V> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	private void moveToHead(LRUCacheNode<K,V> nodeToMoveToHead) {
		LRUCacheNode<K,V> tempNode = this.head.next;
		join(this.head, nodeToMoveToHead);
		join(nodeToMoveToHead, tempNode);		
	}

	private void join(LRUCacheNode<K,V> node1, LRUCacheNode<K,V> node2) {
		node1.next = node2;
		node2.prev = node1;		
	}

	
	private static class LRUCacheNode<K,V> {
		K key;
		V value;
		LRUCacheNode<K,V> next, prev;
		public LRUCacheNode() {}
		public LRUCacheNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		
		MyLRUCache<Integer, Integer> lruCache = new MyLRUCache<>(5);
		
		lruCache.put(1, 2);
		lruCache.put(2, 4);
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.get(1));
		System.out.println(lruCache.get(3));
	}

}
