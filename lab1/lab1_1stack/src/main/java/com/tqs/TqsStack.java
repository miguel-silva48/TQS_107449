package com.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack {
    private final int MAX_SIZE = 100;
    private LinkedList<Object> stack;

    public TqsStack() {
        this.stack = new LinkedList<>();
    }

    public void push(Object o) {
        if (this.stack.size() < MAX_SIZE) {
            this.stack.add(o);
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    public Object pop() {
        if (this.stack.size() > 0) {
            return this.stack.removeLast();
        } else {
            throw new NoSuchElementException("Stack is empty");
        }
    }

    public Object peek() {
        if (this.stack.size() > 0) {
            return this.stack.getLast();
        } else {
            throw new NoSuchElementException("Stack is empty");
        }
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
