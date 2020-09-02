package proj2AB_bearMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	
	private ArrayList<PriorityNode> items;
	private HashMap<T, Integer> keys;
	
	private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }
	}
	
	public ArrayHeapMinPQ(){
		this.items = new ArrayList<>();
		this.keys = new HashMap<>();
		items.add(null);	
	}
	
	private void swap(int i, int j) {
		PriorityNode t = items.get(i); 
		items.set(i, items.get(j)); 
		items.set(j, t); 
	}
	
	private void swim(int k) {
		if (k != 1 && less(k, parent(k))) {
			keys.put(items.get(k).getItem(), parent(k));
            keys.put(items.get(parent(k)).getItem(), k);
			swap(k, parent(k));		
			swim(parent(k));			
		}
	}
	
	private boolean less(int a, int b) {
		return items.get(a).compareTo(items.get(b)) < 0;
	}
		
	private int parent(int k){
		return k/2;
	}
	
	private void sink(int k)
	  {
		while (k*2 <= size()) {
			int j = 2*k;
			if (j < size() && less(j+1, j)) 
				j++; 
			if (less(k, j)) 
				break;
		keys.put(items.get(k).getItem(), j);
        keys.put(items.get(j).getItem(), k);
		swap(k, j);
		k = j;
	} 
		}
	        
	/* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
	public void add(T item, double priority) {
    	if (contains(item)) {
            throw new IllegalArgumentException();
        }
    	PriorityNode x = new PriorityNode(item, priority);
    	items.add(x);
    	keys.put(item, size());
    	swim(size());
    	  	
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
    	return keys.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
    	if(size() == 1) {
    		throw new NoSuchElementException();
    	}
    	return items.get(1).getItem();
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
    	if(size() == 1) {
    		throw new NoSuchElementException();
    	}
    	T smallest = getSmallest();
    	swap(1,size());
    	items.remove(size());
    	sink(1);
    	return smallest;
    			
    	
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
    	return items.size()-1;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item 
     * doesn't exist. */
    public void changePriority(T item, double priority) {
    	if (size() == 1 || !contains(item)) {
            throw new NoSuchElementException();
        }
    	int i = keys.remove(item);
    	items.remove(i);  	
        add(item, priority);
    	
    	
    	
    }
	
}
