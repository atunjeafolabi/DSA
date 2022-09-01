package chapter_6.stack;

/**
 * Sample usage of the ArrayStack
 */
public class Main {
    public static void main(String[] args){
        // Using ArrayStack
//        Stack<Integer> integerStack = new ArrayStack<>();
//        integerStack.push(10);
//        integerStack.push(15);
//        integerStack.push(12);
//
//        System.out.println(integerStack.pop());
//        System.out.println(integerStack.top());

        // Using Linked Stack
        Stack<Integer> integerStack = new LinkedStack<>();
        integerStack.push(10);
        integerStack.push(15);
        integerStack.push(12);

        System.out.println(integerStack.pop());
        System.out.println(integerStack.top());
    }
}
