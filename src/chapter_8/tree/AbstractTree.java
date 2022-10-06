package chapter_8.tree;

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
}
