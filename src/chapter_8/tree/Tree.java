package chapter_8.tree;

/**
 * Code Fragment 8.1:
 * -----------------
 * Definition of the Tree interface.
 *
 * An interface for a tree where nodes can have an arbitrary number of children.
 */

public interface Tree<E> extends Iterable<E> {

    Position<E> root();

    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    int numChildren(Position<E> p) throws IllegalArgumentException;

    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    int size();

    boolean isEmpty();

//    Iterator<E> iterator();

    Iterable<Position<E>> positions();
}