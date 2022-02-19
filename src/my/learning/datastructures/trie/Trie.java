package my.learning.datastructures.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/***************************************************************************
 * 
 *	Trie is data structure which stores data in such a way that it can be retrieved faster and improve the performance.
 *	
 *	Trie can be used to implement :
 *	1) Dictionary
 *	2) Searching contact in mobile phone book. 
 *	
 *	
 *
 * 	Description:
 * 
 *  A trie is a tree data-structure that stores words by compressing common prefixes. 
 *  To illustrate, following is a word list and its resulting trie.
 *
 *  WORDS: rat, rats, rattle, rate, rates, rating, can, cane, canny, cant, cans, cat, cats, cattle, cattles.
 *
 *   TRIE:
 *         ___________|____________
 *        |                        |
 *        r                        c
 *        a                ________a___________
 *        t~              |                    |
 *    ____|_____          n~                   t~
 *   |   |  |   |    _____|_____        _______|______
 *   s~  t  e~  i   |   |   |   |      |              t
 *       l  s~  n   e~  n   t~  s~     s~             l
 *       e~     g~      y~                            e~
 *                                                    s~
 *
 *   Each ~ in the figure indicates where a prefix is a word.
 *
 *   Generally, a trie has all the benefits of a hash table without any of the disadvantages.
 *
 *   --------------------------------------------------------------
 *          | HASH TABLE | TRIE     | explanations
 *   --------------------------------------------------------------
 *   Memory |    O(n)    |  < O(n)  | trie uses prefix compression.
 *          |            |          | Hence it does not store each
 *          |            |          | word explicitly
 *  ----------------------------------------------------------------
 *   Search |   O(1)     |  O(1)    | trie is technically faster.
 *          |            | pseudo-  | Given a word, computing a
 *          |            | constant | hash takes at least as long
 *          |            |          | as traversing a trie. Plus,
 *          |            |          | trie has no collision.
 *  ----------------------------------------------------------------
 *
 *  Tries are particularly superior to hash tables when it comes to solving problems such as word puzzles like boggle. 
 *  In such puzzles the objective is to find how many words in a given list are valid. So if for example at a particular instance 
 *  in boggle you have a list of one billion words all starting with zh-, whereas the dictionary has no words starting with
 *  zh-; then: if the dictionary is a hash table, you must compute the entire hashcode for each word and do one billion look-ups; 
 *  if on the other hand the dictionary is a trie, you only do the equivalent of partially computing one hashcode! 
 *  That's a saving of over one billion fold!
 *
 *  This implementations of trie uses an array to store the children nodes, where the numerical value of each char serves as index.
 **************************************************************************/

public class Trie {

	private TrieNode root;
	
	public Trie() {
		this.root = new TrieNode();
	}
		
	public boolean insert(String word) {				
		TrieNode curr = this.root;		
		for (char ch: word.toCharArray()) {
			TrieNode child = curr.getChild(ch);
			if (child == null) {
				curr.addChild(ch);
			}
			curr = curr.getChild(ch);
		}
				
		if (curr != null && !curr.isEndOfWord) {
			curr.isEndOfWord = true;
			return true;
		}		
		return false;
	}
	
	public boolean search(String word) {		
		TrieNode curr = this.root;
		for (char ch: word.toCharArray()) {
			curr = curr.getChild(ch);
			if (curr == null)
				return false;
		}		
		return curr != null? curr.isEndOfWord: false;
	}
	
	public boolean remove(String word) {				
		TrieNode curr = this.root;
		for (char ch: word.toCharArray()) {
			curr = curr.getChild(ch);			
			if (curr == null)				
				return false;
		}
		curr.isEndOfWord = false;
		return true;
	}
	

	
	
