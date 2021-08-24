package com.dsa.positional_list;

public interface Position<E> {

    /**
     * Returns the element stored at this position.
     *
     * @return An element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}
