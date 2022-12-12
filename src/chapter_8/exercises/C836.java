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
public class C836 {
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

        // entire tree size before pruning
        System.out.println("Tree size before pruning: " + tree.size());

        // prune subtree rooted at B
        tree.pruneSubtree(tree.left(tree.root()));

        // entire tree size after pruning
        System.out.println("Tree size after pruning: " + tree.size());

        // should now throw NullPointerException since B no longer exists
        // System.out.println(tree.left(tree.root()).getElement());

    }
}
