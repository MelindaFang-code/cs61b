package proj2AB_bearMaps;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ArrayHeapMinPQTest {

	@Test
    public void testAdd() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("1", 1);
        pq.add("6", 6);
        pq.add("3", 3);
        pq.add("5", 5);
        pq.add("5p", 5);
        pq.add("5pp", 5);
        pq.add("1p", 1);
        pq.add("6p", 6);
        pq.add("7", 7);
        pq.add("7p", 1);
        pq.add("8", 8);
        pq.add("3p", 3);
        assertEquals(12, pq.size());
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("1", 1);
        pq.add("6", 6);
        pq.add("3", 3);
        pq.add("5", 5);
        pq.add("5p", 5);
        pq.add("5pp", 5);
        pq.add("1p", 1);
        pq.add("6p", 6);
        pq.add("7", 7);
        pq.add("7p", 1);
        pq.add("8", 8);
        pq.add("3p", 3);
        assertEquals("1", pq.getSmallest());
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("1", 1);
        pq.add("6", 6);
        pq.add("3", 3);
        pq.add("5", 5);
        pq.add("5p", 5);
        
        pq.add("1p", 1);
        pq.add("6p", 6);
        pq.add("7", 7);
        pq.add("7p", 7);
        pq.add("8", 8);
        pq.add("3p", 3);
        assertEquals("1", pq.removeSmallest());
        assertEquals(10, pq.size());
        assertEquals("1p", pq.getSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("Test1", 3);
        pq.add("Test2", 1);
        pq.add("Test3", 2);
        pq.add("Test4", 2);
        pq.changePriority("Test3", 0);
        pq.changePriority("Test4", 5);
        assertEquals("Test3", pq.getSmallest());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElement() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("Test1", 3);
        pq.add("Test2", 1);
        pq.add("Test3", 2);
        pq.changePriority("Test4", 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("Test1", 3);
        pq.add("Test1", 2);
    }

}
