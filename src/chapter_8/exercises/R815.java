package chapter_8.exercises;

import chapter_8.binary_tree.LinkedBinaryTree;

public class R815 {

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

        // compute and print level number
         tree.computeLevelNumberForEntireTree();

        // compute and print level number starting from a position, p and level number
        // tree.computeLevelNumber((tree.left(tree.right(tree.root()))), 5);

    }
}
