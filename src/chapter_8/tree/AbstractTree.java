package chapter_8.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract base class providing some functionality of the Tree interface.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    public boolean isInternal(Position<E> p) {

        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {

        return p == root();
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Returns the number of levels separating Position p from the root.
     * i.e the depth of p is the number of ancestors of p, other than p itself
     */
    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    /**
     * Returns the height of the tree. This approach of getting the height of
     * a tree is NOT efficient. It works, but quadratic worst-case time.
     */
    private int heightBad() {

        int h = 0;

        for (Position<E> p : positions()) {
            if (isExternal(p)) {                // only consider leaf positions
                h = Math.max(h, depth(p));
            }
        }

        return h;
    }

    /**
     * This class adapts the iteration produced by positions() to return elements.
     */
    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator = positions().iterator();

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

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {
        return preorder();                                                          // we arbitrarily use preorder as default for positions
    }

    /*
     * @return an iterable collection of positions of the tree, reported in preorder.
     */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();                             // i.e snapshot of 'visited' nodes
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);                                      // fill the snapshot recursively
        }
        return snapshot;
    }

    /*
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    /*
     * Returns an iterable collection of positions of the tree, reported in postorder.
     */
    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);                                     // fill the snapshot recursively
        }
        return snapshot;
    }

    /*
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);                                                           // for postorder, we add position p after exploring subtrees
    }
}
