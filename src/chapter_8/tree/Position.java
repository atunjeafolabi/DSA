package chapter_8.tree;

public interface Position<E> {

    /**
     * Returns the element stored at this position.
     *
     * @return An element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
