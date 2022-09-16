package chapter_6.exercises;

import chapter_6.stack.LinkedLeakyStack;

public class P639 {
    public static void main(String[] args) {
        LinkedLeakyStack<String> leakyStack = new LinkedLeakyStack<>(4);
        leakyStack.push("red");
        leakyStack.push("green");
        leakyStack.push("blue");
        leakyStack.push("yellow");
        leakyStack.push("cyan");

        System.out.println(leakyStack.size());

        System.out.println(leakyStack.pop());
        System.out.println(leakyStack.pop());
        System.out.println(leakyStack.pop());
        System.out.println(leakyStack.pop());
//        System.out.println(leakyStack.pop());

        System.out.println(leakyStack.size());
    }
}
