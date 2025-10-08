package app.history_saver;

import java.util.NoSuchElementException;

public class Stack<T> {
    private final LinkedList<T> list = new LinkedList<>();
    
    public boolean isEmpty(){
        return list.isEmpty();
    }

    public T peek(){
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.getAt(0);
    }

    public T pop(){
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        T top = list.getAt(0);
        list.removeAt(0);
        return top;
    }

    public void push(T data){
        list.addToFirst(data);
    }

}