	/*************************************************************************
	 * Function: getWordList
	 * 
	 * @param word
	 * @return List<String>
	 *
	 *         Description: Return a lexicographically ordered list of all the words on the trie.
	 *
	 *         Technical Details: This is a recursive pre-order depth-first
	 *         traversal algorithm, pre-order is to Trie what in-order is to BST. In
	 *         addition, because each prefix assembled is an optimal substructure,
	 *         this is also a greedy algorithm: where the words are assembled during
	 *         traversal and as soon as a word is encountered, it is added to the
	 *         list of results.
	 *
	 *         0] initialize an empty list to hold the words to be added. 
	 *         1] for each child node of the root 
	 *         2] call the recursive function getWordList() to assemble all words stemming from the given prefix. 
	 *         3] return the list
	 ************************************************************************/
	private List<String> getAllWords() {
		List<String> words = new LinkedList<>();
		getAllWords(this.root, new LinkedList<>(), words);
		return words;
	}
	
	private void getAllWords(TrieNode curr, List<Character> word, List<String> words) {
		
		if (curr == null) return;		
		if (curr.ch != null)
			word.add(curr.ch);
				
		if (curr.isEndOfWord) {
			words.add(word.stream().map(String::valueOf).collect(Collectors.joining())); // convert List<Character> to String. 			
		}		
		
		for (TrieNode child: curr.children.values()) {
			getAllWords(child, word, words);
			word.remove(word.size() - 1); // backtracking.
		}	
	}
	
	/**
	 * Description: this function is the recursive portion of the overloaded function above.
	 */
	private void getWordList(TrieNode curr, String word, List<String> words) {
		
		if (curr == null) return;
		if (curr.ch == null)
			word = "";
		
		if (curr.isEndOfWord) {
			words.add(word);
		}
		
		for (TrieNode child: curr.children.values()) {			
			getWordList(child, word + child.ch, words);
		}	
	}
	
	public List<String> getWordList() {
		List<String> words = new LinkedList<>();
		getWordList(this.root, "", words);
		return words;
	}
	
	
	public List<String> startsWith(String prefix) {
		List<String> words = new LinkedList<>();
		
		TrieNode curr = this.root;
		for (Character ch: prefix.toCharArray()) {
			curr = curr.getChild(ch);
			if (curr == null)
				break;
		}
		
		if (curr != null) {
			startsWith(curr, prefix, words);
		}
		
		return words;
	}
	
	private void startsWith(TrieNode curr, String word, List<String> words) {
		if (curr == null) return;
				
		if (curr.isEndOfWord)
			words.add(word);
		
		for (TrieNode child: curr.children.values()) {
			startsWith(child, word + child.ch, words);
		}
	}
	
	
	static class TrieNode {		
		
		Character ch;
		Map<Character, TrieNode> children;		
	    boolean isEndOfWord;
				
		public TrieNode(Character ch) {
			this.ch = ch;
			this.children = new HashMap<>(26); // 26 possible options ('a','b','c'...'x','y','z').
		}
		public TrieNode() {			
			this(null);
		}
		
		public TrieNode addChild(Character ch) {
			this.children.put(ch, new TrieNode(ch));
			return this.children.get(ch);
		}
		
		public TrieNode getChild(Character ch) {
			return this.children.get(ch);
		}
	}
	
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		
		trie.insert("application");
		trie.insert("apple");
		trie.insert("applebee");
		trie.insert("apply");
		trie.insert("applause");
		trie.insert("Programming");
	    trie.insert("is");
	    trie.insert("a");
	    trie.insert("way");
	    trie.insert("of");
	    trie.insert("life");
		
		//System.out.println(trie.search("Programming"));
	    //System.out.println(trie.search("Programmi"));
		//System.out.println(trie.remove("apple"));
		//System.out.println(trie.remove("applause"));
	    	    	
	    trie.getWordList().forEach(word -> System.out.println(word));
	    System.out.println();
	    trie.getAllWords().forEach(word -> System.out.println(word));
	    System.out.println();
	    trie.startsWith("apple").forEach(word -> System.out.println(word));
	}	
		
}
