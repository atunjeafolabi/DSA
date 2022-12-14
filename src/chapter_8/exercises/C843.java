package chapter_8.exercises;

import chapter_8.binary_tree.LinkedBinaryTree;

/**
 *
 *          A
 *         / \
 *        B   C
 *       /\   /\
 *      D E  F  G
 *     /    /\
 *    H    J  I
 */
public class C843 {
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();

        // add elements to left subtree
        tree.addRoot("A");
        tree.addLeft(tree.root(), "B");
        tree.addRight(tree.root(), "C");

        tree.addLeft(tree.left(tree.root()), "D");
        tree.addRight(tree.left(tree.root()), "E");

        tree.addLeft(tree.left(tree.left(tree.root())), "H");

        // add elements to right subtree
        tree.addLeft(tree.right(tree.root()), "F");
        tree.addRight(tree.right(tree.root()), "G");

        tree.addLeft(tree.left(tree.right(tree.root())), "J");
        tree.addRight(tree.left(tree.right(tree.root())), "I");

        // print all elements and their depths
        // starting from the root
        tree.depthN(tree.root(), 0);
    }
}
