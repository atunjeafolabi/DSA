package chapter_8.binary_tree;


import java.util.ArrayList;
import java.util.List;
import chapter_8.tree.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected Node<E> root = null;
    private int size = 0;

    //---------------- nested Node class ----------------
    protected static class Node<E> implements Position<E> {

        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return this.element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    /*
     * Factory function to create a new node storing the element
     */
    protected Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node(element, parent, left, right);
    }

    /*
     * Validates the position and returns it as a node.
     */
    protected Node<E> validate(Position<E> position) throws IllegalArgumentException {
        if (!(position instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type!");
        }

        Node<E> node = (Node<E>) position;

        if (node.getParent() == node) {         // defined convention for defunct/removed node
            throw new IllegalArgumentException("This position is not longer in the tree");
        }

        return node;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Position<E> root() {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> position) throws IllegalStateException {
        Node<E> node = validate(position);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getRight();
    }

    /*
     * Places element e at the root of an empty tree and returns its new Position.
     */
    public Position<E> addRoot(E element) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is no empty!");
        }

        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    /*
     * Creates a new left child of Position p storing element e; returns its Position.
     */
    public Position<E> addLeft(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> parent = validate(position);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("This position already has a left child!");
        }

        Node<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /*
     * Creates a new right child of Position p storing element e; returns its Position.
     */
    public Position<E> addRight(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> parent = validate(position);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException("This position already jas a right child!");
        }

        Node<E> right = createNode(element, parent, null, null);
        parent.setRight(right);
        size++;
        return right;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element.
     */
    public E set(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> node = validate(position);
        E removedElement = node.getElement();
        node.setElement(element);
        return removedElement;
    }

    /*
     * Attaches trees t1 and t2 as left and right subtrees of external position.
     */
    public void attach(Position<E> position, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(position);

        if (isInternal(position)) {
            throw new IllegalArgumentException("The position must be a leaf!");
        }

        size += t1.size() + t2.size();

        if (!t1.isEmpty()) {                                                // attach t1 as left subtree of node
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()) {                                                // attach t2 as right subtree of node
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /*
     * Removes the node at a position and replaces it with its child, if any.
     */
    public E remove(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);

        if (numChildren(position) == 2) {
            throw new IllegalArgumentException("This position has two children!");
        }

        Node<E> parent = node.getParent();
        Node<E> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        if (child != null) {
            child.setParent(parent);
        }

        if (node == root) {
            root = child;
        } else {
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;
        E removedElement = node.getElement();
        node.setElement(null);
        node.setParent(null);
        node.setLeft(null);
        node.setRight(node);                                                // defined convention to remove node

        return removedElement;
    }

    /**
     * Utility wrapper function to count all left leaves in
     * the entire tree
     */
    public int countLeftLeavesOfEntireTree() {
        return countLeftLeaves(root());
    }

    /**
     * R-8.5
     *
     * Describe an algorithm, relying only on the BinaryTree operations,
     * that counts the number of leaves in a binary tree that are the
     * left child of their respective parent.
     */
    public int countLeftLeaves(Position<E> p) {
        int count = 0;

        for(Position<E> c : children(p)) {
            if(c == left(p) && isExternal(c)) {
                count += 1;
            }
            count += countLeftLeaves(c);
        }

        return count;
    }

    /**
     * C-8.33
     *
     * Check if two trees are Isomorphic for a Binary Tree
     *
     * (To Revisit)
     */
    public static <E> boolean isIsomorphic(Position<E> p1, Position<E> p2) {

        if (p1 == null && p2 == null) {
            return true;
        }

        if (p1 == null || p2 == null) {
            return false;
        }

        if (p1.getElement() != p2.getElement()) {
            return false;
        }

        Node<E> n1 = (Node<E>) p1;
        Node<E> n2 = (Node<E>) p2;

        boolean caseOne = isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right);
        boolean caseTwo = isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right, n2.left);

        return caseOne || caseTwo;
    }

    /**
     * C-8.36
     *
     * Add support in LinkedBinaryTree for a method, pruneSubtree(p), that removes the
     * entire subtree rooted at position p, making sure to maintain an accurate count
     * of the size of the tree. What is the running time of your implementation?
     */
    public void pruneSubtree(Position<E> p) {
        // subtract the size of subtree rooted at p from the size of the entire tree
        size -= sizeSubtree(p);

        Node<E> n = (Node<E>) p;
        Node<E> nParent = (Node<E>) parent(p);

        // set the child of p's parent to be null
        if (left(parent(p)) == p) {
            nParent.setLeft(null);
        }

        if (right(parent(p)) == p) {
            nParent.setRight(null);
        }

        // also link p to itself to delete/detach it from its parent
        n.setParent(n);
    }

    /**
     * Get the size of the subtree rooted at p
     */
    private int sizeSubtree(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>();
        inorderSubtree(p, snapshot);

        return snapshot.size();
    }

    /**
     * C-8.37
     *
     * Add support in LinkedBinaryTree for a method, swap(p, q), that has the effect of restructuring
     * the tree so that the node referenced by p takes the place of the node referenced by q, and
     * vice versa. Make sure to properly handle the case when the nodes are adjacent.
     *
     * (To Revisit) - Not working yet
     */
    public void swap(Position<E> p, Position<E> q) {

        Node<E> np = validate(p);
        Node<E> nq = validate(q);

        Node<E> parentP = np.getParent();
        Node<E> leftP = np.getLeft();
        Node<E> rightP = np.getRight();

        Node<E> parentQ = nq.getParent();
        Node<E> leftQ = nq.getLeft();
        Node<E> rightQ = nq.getRight();


        // i.e p and q have a parent-child relationship
        if (np.getLeft() == nq || nq.getLeft() == np) {

            return;
        }

        if (np.getRight() == nq || nq.getRight() == np) {

            return;
        }

        // begin swapping
        nq.setParent(parentP);

        if (np == np.getParent().getLeft()) {
            parentP.setLeft(nq);
        }

        if (np == np.getParent().getRight()) {
            parentP.setRight(nq);
        }

        //
        np.setParent(parentQ);

        if (nq == nq.getParent().getLeft()) {
            parentQ.setLeft(np);
        }

        if (nq == nq.getParent().getRight()) {
            parentQ.setRight(np);
        }
    }


    /**
     * C-8.43
     *
     * Give an O(n)-time algorithm for computing the depths of all
     * positions of a tree T, where n is the number of nodes of T.
     *
     * Optimization Tip used here:
     * Instead of using depth(p), to calculate depth, we
     * simply add one as we recursively go down in depth.
     */
    public void depthN(Position<E> p, int n) {
        int nextDepth = n + 1;
        if (isRoot(p)) {
            System.out.println("Element: " + p.getElement() + " depth: " + 0);
        }
        for (Position<E> c : children(p)) {
            System.out.println("Element: " + c.getElement() + " depth: " + nextDepth);
            depthN(c, nextDepth);
        }
    }

    /**
     * C-8.44
     *
     * The balance factor of an internal position p of a proper binary tree
     * is the difference between the heights of the right and left subtrees
     * of p. Show how to specialize the Euler tour traversal of
     * Section 8.4.6 to print the balance factors of all the
     * internal nodes of a proper binary tree
     *
     * TODO: Suggested Optimization Tip:
     * This algorithm works but it can be improved by avoiding the use of height(p)
     * to determine the height of each position. Instead, the height of each
     * position can be determined by starting from the height of the leaves
     * as 0 and then returning h+1 from the recursive calls.
     */
    public void balanceFactor(Position<E> p) {
        int hLeft = 0;
        int hRight = 0;

        if (isInternal(p)) {
            if (left(p) != null)
                hLeft = height(left(p));

            if (right(p) != null)
                hRight = height(right(p));

            System.out.println("Element: " + p.getElement());

            System.out.println("Balance factor: " + (hLeft - hRight));
        }

        if (left(p) != null) {
            balanceFactor(left(p));
        }

        if (right(p) != null) {
            balanceFactor(right(p));
        }
    }
}
