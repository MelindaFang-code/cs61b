package lab8_hashmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class MyHashMap<K,V> implements Map61B<K, V>  {
	
	
	private double loadFactor;
	private int size; // # of elements
	private int b; // # of buckets
	Set<K> keys = new HashSet<>();
	private ArrayList<Bucket> bucketList;
	
	class Bucket{
		ArrayList<item> itemList;
		
		Bucket(){
			itemList = new ArrayList<>();
		}
		
		void put(K key, V value) {
			itemList.add(new item(key, value));
		}
		
		V get(K key) {
			for(item x: itemList) {
				if(x.key.equals(key)) {
					return x.val;
				}
			}
			return null;			
		}
		
		V delete(K key, V val) {
			for (item i : itemList) {
                if (i.key == key && i.val == val) {
                    itemList.remove(i);
                    return val;
                }
            }
            return null;
		}
		
		public V delete(K key) {
            for (item i : itemList) {
                if (i.key == key) {
                    itemList.remove(i);
                    return i.val;
                }
            }
            return null;
        }
		
		class item{
			K key;
			V val;
			
			item(K k, V v) {
				key = k;
				val = v;
			}
		}
	}
		
	public MyHashMap() {
		this(16);
		
	}
	public MyHashMap(int initialSize) {
		this(initialSize, 0.75);		
	}
	public MyHashMap(int initialSize, double loadFactor) {
		this.loadFactor = loadFactor;
		this.b = initialSize;
		clear();
		
	}
	
	/** Removes all of the mappings from this map. */
	@Override
    public void clear() {
    	bucketList = initBucket();
    	this.size = 0;
    	this.keys.clear();;
    	
    }

    private ArrayList<Bucket> initBucket(){
    	ArrayList<Bucket> bucketsList = new ArrayList<>();
        for (int i = 0; i < this.b; i++) {
            bucketsList.add(new Bucket());
        }    
        return bucketsList;
        
    }
    
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % this.b;
    } 
    
    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
    	return keys.contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K k) {
    	if(k == null) {
    		throw new IllegalArgumentException();
    	}else if(containsKey(k)) {
    		return bucketList.get(hash(k)).get(k);
    	}
    	return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return this.size;
    }

    /* must pay attention to this!*/
    public void resize(int capacity) {
    	
    	ArrayList<Bucket> temp = initBucket();
        for (int i = 0; i < capacity; i++) {
            temp.add(new Bucket());
        }
    	for(K key: keys) {
    		int index = (key.hashCode() & 0x7fffffff) % capacity;
    		Bucket curr = temp.get(index);
    		curr.put(key, get(key));
    		
    	}
    	this.bucketList = temp;
    	this.b = capacity;
    }
    
    /* Associates the specified value with the specified key in this map. 
     * if the item with the same key is added, update the value associated with the key
     * (no duplicates) 
     */
    @Override
    public void put(K key, V value) {
    	if (key == null) {
            throw new IllegalArgumentException();
        }
        if (containsKey(key)) {
        	
        	this.bucketList.get(hash(key)).delete(key, get(key));
        	this.bucketList.get(hash(key)).put(key, value);
        }else {
		    this.bucketList.get(hash(key)).put(key, value);
		    size += 1;
		    this.keys.add(key);
        }
	    if ((double) size / b >= loadFactor) {
            resize(this.b * 2);       
        }
	        
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
    	return keys;
    }

    @Override
    /*iterates over the stored keys*/
    public Iterator<K> iterator() {
        return keys.iterator();
    }
    
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
    	if(!keys.contains(key)) {
    		return null;
    	}
    	keys.remove(key);
        size -= 1;
        return this.bucketList.get(hash(key)).delete(key);
        
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
    	if(!keys.contains(key)) {
    		return null;
    	}
    	// guarantees that only the specific key-value pair is deleted
    	V del = this.bucketList.get(hash(key)).delete(key, get(key));
    	if(del != null) {
	    	keys.remove(key);
	        size -= 1;
    	}       
        return del;
    }
}
