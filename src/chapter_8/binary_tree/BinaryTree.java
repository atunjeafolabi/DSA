package chapter_8.binary_tree;

import chapter_8.tree.Position;
import chapter_8.tree.Tree;

public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the Position of p's left child (or null if no child exists).
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> left(Position<E> position) throws IllegalArgumentException;

    /**
     * Returns the Position of p's right child (or null if no child exists).
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> right(Position<E> position) throws IllegalArgumentException;

    /**
     * Returns the Position of p's sibling (or null if no sibling exists).
     * @param position
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> sibling(Position<E> position) throws IllegalArgumentException;

}