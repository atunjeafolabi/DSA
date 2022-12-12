package chapter_8.binary_tree;

import chapter_8.tree.AbstractTree;
import chapter_8.tree.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    @Override
    public Position<E> sibling(Position<E> position) throws IllegalArgumentException {
        Position<E> parent = parent(position);

        if (parent == null) {
            return null;                                                           // p must be the root
        }

        if (position == left(parent)) {
            return right(parent);
        }

        return left(parent);
    }

    @Override
    public int numChildren(Position<E> position) {
        int count = 0;

        if (left(position) != null) {
            count++;
        }

        if (right(position) != null) {
            count++;
        }

        return count;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> position) {
        List<Position<E>> snapshot = new ArrayList<>(2);            // max capacity of 2

        if (left(position) != null) {
            snapshot.add(left(position));
        }

        if (right(position) != null) {
            snapshot.add(right(position));
        }

        return snapshot;
    }

    /*
     * Returns an iterable collection of positions of the tree,
     * reported in inorder.
     */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);                                   // fill the snapshot recursively
        }
        return snapshot;
    }

    /*
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    protected void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }

        snapshot.add(p);

        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    /*
     * Overrides positions to make inorder the default order
     * for binary trees.
     */
    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }

    /**
     * Euler tour of the entire binary tree
     */
    public Iterable<Position<E>> eulerTourBinary() {
        List<Position<E>> snapshot = new ArrayList<>();
        eulerTourBinarySubtree(root(), snapshot);

        return snapshot;
    }

    /**
     * Algorithm eulerTourBinary for performing an Euler tour traversal
     * of a subtree rooted at position p of a binary tree.
     *
     * It is a simple combination of pre-order, in-order and post-order applied to a binary tree
     */
    private void eulerTourBinarySubtree(Position<E> p, List<Position<E>> snapshot) {
        // perform pre-visit action
        snapshot.add(p);

        if (left(p) != null) {
            eulerTourBinarySubtree(p, snapshot);
        }

        // perform in-visit action
        snapshot.add(p);

        if (right(p) != null) {
            eulerTourBinarySubtree(p, snapshot);
        }

        // perform post-visit action
        snapshot.add(p);
    }

    /**
     * Computes the level number of all positions in the tree
     * starting from the root.
     */
    public void computeLevelNumberForEntireTree() {
        // i.e level number f(p) = 0 for root
        computeLevelNumber(root(), 0);
    }

    /**
     * R-8.15
     *
     * Compute level number f(p) of a position p using euler tour traversal
     */
    public void computeLevelNumber(Position<E> p, int fp) {

        System.out.println("Level No for " + p.getElement() + ": " + fp);

        if (left(p) != null) {
            // int fp = 2*fq + 1;
            computeLevelNumber(left(p), 2*fp + 1);
        }

        if (right(p) != null) {
            // int fp = 2*fq + 2;
            computeLevelNumber(right(p), 2*fp + 2);
        }
    }
}
