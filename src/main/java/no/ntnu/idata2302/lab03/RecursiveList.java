/*
 * This file is part of NTNU's IDATA2302 Lab 03.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;

/**
 * Linked list implemented using recursion
 */
public class RecursiveList<T> extends Sequence<T> {

    private RNode<T> head;

    public RecursiveList() {
        super();
        this.head = null;
    }

    @Override
    public int length() {
        return lengthFrom(head);
    }

    private int lengthFrom(RNode<T> start) {
        if (start == null)
            return 0;
        return 1 + lengthFrom(start.next);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public T get(int index) throws InvalidIndex {
        return this.getNode(this.head, index).item;
    }

    private RNode<T> getNode(RNode<T> start, int index) throws InvalidIndex {
        if (start == null)
            throw new IllegalStateException("Empty List");
        if (index <= 0)
            throw new InvalidIndex(index);
        if (index == 1)
            return start;
        if (start.next == null)
            throw new InvalidIndex(index);
        return getNode(start.next, index - 1);
    }

    @Override
    public void set(int index, T item) throws InvalidIndex {
        this.getNode(this.head, index).item = item;
    }

    @Override
    public void insert(int index, T item) throws InvalidIndex {
        // TODO: Implement this operation
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public void remove(int index) throws InvalidIndex {
        if (index < 1) throw new IllegalArgumentException("Invalid index");
        removeAfter(head, index-1);
    }

    private void removeAfter(RNode<T> start, int index) throws InvalidIndex {
        if (index == 0) {
            head = start.next;

        } else if (index == 1) {
            start.next = start.next == null
                ? null
                : start.next.next;

        } else if (index > 1) {
            removeAfter(start.next, index-1);

        } else {
            throw new IllegalStateException("Cannot remove: Broken chain.");
        }
    }

    @Override
    public int search(T item) {
        return searchFrom(head, item, 1);
    }

    private int searchFrom(RNode<T> start, T item, int index) {
        if (start == null) return 0;
        if (start.item == item) return index;
        return searchFrom(start.next, item, index+1);
    }

}

class RNode<T> {

    T item;
    RNode<T> next;

    RNode(T item, RNode<T> next) {
        this.item = item;
        this.next = next;
    }

}
