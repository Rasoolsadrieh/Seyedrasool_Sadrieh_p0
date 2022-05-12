package com.revature.Rossbank.util.collections;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;

@JsonSerialize(using = ArrayListSerializer.class)
public class ArrayList<T> implements List<T> {

    protected int size;
    protected Object[] elements;

    public ArrayList() {
        elements = new Object[16];
    }

    public ArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return true
     */
    @Override
    public boolean add(T element) {
        // TODO: IMPLEMENT ME TO ADD TO THE ARRAY LIST
        resizeBackingArrayIfNeeded(); // must implement the potential need for resizing the array
        return true;
    }

    /**
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element e
     * such that (o==null ? e==null : o.equals(e)).
     *
     * @param element element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(T element) {
       // TODO: IMPLEMENT ME TO FIND OUT IF THE ARRAY CONTAINS AN ELEMENT
        return false;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If this list does not contain the element, it is unchanged. More formally, removes the
     * element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i)))
     * (if such an element exists). Returns true if this list contained the specified element.
     *
     * @param element element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(T element) {
        // TODO: (Optional) EXTRA CHALLENGE TO REMOVE ELEMENT FROM THE ARRAY
        return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public T get(int index) {
        // TODO: Return the appropriate value at the index of
        return null;
    }


    protected void resizeBackingArrayIfNeeded() {
        //TODO: resize the array
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}