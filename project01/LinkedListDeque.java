package project01;

public class LinkedListDeque<T> {
	
	private class Node {
		
		public T item;
		public Node prev;
		public Node next;		
		public Node(T n, Node p, Node r){
			item = n;
			prev = p;
			next = r;
		}
	}
	
	private Node sentinel;
	private int size;
	
	
	public LinkedListDeque() {
		sentinel = new Node(null, null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		
	}
	
	
	public LinkedListDeque(LinkedListDeque other) {
		sentinel = new Node(null, null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
		Node x = other.sentinel.next;
		while(size < other.size()) {
			addLast(x.item);
			x = x.next;
		}
		
		
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/** adds an item of type T to the front of the deque*/
	public void addFirst(T item) {
		size += 1;
		Node m = new Node(item, sentinel, sentinel.next);
		sentinel.next.prev = m;
		sentinel.next = m;
	}
	
	
	public void addLast(T item) {
		
		size += 1;
		Node m = new Node(item,sentinel.prev, sentinel);
		sentinel.prev.next = m;
		sentinel.prev = m;
	}
	
	/** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
	public T removeFirst() {
		if(isEmpty()) {
			return null;
		}
		size -= 1;
		T first = sentinel.next.item;
		sentinel.next.prev = sentinel;
		sentinel.next = sentinel.next.next;
		return first;
	}
	
	/** Removes and returns the item at the end of the deque.
     *  If no such item exists, returns null.
     */
	public T removeLast() {
		if(isEmpty()) {
			return null;
		}
		size -= 1;
		T last = sentinel.prev.item;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		return last;
		
	}
	
	public int size() {
		return this.size;
	}
	
	/**Gets the item at the given index, where 0 is the front, 1 is 
	 * the next item, and so forth. If no such item exists, returns 
	 * null. Must not alter the deque!*/
	public T get(int index) {
		int i = 0;
		Node m = sentinel;
		while(i < size) {
			if(i == index) {
				return m.item;
			}
			m = m.next;
			i = i+1;
		}
		return null;
	}
	
	public T getRecursive(int index) {
		return getHelper(index, sentinel.next);
		
	}
	

	public T getHelper(int index, Node x) {
		if(index >= size) {
			return null;
		}else if (index == 0) {
			return x.item;
		}else {
			return getHelper(index-1, x.next);
		}
	}
	/** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
	public void printDeque() {
		Node cur = sentinel.next;
		while (cur != sentinel) {
			System.out.print("" + cur.item + " ");
			cur = cur.next;
		}
		System.out.println();
		
	}

}
