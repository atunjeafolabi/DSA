package chapter_8.tree;

import chapter_6.queue.LinkedQueue;
import chapter_6.queue.Queue;

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
     * a tree is NOT efficient. It works, but quadratic worst-case time, O(n2).
     */
    private int heightBad() {

        int h = 0;

        for (Position<E> p : positions()) {
            if (isExternal(p)) {                                                // only consider leaf positions
                h = Math.max(h, depth(p));
            }
        }

        return h;
    }

    /*
     * Returns the height of the subtree rooted at Position p.
     * exactly as defined in the textbook.
     *
     * Running time:    O(n)
     *
     * If the method is initially called on the root of T, it will eventually
     * be called once for each position of T.  This is because the root
     * eventually invokes the recursion on each of its children, which
     * in turn invokes the recursion on each of their children,
     * and so on.
     */
    public int height(Position<E> p) {
        int h = 0;                                                  // base case if p is external

        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }

        return h;
    }

    /**
     * This definition appears easier to reason about than the
     * definition of height above, though they look very similar.
     *
     * Running time:    O(n)
     */
    public int height2(Position<E> p) {
        int h = 0;

        for (Position<E> c : children(p)) {
            h = Math.max(h, height(c));
        }

        return 1 + h;
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
     * @return an iterable collection of positions of the tree,
     * reported in preorder.
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
     *
     * Running Time:    O(n)
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    /*
     * Returns an iterable collection of positions of the tree,
     * reported in postorder.
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
     *
     * Running Time:    O(n)
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);                                    // for postorder, we add position p after exploring subtrees
    }

    /**
     * Returns an iterable collection of positions of
     * the tree in breadth-first order.
     */
    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());

            while (!fringe.isEmpty()) {
                Position<E> position = fringe.dequeue();
                snapshot.add(position);

                for (Position<E> child : children(position)) {
                    fringe.enqueue(child);
                }
            }
        }

        return snapshot;
    }

    /**
     * Prints all elements of tree without indentation
     */
    public void printPreorder(AbstractTree<E> T) {
        for (Position<E> p : T.preorder()) {
            System.out.println(p.getElement());
        }
    }

    /*
     * Prints all elements of tree with indentation
     * This runs in O(n2) worst-case time but there is a better way that runs in O(n)
     */
    /*

    public static void printPreorderIndent(Tree<E> T) {
        for (Position<E> p : T.preorder()) {
            System.out.println(p.getElement());
            System.out.println(spaces(2*T.depth(p)) + p.getElement());
        }

    }*/

    /**
     * Code Fragment 8.23
     *
     * Efficient recursion for printing indented version of a pre-order traversal.
     * Prints preorder representation of subtree of T rooted at p having depth d.
     * This runs in O(n)
     *
     * To print an entire tree T, the recursion should be started with
     * form printPreorderIndent(T, T.root(), 0).
     */
    public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d) {

        System.out.println(spaces(2*d) + p.getElement());                             // indent based on d

        for (Position<E> c : T.children(p)) {
            printPreorderIndent(T, c, d+1);                                             // child depth is d+1
        }
    }

    private static String spaces(int times) {
        return " ".repeat(times);
    }

    /*
     * Prints labeled representation of subtree of T rooted at p having depth d.
     *
     * For the whole tree, supply parameters: p equal to root(), and use empty instance of ArrayList for path
     * i.e printPreorderLabeled(T, T.root(), arrayList)
    */
    public static <E> void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path) {
        int d = path.size();                                // depth equals the length of the path

        System.out.print(spaces(2*d));               // print indentation, then label
        for (int j=0; j < d; j++) {
            System.out.print(path.get(j) + (j == d-1 ? " " : "."));
        }
        System.out.println(p.getElement());

        path.add(1);                                        // add path entry for first child
        for (Position<E> c : T.children(p)) {
            printPreorderLabeled(T, c, path);
            path.set(d, 1 + path.get(d));                   // increment last entry of path
        }

        path.remove(d);                                     // restore path to its incoming state
    }

    /*
     * Code Fragment 8.25:
     *
     * Recursive computation of disk space for a tree. (this is a postorder traversal)
     * We assume that each tree element reports the local space used at that position.
     */
    /*

    public static int diskSpace(Tree<Integer> T, Position<Integer> p) {
        int subtotal = p.getElement();                      // we assume element represents space usage

        for (Position<Integer> c : T.children(p)) {
            subtotal += diskSpace(T, c);
        }
        return subtotal;
    }

    */


    /*
     * Code Fragment 8.26:
     *
     * Method that prints parenthetic string representation of a tree.
     * (this is a preorder traversal)
     */
    public static <E> void parenthesize(Tree<E> T, Position<E> p) {
        System.out.print(p.getElement());

        if (T.isInternal(p)) {
            boolean firstTime = true;
            for (Position<E> c : T.children(p)) {
                System.out.print((firstTime ? " (" : ", "));                         // determine proper punctuation
                firstTime = false;                                                   // any future passes will get comma
                parenthesize(T, c);                                                  // recur on child
            }
            System.out.print(")");
        }
    }
}
