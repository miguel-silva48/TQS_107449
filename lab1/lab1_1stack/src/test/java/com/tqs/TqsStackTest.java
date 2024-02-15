package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TqsStackTest {
    private TqsStack test_stack;

    @BeforeEach
    void setup() {
        test_stack = new TqsStack();
    }

    @Test 
    // a) A stack is empty on construction.
    void Test_emptyOnBuild() {
        assertTrue(test_stack.isEmpty());
    }

    @Test
    // b) A stack has size 0 on construction.
    void Test_zeroElementsOnBuild() {
        assertEquals(0, test_stack.size());
    }

    @Test
    // c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
    void Test_push() {
        final int elements = 5;
        for (int i = 0; i < elements; i++) {
            final Object o = new Object();
            test_stack.push(o);
        }
        assertFalse(test_stack.isEmpty());
        assertEquals(elements, test_stack.size());
    }

    @Test
    // d) If one pushes x then pops, the value popped is x.
    void Test_pushAndPop() {
        final Object o = new Object();
        test_stack.push(o);
        assertEquals(o, test_stack.pop());
    }

    @Test
    //  e) If one pushes x then peeks, the value returned is x, but the size stays the same
    void Test_pushAndPeek() {
        final Object o = new Object();
        test_stack.push(o);

        int sizeAfterPush = test_stack.size();
        assertEquals(o, test_stack.peek());
        int sizeAfterPeek = test_stack.size();
        assertEquals(sizeAfterPush, sizeAfterPeek);
    }

    @Test
    // f) If the size is n, then after n pops, the stack is empty and has a size 0
    void Test_fullPop() {
        final int elements = 5;
        for (int i = 0; i < elements; i++) {
            final Object o = new Object();
            test_stack.push(o);
        }

        int currentSize = test_stack.size();
        for (int i = 0; i < currentSize; i++) {
            test_stack.pop();
        }

        assertTrue(test_stack.isEmpty());
        assertEquals(0, test_stack.size());
    }

    @Test
    // g) Popping from an empty stack does throw a NoSuchElementException [You should test for the Exception occurrence]
    void Test_invalidPop() {
        assertTrue(test_stack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> test_stack.pop());
    }

    @Test 
    // h) Peeking into an empty stack does throw a NoSuchElementException
    void Test_invalidPeek() {
        assertTrue(test_stack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> test_stack.peek());
    }

    @Test
    // i) For bounded stacks only: pushing onto a full stack does throw an IllegalStateException
    void test_fullStack() {
        final int max_size = 100;
        for (int i = 0; i < max_size; i++) {
            final Object o = new Object();
            test_stack.push(o);
        }
        final Object o = new Object();
        assertThrows(IllegalStateException.class, () -> test_stack.push(o));
    }
}
