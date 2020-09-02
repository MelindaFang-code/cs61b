package lab06;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnionFindTest {

	UnionFind u1 = new UnionFind(8);

	@Test
	public void testUnion() {
		u1.union(0,1);
		u1.union(2,3);
		u1.union(1,2);
		assertEquals(1, u1.parent(0));
		assertEquals(3, u1.parent(1));
		assertEquals(3, u1.parent(2));
		assertEquals(-4, u1.parent(3));
		
		u1.union(0, 2);
		assertEquals(3, u1.parent(0));//test if path compression works.
		
		assertEquals(4, u1.sizeOf(0));
        assertEquals(4, u1.sizeOf(1));
        assertEquals(4, u1.sizeOf(2));
        assertEquals(4, u1.sizeOf(3));
	}
		
		
		
	}


