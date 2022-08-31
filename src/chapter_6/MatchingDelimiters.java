package chapter_6;

/**
 * Code Fragment 6.7:
 * ------------------
 * Method for matching delimiters in an arithmetic expression.
 */
public class MatchingDelimiters {

    public static void main (String[] args){
//        String matchedExpression = "[(x+y)+(m-n) - (2+3y)]";
        String mismatchedExpression = "[(x+y)+(m-n)) - (2+3y)]";

        System.out.println("Are brackets matched? " + isMatched(mismatchedExpression));
    }

    public static boolean isMatched(String expression) {
        final String opening = "{[(";
        final String closing = "}])";

        Stack<Character> buffer = new LinkedStack<>();

        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                buffer.push(c);
            } else if (closing.indexOf(c) != -1) {
                if (buffer.isEmpty()) {
                    return false;
                }

                if (closing.indexOf(c) != opening.indexOf(buffer.pop())) {
                    return false;
                }
            }
        }

        return buffer.isEmpty();
    }
}
