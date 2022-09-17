package chapter_3.linked_list.circularly_linked_list;

public class CircularlyLinkedList<E> {

    private static class Node<E> {

        private E element;                          // ref to the element stored at this node
        private Node<E> next;                       // ref to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> tail = null;                    // last node of the list (or null if empty)
    private int size = 0;                           // number of nodes in the list

    public CircularlyLinkedList() {
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
        return tail.getNext().getElement();         // head is after the tail
    }

    public E last() {                               // returns but does not remove the last element
        if (isEmpty()) {
            return null;
        }

        return tail.getElement();
    }

    public void rotate() {                          // rotate the first element to the back of the list
        if (tail != null)                           // do nothing if empty
            tail = tail.getNext();                  // old "implicit head" becomes new tail
    }

    public void addFirst(E e) {                     // adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);                     // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {                      // adds element e to the end of the list
        addFirst(e);                                // insert e at front of list
        tail = tail.getNext();                      // now new element becomes the tail
    }

    public E removeFirst() {                        // removes and returns the first element
        if (isEmpty( )) {
            return null;                            // nothing to remove
        }
        Node<E> head = tail.getNext( );
        if (head == tail) {
            tail = null;                            // must be the only node left
        } else {
            tail.setNext(head.getNext( ));         // removes head from the list
        }
        size--;

        return head.getElement( );
    }

    /*
     * R-3.15
     *
     * Implement the equals() method for the CircularlyLinkedList class, assuming that two lists are equal if they
     * have the same sequence of elements, with corresponding elements currently at the front of the list.
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        CircularlyLinkedList other = (CircularlyLinkedList) o;
        if (size != other.size) {
            return false;
        }

        Node walkA = tail;
        Node walkB = other.tail;

        for (int i = 0; i < size; i++) {
            if (!walkA.getElement().equals(walkB.getElement())) {
                return false;
            }

            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }

        return true;
    }

    /**
     * C-3.34
     *
     * Implementation of the clone() method.
     */
    public CircularlyLinkedList clone() throws CloneNotSupportedException {
        CircularlyLinkedList other = new CircularlyLinkedList();

        if (size > 0) {
            other.tail = new Node(tail.getElement(), null);
            Node<E> walk = tail.getNext();
            Node<E> temp = other.tail;

            for (int i = 0; i < size; i++) {
                /*
                 * when the second to the last element of the new (i.e cloned) list is reached,
                 * set its next to the tail
                */
                if (i == size-1) {
                    temp.setNext(other.tail);
                    other.size++;
                    break;
                }

                Node<E> newest = new Node<>(walk.getElement(), null);
                temp.setNext(newest);
                temp = newest;
                walk = walk.getNext();
                other.size++;
            }
        }
        return other;
    }

    /*
     * C-3.29
     *
     * Suppose you are given two circularly linked lists, L and M. Describe an algorithm for telling if L and M
     * store the same sequence of elements (but perhaps with different starting points).
     */
//    public static boolean isSameSequenceOfElements(CircularlyLinkedList listL, CircularlyLinkedList listM) {
//        Node walkL = listL.tail.getNext();
//        Node walkM = listM.tail.getNext();
//
//        // TODO:check if list are also of same length
//
//        while (walkL != listL.tail) {
//            if (listL.tail.getNext().getElement().equals(walkM.getElement())) {
//                listM.tail = walkM;
//            }
//
//            if (!walkL.getElement().equals(walkM.getElement())) {
//                return false;
//            }
//
//            walkL = walkL.getNext();
//            walkM = walkM.getNext();
//        }
//
//        return true;
//    }

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