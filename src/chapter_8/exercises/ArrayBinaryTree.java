package chapter_8.exercises;

/**
 * R-8.16
 *
 * Let T be a binary tree with n positions that is realized with an array representation A,
 * and let f() be the level numbering function of the positions of T, as given in
 * Section 8.3.2. Give pseudocode descriptions of each of the methods
 * root, parent, left, right, isExternal, and isRoot.
 */
public class ArrayBinaryTree<E> {

    E[] A;

    public ArrayBinaryTree() {
        this.A = (E[]) new Object[20];
    }

    public E root() {
        int fp = 0;
        return A[fp];
    }

    public E parent(int fp) {
        int fq = (fp - 1) / 2;
        return A[fq];
    }

    public E left(int fq) {
        int fp = 2*fq + 1;
        return A[fp];
    }

    public E right(int fq) {
        int fp = 2*fq + 2;
        return A[fp];
    }

    public boolean isExternal(int fp) {
        return (left(fp) == null && right(fp) == null);
    }

    public boolean isRoot(E e) {
        return A[0] == e;
    }
}
