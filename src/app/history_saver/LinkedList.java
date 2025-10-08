package app.history_saver;

public class LinkedList<T> implements Iterable<T>{
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public void addToEnd(T data){
        Node<T> newNode = new Node<>(data);
        
        if (size == 0){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addToFirst(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        
        if (size == 0) {
            tail = newNode;
        }
        
        size++;
    }

    public T getAt(int index){
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Node<T> cur = head;
        int counter = 0;
        while (counter < index) {
            counter++;
            cur = cur.next;
        }
        return cur.data;
    }

    public void insertAt(T data, int index){
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<T>(data);
        Node<T> cur = head;
        int counter = 0;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        }else{
            while (counter < index - 1) {
            counter++;
            cur = cur.next;
            }
            newNode.next = cur.next;
            cur.next = newNode;
            if (index == size) {
                tail = newNode;
            }
        }
        size++;
    }

    public void removeAt(int index){
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<T> cur = head;
        int counter = 0;
        if (index == 0) {
            if(size == 1){
                head = null;
                tail = null;
            }else{
                head = head.next;
            }
        }else{
            while (counter < index - 1) {
            counter++;
            cur = cur.next;
            }
            cur.next = cur.next.next;
            if (index == size - 1) {
                tail = cur;
            }
        }
        size--;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public LinkedListIterator<T> iterator(){
        return new LinkedListIterator<T>(head);
    }
}
