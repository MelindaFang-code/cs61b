package lab07;

import java.util.Iterator;
import java.util.Set;





public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
	
	private Node root;
	private int size;
	
	private class Node {
		
		/** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the BST. */
        Node left;
        Node right;               

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  left as the left node in the BST. */
        public Node(K k, V v) {
            key = k;
            val = v;            
        }                    
    }
	
	public BSTMap(){
		size = 0;
	}
	
	
	/** Removes all of the mappings from this map. */
    public void clear() {
    	root = null;
    	size = 0;
    	
    }
    	
    
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
    	return get(root, key) != null;
    		
    }
    

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
    	Node x = get(root, key);
    	if(x == null) {
    		return null;
    	}else {
    		return x.val;
    	}
    
    }
    
    /** Returns the node in the subtree x of BST of key-value pairs whose key
     *  is equal to KEY, or null if no such Entry exists. */
    private Node get(Node x, K k) {
    	if (k == null) {
            throw new IllegalArgumentException();
    	}else if(x == null) {
    		return null;
    	}
    	int cmp = k.compareTo(x.key);
    	if (cmp < 0) { 
    		return get(x.left, k);
    	} else if (cmp > 0){ 
    		return get(x.right, k);
    	}else { 
    		return x; 
    	}
    }
    	 
         

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
    	root = put(key, value, root);
    }
    
    private Node put(K key, V value, Node x) {
    	if (key == null) {
            throw new IllegalArgumentException();
    	}else if (x == null) {
    		this.size += 1;
    		return new Node(key, value);    		
    	}
    	int cmp = key.compareTo(x.key);
    	if (cmp < 0) { 
    		x.left = put(key, value, x.left); 
    	}else if (cmp > 0) {
    		x.right = put(key, value, x.right);
    	} else {
    		x.val = value; 
    	}
    	return x;    	
    }
    
    public void printInOrder() {
    	printInOrder(root);
    }
    
    private void printInOrder(Node x) {
    	if(x != null) {    		   
    		printInOrder(x.left);
    		System.out.println(x.key+"");
    		printInOrder(x.right);
    	}
    }
    
    
    @Override
    public Iterator<K> iterator() {
    	throw new UnsupportedOperationException();
    }
     
    
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
    	throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
    	throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
    	throw new UnsupportedOperationException();
    }
}
	

