package chapter_7.positional_list.IterableLinkedPositionalList;

import chapter_7.positional_list.Position;
import chapter_7.positional_list.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Code Fragment: 7.14 nested within the LinkedPositionalList class definition of Code Fragments 7.9–7.12
 * ------------------------------------------------------------------------------------------------------
 * Support for providing iterations of positions and elements of a LinkedPositionalList.
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {
        private E element;                                                          // reference to the element stored at this node
        private Node<E> prev;                                                       // reference to the previous node in the list
        private Node<E> next;                                                       // reference to the subsequent node in the list

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException {
            if (next == null)                                       // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private class PositionIterator implements Iterator<Position<E>> {

        private String oddOrEven;
        private Position<E> cursor;                                       // position of the next element to report
        private Position<E> recent = null;                                          // position of last reported element

        PositionIterator() {
            this.cursor = first();
        }

        PositionIterator(String oddOrEvenIndex) {
            this.oddOrEven = oddOrEvenIndex;

            if (oddOrEvenIndex == "odd") {
                this.cursor = first();
            }

            if (oddOrEvenIndex == "even") {
                this.cursor = after(first());
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() {
            if (cursor == null) {
                throw new NoSuchElementException("Nothing to get");
            }

            recent = cursor;
            cursor = after(cursor);

            // i.e next after next if alternateIterator is odd or even
            if (oddOrEven != null && cursor != null) {
                cursor = after(cursor);
            }

            return recent;
        }

        @Override
        public void remove() {
            if (recent == null) {
                throw new IllegalStateException("Nothing to remove");
            }

            LinkedPositionalList.this.remove(recent);
            recent = null;                                                          // do not allow remove again until next is called
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {

        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    /*
     * Returns an iterable representation of the list's positions.
     */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    /*
     * This class adapts the iteration produced by positions() to return elements.
     */
    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator;

        public ElementIterator() {
            positionIterator = new PositionIterator();
        }

        public ElementIterator(String oddOrEvenIndex) {
            positionIterator = new PositionIterator(oddOrEvenIndex);
        }

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    /*
     * Returns an iterator of the elements stored in the list.
     */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /*
     * R-7.16
     *
     * Describe how to implement a method, alternateIterator(), for a positional list that returns an
     * iterator that reports only those elements having even index in the list.
     */
    public Iterator<E> alternateIterator() {
        return new ElementIterator("even");                     // argument can be even, odd or nothing
    }

    // instance variables of the LinkedPositionalList
    private Node<E> header;                                                     // header sentinel
    private Node<E> trailer;                                                    // trailer sentinel
    private int size = 0;                                                       // number of elements in the list

    /**
     * Constructs a new empty list.
     */
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);                       // create header
        trailer = new Node<>(null, header, null);                        // trailer is preceded by header
        header.setNext(trailer);                                                // header is followed by trailer
    }

    // private utilities
    /*
     * Validates the position and returns it as a node.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p;                                             // safe cast
        if (node.getNext() == null) {                                           // convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    /*
     * Returns the given node as a Position (or null, if it is a sentinel).
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;                                                        // do not expose user to the sentinels
        }
        return node;
    }

    // public accessor methods
    /*
     * Returns the number of elements in the linked list.
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the first Position in the linked list (or null, if empty).
     */
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the last Position in the linked list (or null, if empty).
     */
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the Position immediately before Position p (or null, if p is first).
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Returns the Position immediately after Position p (or null, if p is last).
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    // private utilities
    /**
     * Adds element e to the linked list between the given nodes.
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ); // create and link a new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    // public update methods

    /**
     * Inserts element e at the front of the linked list and returns its new Position.
     */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());                             // just after the header
    }

    /*
     * Inserts element e at the back of the linked list and returns its new Position.
     */

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);                           // just before the trailer
    }

    /**
     * R-7.11
     *
     * Alternative implementation of addLast()
     *
     * Describe an implementation of the positional list methods addLast and addBe-fore realized by using only methods
     * in the set {isEmpty, first, last, before, after, addAfter, addFirst}.
     */
    public Position addLastAlt(E e) {
        Node<E> lastNode = validate(last());

        return addAfter(lastNode, e);
    }

    /*
     * Inserts element e immediately before Position p, and returns its new Position.
     */

    public Position<E> addBefore(Position<E> p, E e)
            throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * R-7.11
     *
     * Alternative implementation of addBefore()
     */
    public Position<E> addBeforeAlt(Position<E> p, E e) throws IllegalStateException {
        Node nodeBeforeP = validate(before(p));
        return addAfter(nodeBeforeP, e);
    }
    /*
     * Inserts element e immediately after Position p, and returns its new Position.
     */

    public Position<E> addAfter(Position<E> p, E e)
            throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /*
     * Replaces the element stored at Position p and returns the replaced element.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    /*
     * Removes the element stored at Position p and returns it (invalidating p).
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);                                      // help with garbage collection
        node.setNext(null);                                         // and convention for defunct node
        node.setPrev(null);
        return answer;
    }

    /**
     * Code Fragment 7.15:
     * Java code for performing insertion-sort on a positional list.
     */
    public static void insertionSort(PositionalList<Integer> list) {
        Position<Integer> marker = list.first();
        while (marker != list.last()) {
            Position<Integer> pivot = list.after(marker);
            int value = pivot.getElement();
            if (value > marker.getElement()) {
                marker = pivot;
            } else {
                Position<Integer> walk = marker;
                while (walk != list.first() && list.before(walk).getElement() > value) {
                    walk = list.before(walk);
                }
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
    }

    /*
     * R-7.12
     *
     * Suppose we want to extend the PositionalList abstract data type with a method, indexOf(p), that returns the
     * current index of the element stored at position p. Show how to implement this method using only other
     * methods of the Positional-List interface (not details of our LinkedPositionalList implementation).
     */
    @Override
    public int indexOf(Position<E> p) throws IllegalArgumentException {
        Node<E> nodeP = validate(p);
        Node<E> walk = validate(first());
        for (int i = 0; i < size(); i++) {
            if (nodeP == walk) {
                return i;
            }
            walk = walk.getNext();
        }
        return -1;
    }

    /*
     * R-7.13
     *
     * Suppose we want to extend the PositionalList abstract data type with a method, ﬁndPosition(e), that returns the
     * first position containing an element equal to e (or null if no such position exists). Show how to implement
     * this method using only existing methods of the PositionalList interface
     * (not details of our LinkedPositionalList implementation).
     */
    @Override
    public Position<E> findPosition(E e) {
        Node<E> walk = (Node) first();
        for (int i = 0; i < size(); i++) {
            if (walk.getElement() == e) {
                return position(walk);
            }
            walk = walk.getNext();
        }
        return null;
    }

    /**
     * C-7.36
     *
     * Suppose we want to extend the PositionalList interface to include a method, positionAtIndex(i), that returns the
     * position of the element having index i (or throws an IndexOutOfBoundsException, if warranted). Show how to
     * implement this method, using only existing methods of the PositionalList interface,
     * by traversing the appropriate number of steps from the front of the list.
     *
     */
    public Position positionAtIndex(int i) {

        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException(" Invalid index");
        }

        Position t = first();
        for (int k = 0; k < i; k++) {
            t = after(t);
        }

        return t;
    }

    /**
     * C-7.37
     *
     * Alternative method to C-7.36
     *
     * Repeat the previous problem, but use knowledge of the size of the list to
     * traverse from the end of the list that is closest to the desired index.
     */
    public Position positionAtIndex2(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException(" Invalid index");
        }

        Position t;
        if(i < size() / 2) {
            t = first();
            for (int k = 0; k < i; k++) {
                t = after(t);
            }
        } else {
            t = last();
            for (int k = size() - 1; k > i; k--) {
                t = before(t);
            }
        }

        return t;
    }

    /**
     * C-7.39
     *
     * Suppose we want to extend the PositionalList abstract data type with a method, moveToFront(p), that moves the
     * element at position p to the front of a list (if not already there), while keeping the relative order of the
     * remaining elements un-changed. Show how to implement this method using only existing methods of the
     * PositionalList interface (not details of our LinkedPositionalList implementation).
     */
    public void moveToFront(Position<E> p) {
        if (indexOf(p) > 0) {
            E e = remove(p);
            addFirst(e);
        }
    }

    /**
     * C-7.40
     *
     * Redo the previous problem, but providing an implementation within the
     * class LinkedPositionalList that does not create or destroy any nodes.
     */
    public void moveToFrontAlt(Position p) {
        Node<E> node = validate(p);

        if (node != first()) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            header.getNext().setPrev(node);
            node.setNext(header.getNext());
            header.setNext(node);
            node.setPrev(header);
        }
    }

    /**
     * C-7.44
     *
     * Describe a method for performing a card shuffle of a list of 2n elements, by converting it into two lists.
     * A card shuffle is a permutation where a list L is cut into two lists, L 1 and L 2 , where L 1 is the first
     * half of L and L 2 is the second half of L, and then these two lists are merged into one by taking the
     * first element in L 1 , then the first element in L 2 , followed by the second element in L 1 ,
     * the second element in L 2 , and so on.
     */
    public LinkedPositionalList<E> cardShuffle(LinkedPositionalList<E> L) {
        LinkedPositionalList<E> L1 = new LinkedPositionalList<>();
        LinkedPositionalList<E> L2 = new LinkedPositionalList<>();

        int size = L.size;

        for (int i = 0; i < size / 2; i++) {
            L1.addLast(L.remove(L.first()));
        }

        for (int i = 0; i < size / 2; i++) {
            L2.addLast(L.remove(L.first()));
        }

        System.out.println("List L1: " + L1.toString());
        System.out.println("List L2: " + L2.toString());

        while (L1.size > 0 && L2.size > 0) {
            L.addLast(L1.remove(L1.first()));
            L.addLast(L2.remove(L2.first()));
        }

        return L;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

}
