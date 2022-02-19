package my.learning.datastructures.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 	Lexicographic order is the way of ordering of words based on the alphabetical order of their component letters. 
 * 	It is also known as lexical order, dictionary order and alphabetical order. 
 * 	It is similar to the way in we search any word in the dictionary.
 */

public class TrieUsingArray {

	private TrieArrayNode root;
	
	public TrieUsingArray() {
		this.root = new TrieArrayNode();
	}
	
	public void insert(String word) {		
		TrieArrayNode curr = this.root;		
		for (char ch: word.toCharArray()) {			
			int charIndex = getCharIndex(ch);			
			TrieArrayNode child = curr.children[charIndex];
			if (child == null) {
				curr.children[charIndex] = new TrieArrayNode(ch);
				curr = curr.children[charIndex];
			} else {
				curr = child;
			}			
		}		
		curr.isEndOfWord = true;
	}
	
	public boolean search(String word) {		
		TrieArrayNode curr = this.root;		
		for (char ch: word.toCharArray()) {			
			//int charIndex = ch - 'a';
			int charIndex = getCharIndex(ch);
			curr = curr.children[charIndex];
			if (curr == null) {
				return false;
			}
		}		
		return curr != null && curr.isEndOfWord;
	}
	
	public void remove(String word) {
		if (search(word)) {
			TrieArrayNode curr = this.root;
			for (char ch: word.toCharArray()) {
				curr = curr.getChild(ch);
			}
			curr.isEndOfWord = false;
		}
	}
	
	private int getCharIndex(char ch) {
		return (ch - 'a');
	}
	
	/**
	 * 	DFT of a trie to collect all the words in it.
	 */
	public List<String> allWords() {
		List<String> words = new ArrayList<>();
		allWords(this.root, null, words);
		return words;
	}
	
	private void allWords(TrieArrayNode curr, String word, List<String> words) {
		if (curr == null) return;
		
		if (curr.ch == null) 
			word = "";
		else
			word += curr.ch;	
			
		if (curr.isEndOfWord)
			words.add(word);
		
		for (TrieArrayNode child: curr.children) {
			allWords(child, word, words);
		}		
	}

	
	public List<String> startsWith(String prefix) {
	
		List<String> words = new ArrayList<>();
		
		TrieArrayNode curr = this.root;
		
		for (int i=0; i < prefix.length(); i++) {
			curr = curr.getChild(prefix.charAt(i));
		}
		
		for (TrieArrayNode child: curr.children) {
			startsWith(child, prefix + child.ch, words);
		}
		return words;
	}
	
	private void startsWith(TrieArrayNode node, String word, List<String> words) {		
		if (node == null) return;
		
		if (node.isEndOfWord)
			words.add(word);
		
		for (TrieArrayNode child: node.children) {			
			startsWith(child, word + child.ch, words);			
		}
	}
	
	
	
	static class TrieArrayNode {
		
		Character ch;
		TrieArrayNode[] children;
		boolean isEndOfWord;
				
		public TrieArrayNode(Character ch) {
			this.ch = ch;
			this.children = new TrieArrayNode[26]; // 26 possible options ('a','b','c'...'x','y','z').
		}
		public TrieArrayNode() {			
			this(null);
		}
		
		public void addChild(Character ch) {						
            int charIndex = (ch - 'a');			
			this.children[charIndex] = new TrieArrayNode(ch);
		}
		
		public TrieArrayNode getChild(Character ch) {
			int charIndex = (ch - 'a');
			return this.children[charIndex];
		}
	}
	
	public static void main(String[] args) {
		
		TrieUsingArray trie = new TrieUsingArray();
				
		trie.insert("applet");
		trie.insert("apple");
		trie.insert("applebee");
		trie.insert("applejack");
		trie.insert("apply");
		trie.insert("applause");
		
		trie.insert("application");
		trie.insert("applicant");
		trie.insert("applied");
		trie.insert("appliance");		
				
		trie.insert("baby");
		trie.insert("bar");
		trie.insert("bare");
	    
	    //System.out.println(trie.search("programming"));
	    //System.out.println(trie.search("programmi"));
	    
		
		System.out.println(Arrays.toString(trie.allWords().toArray())); 
		
		trie.remove("applause");
		System.out.println(Arrays.toString(trie.allWords().toArray()));
	    
	    
//	    System.out.println("");
//	    System.out.println("Starts with: appl");
//	    for (String word: trie.startsWith("appl")) {
//	    	System.out.println(word);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Starts with: appli");
//	    for (String word: trie.startsWith("appli")) {
//	    	System.out.println(word);
//	    }
	    
	}
	
}
