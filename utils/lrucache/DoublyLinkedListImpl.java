package lrucache;

import java.util.NoSuchElementException;

public class DoublyLinkedListImpl {
 
    private Node head;
    private Node tail;
    private int size;
     
    public DoublyLinkedListImpl() {
        size = 0;
    }
    /**
     * this class keeps track of each element information
     * @author java2novice
     *
     */
    public class Node {
        int element;
        int key;
        Node next;
        Node prev;

        @Override
        public String toString() {
            return String.format(" NODE{%d:%d}", key, element);
        }

        public Node(int element, int key, Node next, Node prev) {
            this.key = key;
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     * returns the size of the linked list
     * @return
     */
    public int size() { return size; }
     
    /**
     * return whether the list is empty or not
     * @return
     */
    public boolean isEmpty() { return size == 0; }
     
    /**
     * adds element at the starting of the linked list
     * @param element
     */
    public Node addFirst(int element, int key) {
        Node tmp = new Node(element, key, head, null);
        return addFirst(tmp);
    }

    public Node addFirst(Node tmp) {
        if(head != null ) {
            head.prev = tmp;
            tmp.next = head;
        }
        head = tmp;
        if(tail == null) {
            tail = tmp;
        }
        size++;
        System.out.println("adding: " + tmp.key + ", "+tmp.element);
        return tmp;
    }
     
    /**
     * adds element at the end of the linked list
     * @param element
     */
    public void addLast(int element, int key) {
         
        Node tmp = new Node(element, key, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
     
    /**
     * this method walks forward through the linked list
     */
    public void iterateForward(){
         
        System.out.println(".....iterating forward......");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }
     
    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward(){
         
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.prev;
        }
    }
     
    /**
     * this method removes element from the start of the linked list
     * @return
     */
    public int removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }

    /**
     * this method removes element from the end of the linked list
     * @return
     */
    public Node removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        size--;
        tmp.prev = null;
        System.out.println("deleted: "+tmp.element);
        return tmp;
    }

    public void remove(Node node) {
        if (node == null) {
            if (size == 0) throw new NoSuchElementException();
        }
        if (node == head) {
            //first
            removeFirst();
            return;
        }
        if (node == tail) {
            removeLast();
            return;
        }

        //guaranteed that node.prev and node.next are non null
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = null;
        node.prev = null;
    }


    public static void main(String a[]){
        DoublyLinkedListImpl dll = new DoublyLinkedListImpl();
        dll.addFirst(10, 0);
        dll.addFirst(34, 0);
        Node n56 = dll.addFirst(56, 0);
        dll.addFirst(364, 0);
        dll.iterateForward();
        dll.remove(n56);
        dll.iterateForward();
    }
}