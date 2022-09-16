package chapter_6.exercises;

import chapter_6.stack.ArrayLeakyStack;

public class P638 {
    public static void main(String[] args) {
        ArrayLeakyStack<Integer> numbers = new ArrayLeakyStack<>();
        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        numbers.push(40);
        numbers.push(50);
        numbers.push(60);
        numbers.push(70);
        numbers.push(80);
        numbers.push(90);

        System.out.println(numbers.toString());
    }
}
