package lab9_tries;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MyTrieSet {
	
	private class Node {
		private boolean isKey;
		private HashMap<Character, Node> next;
		
		private Node(boolean isKey) {
			this.isKey = isKey;
			this.next = new HashMap();
		}	
	}
	
	private Node root;
	
	public MyTrieSet(){
		root = new Node(false);		
	}
	
	void clear() {
		root = new Node(false);
	}

    /** Returns true if the Trie contains KEY, false otherwise */
    boolean contains(String key) {
    	Node curr = root;
    	for(int i = 0; i < key.length(); i++) {
    		if(!curr.next.containsKey(key.charAt(i))) {
    			return false;
    		}
    		curr = curr.next.get(key.charAt(i));
    	}
    	if(curr.isKey) {
    		return true;
    	}else {
    		return false;
    	}
    }

    /** Inserts string KEY into Trie */
    void add(String key) {
    	Node curr = root;
    	for(int i = 0; i < key.length(); i++) {
    		if(!curr.next.containsKey(key.charAt(i))) {
    			Node x = new Node(false);    			
    			curr.next.put(key.charAt(i), x);    			
    		}
    		curr = curr.next.get(key.charAt(i));
    	}
    	curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    List<String> keysWithPrefix(String prefix){
    	List<String> start = new ArrayList<String>();
    	Node curr = root;
    	for(int i = 0; i < prefix.length(); i++) {
    		if(!curr.next.containsKey(prefix.charAt(i))) {
    			return null;
    		}
    		curr = curr.next.get(prefix.charAt(i));
    	}
    	for(char c: curr.next.keySet()) {
    		prefixHelp(prefix + c, start, curr.next.get(c));
    	}
    	return start;
    }
    
	void prefixHelp(String s, List<String> x, Node n) {
		if (n.isKey){ 
			x.add(s);
		}
		for(char c : n.next.keySet()) {
			prefixHelp(s + c, x, n.next.get(c));
		}
	}

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    String longestPrefixOf(String key) {
    	throw new UnsupportedOperationException();
    }
}
