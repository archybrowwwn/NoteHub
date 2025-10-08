package app.history_saver;

import java.util.NoSuchElementException;

public class LinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;
    public int size = 0;


    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        }else{
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void pop(){
        if (size == 0){
            throw new IndexOutOfBoundsException("List is empty");
        }
        if (size == 1){
            head = null;
            tail = null;
            size--;
        }else{
            Node<T> current = head;
            while(current.next != tail){
                current = current.next;
            }
            current.next = null;
            tail = current;
            size--;
        }
    }

    public Node<T> get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of list");
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    public int getSize(){
        return size;
    }

    public ListIterator<T> getIterator() {
        return new LinkedListIterator();
	}

	private class LinkedListIterator implements ListIterator<T> {
		private Node<T> currentNode = null;

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
            currentNode = (currentNode == null) ? head : currentNode.next;
            return currentNode.data;
		}

		@Override
		public boolean hasNext() {
			if (currentNode == null) return head != null;
            return currentNode.next != null;
		}
	}
}