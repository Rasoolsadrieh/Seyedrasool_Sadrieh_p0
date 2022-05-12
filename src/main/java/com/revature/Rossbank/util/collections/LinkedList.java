package com.revature.Rossbank.util.collections;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.revature.Rossbank.util.collections.serializers.LinkedListSerializerUser;

@JsonSerialize(using = LinkedListSerializerUser.class)
public class LinkedList <T> implements List<T>{

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean add(T element) {

        if(element == null) return false;

        Node<T> newNode = new Node<>(element);

        if (head == null){
            tail = head = newNode;
        } else {
            tail.nextNode = newNode; // adding to the previous tail, what the next node is
            tail = newNode; // re-assigning tail to the newNode
        }

        size++;

        return true;
    }

    @Override
    public boolean contains(T element) {

        // we need to run through all the nodes int he linked list to see whether or not the node with the data of interest (element) is contained within
        Node<T> runner = head;

        while(runner != null){
            if(runner.data.equals(element)){
                return true;
            }

            runner = runner.nextNode;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(T element) {

        if(element == null || size == 0){
            return false;
        }

        Node<T> prevNode = null;
        Node<T> currentNode = head;

        for(int i = 0; i < size; i++){ // worst case scenario you need to hit that last node
            if(currentNode.data != null && currentNode.data.equals(element)){
                if (currentNode == head){
                    head = currentNode.nextNode;
                } else {
                    prevNode.nextNode = currentNode.nextNode;
                }

                size--;
                return true;
            }
            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }


        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> currentNode = head;

        for(int i = 0; i <= index; i++){
            if(i == index){
                return currentNode.data;
            }

            currentNode = currentNode.nextNode;
        }

        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // Why might we do this?
    private static class Node<T>{
        T data;
        Node<T> nextNode;

        public Node(T data){
            this.data = data;
        }

    }
}
