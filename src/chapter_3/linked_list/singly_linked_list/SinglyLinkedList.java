package chapter_3.linked_list.singly_linked_list;

public class SinglyLinkedList<E> {

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

    private Node<E> head = null;                    // head node of the list (or null if empty)
    private Node<E> tail = null;                    // last node of the list (or null if empty)
    private int size = 0;                           // number of nodes in the list

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {                              // returns but does not remove the first element
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {                               // returns but does not remove the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {                     // adds element e to the front of the list
        head = new Node<>(e, head);                 // create and link a new node
        if (size == 0) {
            tail = head;                            // special case: new node becomes tail also
        }
        size++;
    }

    public void addLast(E e) {                      // adds element e to the end of the list
        Node<E> newest = new Node<>(e, null);   // node will eventually be the tail
        if (isEmpty()) {
            head = newest;                          // special case: previously empty list
        }
        else {
            tail.setNext(newest);                   // new node after existing tail
        }
        tail = newest;                              // new node becomes the tail
        size++;
    }

    public E removeFirst() {                        // removes and returns the first element
        if (isEmpty()) {
            return null;                            // nothing to remove
        }

        E answer = head.getElement();
        head = head.getNext();                      // will become null if list had only one node
        size--;

        if (size == 0) {
            tail = null;                            // special case as list is now empty
        }

        return answer;
    }

    /**
     * Returns the element in the node just before the tail node
     * i.e #->#->#->P->#->null
     *
     * @return
     */
    public E secondToLast(){
        if(isEmpty()) {
            return null;
        }

        Node<E> pointer = head;
        Node<E> prev = null;
        while(pointer.getNext() != null) {
            prev = pointer;
            pointer = pointer.getNext();
        }

        return prev.getElement();
    }

    public static SinglyLinkedList concatLists(SinglyLinkedList firstList, SinglyLinkedList secondList) {

        SinglyLinkedList newList = new SinglyLinkedList<>();

        Node walk = firstList.head;

        for(int i=0; i < firstList.size(); i++) {
            newList.addFirst(walk.getElement());
            walk = walk.getNext();
        }

        walk = secondList.head;
        for(int i=0; i < secondList.size(); i++) {
            newList.addLast(walk.getElement());
            walk = walk.getNext();
        }

        return newList;
    }

    /*
     * Reverse a linked list using iteration method
     *
     * Original List:   #->#->#->#->#->#->null
     * Reversed List:   null<-#<-#<-#<-#<-#<-#
     */
    public static void reverseIterative(SinglyLinkedList list) {
        Node currentNode = list.head;
        Node nextNode;
        Node prevNode = null;
        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(prevNode);
            prevNode = currentNode;
            currentNode = nextNode;
        }

        // swap head and tail
        Node head = list.head;
        list.head = list.tail;
        list.tail = head;
    }

    /*
     * Alternative method for reversing a linked list using two loops.
     *
     * In this technique, a temporary list in used to store the removed elements.
     * The elements in the temporary list are then re-assigned to the original list.
     *
     */
    public static void reverseIterative2(SinglyLinkedList list) {
        SinglyLinkedList temp = new SinglyLinkedList<>();
        while (list.head != null) {
            temp.addFirst(list.removeFirst());
        }

        while(temp.head != null) {
            list.addLast(temp.removeFirst());
        }
    }

    /**
     * Alternative method for reversing a linked list using recursion
     */
    public void reverse() {
        if (!isEmpty()) {
            E nodeElement = removeFirst();
            reverse();
            addLast(nodeElement);
        }
    }

    // As implemented in textbook bearing in mind Type erasure in java
//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (getClass() != o.getClass()) return false;    // Ensure that the parameter o is an instance of the SinglyLinkedList class (or an appropriate subclass)
//        SinglyLinkedList other = (SinglyLinkedList) o; // use nonparameterized type
//        if (size != other.size) return false;
//        Node walkA = head; // traverse the primary list
//        Node walkB = other.head; // traverse the secondary list
//        while (walkA != null) {
//            if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
//            walkA = walkA.getNext();
//            walkB = walkB.getNext();
//        }
//        return true; // if we reach this, everything matched successfully
//    }

    // This also still works even when the formal type declaration E is included. This is more intuitive
    public boolean equals(SinglyLinkedList<E> otherList) {
        if (otherList == null) {
            return false;
        }
        if (getClass() != otherList.getClass()) {
            return false;                                                       // Ensure that the parameter o is an instance of the SinglyLinkedList class (or an appropriate subclass)
        }
        SinglyLinkedList<E> other = otherList;
        if (size != other.size) {
            return false;
        }

        Node<E> walkA = head;                                                   // traverse the primary list
        Node<E> walkB = other.head;                                             // traverse the secondary list
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) {
                return false;                                                   //mismatch
            }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }

        return true;                                                            // if we reach this, everything matched successfully
    }

    @Override
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // always use inherited Object.clone() to first create the initial(shallow) copy
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();        // safe cast
        if (size > 0) {                                                         // we need independent chain of nodes
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext();                                      // walk through remainder of original list
            Node<E> otherTail = other.head;                                     // remember most recently created node
            while (walk != null) {                                              // make a new node storing same element
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest);                                      // link previous node to this one
                otherTail = newest;
                walk = walk.getNext();
            }
        }
        return other;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = head;

        while (walk != null) {
            sb.append(walk.getElement());
            if (walk != tail) {
                sb.append(", ");
            }
            walk = walk.getNext();
        }
        sb.append("]");

        return sb.toString();
    }
}