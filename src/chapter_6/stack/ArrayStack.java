package chapter_6.stack;

/**
 * Code Fragment 6.2:
 * -----------------
 * Array-based implementation of the Stack interface.
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private static final int CAPACITY = 1000;
    private int top = -1;
    private E[] data;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(final int capacity) {
        // In java, type parameter E cannot be instantiated directly,
        // so we instantiate the generic Object class.
        // this.data = new E[capacity];  is not allowed
        // Instead, we use new Object[capacity] and then cast it to type E[]
        this.data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    }

    @Override
    public void push(E e) {
        if (data.length == size()) {
            throw new IllegalStateException("Stack is full");
        }

        data[++top] = e;
    }

    @Override
    public E pop() {
        if (this.isEmpty()) {
            return null;
        }

        E e = data[top];
        data[top] = null;
        top--;
        return e;
    }

    @Override
    public E top() {
        if (this.isEmpty()) {
            return null;
        }

        return data[top];
    }

    @Override
    public int size() {
        return this.top + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    /*
     * Problem R-6.4
     *
     * Transfers all elements from stack S onto stack T,
     * so that the element that starts at the top of S is the first to be inserted onto T,
     * and the element at the bottom of S ends up at the top of T
     */
    public static <E> Stack<E> transfer(Stack<E> stackS, Stack<E> stackT) {
        if (stackS.isEmpty()) {
            return null;
        }

        while (stackS.size() > 0) {
            stackT.push(stackS.pop());
        }

        return stackT;
    }

    /*
     * Problem R-6.5
     *
     * Remove all the elements in a stack recursively.
     */
    public void recursiveRemoveAll() {
        if (!isEmpty()) {
            pop();
            recursiveRemoveAll();
        }
    }

    @Override
    public String toString() {
        String str = "";
        while (size() != 0) {
            str = str + " " + pop() + ", ";
        }

        return str;
    }

    /**
     * Sample usage of the ArrayStack
     */
    public static class Main {
        public static void main(String[] args){
            // Using ArrayStack
    //        Stack<Integer> integerStack = new ArrayStack<>();
    //        integerStack.push(10);
    //        integerStack.push(15);
    //        integerStack.push(12);
    //
    //        System.out.println(integerStack.pop());
    //        System.out.println(integerStack.top());

            // Transfer the elements of stackS onto stackT
            Stack<String> stackS = new ArrayStack<>();
            Stack<String> stackT = new ArrayStack<>();

            stackS.push("Red");
            stackS.push("Green");
            stackS.push("Blue");
            stackS.push("Yellow");

            ArrayStack.transfer(stackS, stackT);
            System.out.println(stackT);

            // Remove all elements recursively
            ((ArrayStack<String>) stackT).recursiveRemoveAll();
            System.out.println("Size of stackT after popping all should be zero: " + stackT.size());

            // Using Linked Stack
            Stack<Integer> integerStack = new LinkedStack<>();
            integerStack.push(10);
            integerStack.push(15);
            integerStack.push(12);

            System.out.println(integerStack.pop());
            System.out.println(integerStack.top());
        }
    }
}
