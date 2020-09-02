package es.datastructur.synthesizer;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
    	// TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    	this.first = 0;
    	this.last = 0;
    	this.fillCount = 0;
    	rb = (T[])new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     *
     * @param x
     */
    @Override
    public void enqueue(T x) {
    	
        if(isFull()) {
        	throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount ++;
        last += 1;
        if (last == capacity()) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
    	if(isEmpty()) {
    		throw new RuntimeException("Ring buffer underflow");
    	}
    	T returnValue = rb[first];
    	rb[first] = null;
    	fillCount --;
    	first ++;
    	if (first == capacity()) {
            first = 0;
        }
        return returnValue;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
    	if(isEmpty()) {
    		throw new RuntimeException("Ring buffer underflow");
    	}
    	return rb[first];
        
    }
    
    
    private class BufferIterator<T> implements Iterator<T>{
    	private int wizPos;
    	private int size;
    	public BufferIterator() {
    		wizPos = first;
    		size = fillCount;
    	}

		@Override
		public boolean hasNext() {
			return size > 0;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T returnItem = (T)rb[wizPos];
			size --;
			wizPos ++;
			if (wizPos == capacity()) {
	            wizPos = 0;
	        }
			return returnItem;
			
		}
    	
    	
    }	
    
    @Override
    public Iterator<T> iterator(){
    	return new BufferIterator<T>();
    }

    

    @Override
    public boolean equals(Object other) {
       if(this == other) {
    	   return true;
       }else if(other == null){
    	   return false;
       }else if(other.getClass() != this.getClass()) {
    	   return false;
       }else {
    	   ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
    	   if(o.fillCount() != this.fillCount() || this.capacity()!= o.capacity()) {
    		   return false;
    	   }
    	   Iterator<T> a = this.iterator();
    	   Iterator<T> b = o.iterator();
    	   while(a.hasNext() && b.hasNext()) {
    		   if(a.next() != b.next()) {
    			   return true;
    		   }
    	   }
    	   return true;
    	   
       }
    }

   
} 


