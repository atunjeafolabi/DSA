package chapter_3.linked_list.doubly_linked_list;

/**
 * Implementation of Doubly Linked List using only one guard node.
 *
 * Solution to problem C3.31
 */
public class DoublyLinkedListTwo<E> {

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

    private Node<E> guard;                                         // guard sentinel
    private int size = 0;                                           // number of nodes in the list

    public DoublyLinkedListTwo() {
        guard = new Node<>(null, null, null);
        guard.setNext(guard);
        guard.setPrev(guard);
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
        return guard.getNext().getElement();       // first element is beyond guard
    }

    public E last() {                               // returns but does not remove the last element
        if (isEmpty()) {
            return null;
        }

        return guard.getPrev().getElement();        // last element is behind the guard
    }

    public void addFirst(E e) {                     // adds element e to the front of the list
        addBetween(e, guard, guard.getNext());      // place just after the guard
    }

    public void addLast(E e) {                      // adds element e to the end of the list
        addBetween(e, guard.getPrev(), guard);      // place between the last element and guard
    }

    public E removeFirst() {                        // removes and returns the first element
        if (isEmpty()) {
            return null;                            // nothing to remove
        }
        return remove(guard.getNext());            // first element is just after the guard
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(guard.getPrev());           // last element is behind the guard
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

    public static void swap(Node x, Node y) {
        Node xNext = x.getNext();
        Node xPrev = x.getPrev();

        Node yNext = y.getNext();
        Node yPrev = y.getPrev();

        x.setNext(yNext);
        x.setPrev(yPrev);

        y.setNext(xNext);
        y.setPrev(xPrev);

        xNext.setPrev(y);
        xPrev.setNext(y);

        yNext.setPrev(x);
        yPrev.setNext(x);
    }

    public static DoublyLinkedListTwo concatLists(DoublyLinkedListTwo firstList, DoublyLinkedListTwo secondList) {

        DoublyLinkedListTwo newList = new DoublyLinkedListTwo();

        Node walk = firstList.guard.getNext();
        while(walk != firstList.guard) {
            newList.addFirst(walk.getElement());
            walk = walk.getNext();
        }

        walk = secondList.guard.getNext();
        while(walk != secondList.guard) {
            newList.addLast(walk.getElement());
            walk = walk.getNext();
        }

        return newList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = guard.getNext();

        while (walk != guard) {
            sb.append(walk.getElement());
            sb.append(", ");
            walk = walk.getNext();
        }
        sb.append("]");

        return sb.toString();
    }
}