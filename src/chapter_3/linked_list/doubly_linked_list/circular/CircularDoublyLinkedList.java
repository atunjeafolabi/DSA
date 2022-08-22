package chapter_3.linked_list.doubly_linked_list.circular;

/**
 * Implementation of Circular Doubly Linked List.
 *
 * Solution to problem C3.32
 */
public class CircularDoublyLinkedList<E> {

    private static class Node<E> {

        private E element;                          // ref to the element stored at this node
        private Node<E> prev;                       // ref to the prev node in the list
        private Node<E> next;                       // ref to the subsequent node in the list

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            next = n;
            prev = p;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> tail = null;                                         // guard sentinel
    private int size = 0;                                           // number of nodes in the list

    public CircularDoublyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {                              // returns but does not remove the first element
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();       // first element is beyond guard
    }

    public E last() {                               // returns but does not remove the last element
        if (isEmpty()) {
            return null;
        }

        return tail.getElement();        // last element is behind the guard
    }

    public void addFirst(E e) {                     // adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null, null);
            tail.setNext(tail);
            tail.setPrev(tail);
            size++;
            return;
        }
        addBetween(e, tail, tail.getNext());      // place just after the guard
    }

    public void addLast(E e) {                      // adds element e to the end of the list
        if (size == 0) {
            tail = new Node<>(e, null, null);
            tail.setNext(tail);
            tail.setPrev(tail);
            size++;
            return;
        }
        addBetween(e, tail, tail.getNext());      // place between the last element and guard
        tail = tail.getNext();
    }

    public E removeFirst() {                        // removes and returns the first element
        if (isEmpty()) {
            return null;                            // nothing to remove
        }
        return remove(tail.getNext());            // first element is just after the guard
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        E removed = remove(tail);                        // last element is behind the guard
        tail = tail.getPrev();

        return removed;
    }

    /**
     * Adds element e to the linked list in between the given nodes.
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /*
     * The rotate method makes the first element in the list become the last
     */
    public void rotate() {
        tail = tail.getNext();
    }

    public void rotateBackward() {
        tail = tail.getPrev();
    }

//    public static void swap(Node x, Node y) {
//        Node xNext = x.getNext();
//        Node xPrev = x.getPrev();
//
//        Node yNext = y.getNext();
//        Node yPrev = y.getPrev();
//
//        x.setNext(yNext);
//        x.setPrev(yPrev);
//
//        y.setNext(xNext);
//        y.setPrev(xPrev);
//
//        xNext.setPrev(y);
//        xPrev.setNext(y);
//
//        yNext.setPrev(x);
//        yPrev.setNext(x);
//    }

    public static CircularDoublyLinkedList concatLists(CircularDoublyLinkedList firstList, CircularDoublyLinkedList secondList) {

        CircularDoublyLinkedList newList = new CircularDoublyLinkedList();

        Node walk = firstList.tail.getNext();
        while(walk != firstList.tail) {
            newList.addFirst(walk.getElement());
            walk = walk.getNext();
        }
        // Don't forget to add the last element (i.e tail)
        newList.addFirst(walk.getElement());

        walk = secondList.tail.getNext();
        while(walk != secondList.tail) {
            newList.addLast(walk.getElement());
            walk = walk.getNext();
        }
        // Don't forget to add the last element (i.e tail)
        newList.addLast(walk.getElement());

        return newList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = tail.getNext();

        while (walk != tail) {
            sb.append(walk.getElement());
            sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(walk.getElement());
        sb.append("]");

        return sb.toString();
    }
}