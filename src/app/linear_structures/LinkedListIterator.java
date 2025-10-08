package app.linear_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListIterator<T> implements Iterator<T>{
    private Node<T> cur;

    LinkedListIterator(Node<T> head){
        this.cur = head;
    }


    @Override
    public boolean hasNext(){
        return (cur != null);
    }


    @Override
    public T next(){
        if (cur == null) {
            throw new NoSuchElementException();
        }
        T value = cur.data;
        cur = cur.next;
        return value;
    }
}