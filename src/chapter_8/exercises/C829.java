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
public class C829 {
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

        // internal path length of the entire tree, starting from root
        System.out.println("Internal Path Length: " + tree.pathLengthInternal(tree.root(), 0));
        System.out.println("External Path Length: " + tree.pathLengthExternal(tree.root(), 0));

    }
}
