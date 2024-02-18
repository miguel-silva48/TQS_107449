package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(5);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test 
    public void testCount() {
        //test with a set with no elements
        assertEquals(0, setA.count(99), "count: element count not as expected.");
        //test with a set with elements
        setA.add(99);
        assertEquals(1, setA.count(99), "count: element count not as expected.");
        //test with an element not present in the set
        assertEquals(0, setA.count(88), "count: element count not as expected.");
        //test with an invalid element
        assertThrows(IllegalArgumentException.class, () -> setA.count(-3), "count: it should not be possible to count an invalid element");
        assertThrows(IllegalArgumentException.class, () -> setA.count(null), "count: it should not be possible to count an invalid element");
    }

    @Test 
    public void testContains() {
        //test with a set with no elements
        assertFalse(setA.contains(99), "contains: element not found in set when it should not be there.");
        //test with a set with elements
        setA.add(99);
        assertTrue(setA.contains(99), "contains: element not found in set when it should be there.");
        //test with an element not present in the set
        assertFalse(setA.contains(88), "contains: element found in set when it should not be there.");
        //test with an invalid element
        assertThrows(IllegalArgumentException.class, () -> setA.contains(-3), "contains: it should not be possible to check for an invalid element");
        assertThrows(IllegalArgumentException.class, () -> setA.contains(null), "contains: it should not be possible to check for an invalid element");
    }

    @Test
    public void testIntersection() {
        //test sets with existing intersection
        assertTrue(setB.intersects(setC), "intersects: sets do not intersect when they should.");
        //test an empty set with a non-empty set
        assertFalse(setA.intersects(setB), "intersects: sets intersect when they should not.");
        //test an empty set with another empty set
        assertFalse(setD.intersects(setA), "intersects: sets intersect when they should not.");
        //test sets with no intersection
        setA.add(99);
        assertFalse(setA.intersects(setB), "intersects: sets intersect when they should not.");
    }

    @Test 
    //Simple element addition and verification
    public void testAddElement() {
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> setD.add(-88), "add: invalid element should not have been added");
        assertEquals(0, setD.size(), "add: elements count not as expected.");
    }

    @Test 
    //Adding an array of elements to a set and verifiying if all elements are there
    public void testAddArray() {
        int[] elems = new int[]{10, 20, 30};
        setD.add(elems);
        for (int elem : elems) {
            assertTrue(setD.contains(elem), "add: added element not found in set.");
        }
        assertEquals(elems.length, setD.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems), "add bad array: array should not have been added");
        assertEquals(1, setD.size(), "add: elements count not as expected."); //only the first element should have been added
    }

    @Test
    //Adding an element to a full set and verifiying that the set is still the same
    public void testAddElementToFullSet() {
        
        //fill the set
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        //overflow the set
        assertThrows(IllegalArgumentException.class, () -> setA.add(77), "bounded set is full. no more elements allowed.");
        assertFalse(setA.contains(77), "add: added element found in set when not intended.");
        assertEquals(1, setA.size(), "add: elements count not as expected.");

        //attempt with a set that is already full
        assertThrows(IllegalArgumentException.class, () -> setB.add(11), "bounded set is full. no more elements allowed.");
        assertFalse(setB.contains(11), "add: added element not found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }

    @Test 
    //Adding an array to a full set and verifiying that the set is still the same
    public void testAddArrayToFullSet() {

        int[] elems = new int[]{10, 20, 30};
        int[] elems2 = new int[]{40, 50, 60};

        //fill the set
        setD.add(elems);
        for (int elem : elems) {
            assertTrue(setD.contains(elem), "add: added element not found in set.");
        }
        assertEquals(elems.length, setD.size(), "add: elements count not as expected.");

        //overflow the set
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems2),  "add array: bounded set does not have enough space.");
        for (int elem : elems2) {
            assertFalse(setD.contains(elem), "add: added element found in set when not intended.");
        }
        assertEquals(elems.length, setD.size(), "add: elements count not as expected.");

        //attempt with a set that is already full
        assertThrows(IllegalArgumentException.class, () -> setC.add(elems), "add array: bounded set does not have enough space.");
        assertEquals(2, setC.size(), "add: elements count not as expected.");
    }

    @Test
    //Adding a duplicate element to a set and verifiying that the set is still the same 
    //(can't be done to a full set, as it would throw an exception before the duplicate check)
    public void testAddDuplicateElement() {
        //add element to set
        setD.add(88);
        assertTrue(setD.contains(88), "add: added element not found in set.");
        assertEquals(1, setD.size(), "add: elements count not as expected.");

        //add duplicate element
        assertThrows(IllegalArgumentException.class, () -> setD.add(88), "duplicate value: 88");
        assertEquals(1, setD.size(), "add: elements count not as expected.");
    }

    @Test
    //Adding an array with duplicates to a set and verifiying that the set is still the same
    public void testAddArrayWithDuplicates() {
        int[] elems = new int[]{10, 20, 30};
        int[] elems2 = new int[]{30, 40};

        //add array to set
        setD.add(elems);
        for (int elem : elems) {
            assertTrue(setD.contains(elem), "add: added element not found in set.");
        }
        assertEquals(elems.length, setD.size(), "add: elements count not as expected.");

        //add array with duplicates - set should remain the same because they wont be added
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems2), "add array: duplicate value on the given array");
        assertEquals(elems.length, setD.size(), "add: elements count not as expected.");
    }
}
