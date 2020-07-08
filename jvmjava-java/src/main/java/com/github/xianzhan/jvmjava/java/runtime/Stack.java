package com.github.xianzhan.jvmjava.java.runtime;

import com.github.xianzhan.jvmjava.java.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * JVM 虚拟机栈
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class Stack<E> {

    private final int maxSize;

    private final LinkedList<E> stack;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = CollectionUtils.newLinkedList();
    }

    public void push(E ele) {
        if (stack.size() >= maxSize) {
            throw new StackOverflowError("stack overflow");
        }
        stack.push(ele);
    }

    public E pop() {
        return stack.pop();
    }

    public E top() {
        return stack.peek();
    }

    public E get(int index) {
        return stack.get(index);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public List<E> getFrames() {
        return stack;
    }

    @Override
    public String toString() {
        return "Stack{" +
               "maxSize=" + maxSize +
               ", stack=" + stack +
               '}';
    }
}
