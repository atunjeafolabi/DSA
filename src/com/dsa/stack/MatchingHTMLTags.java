package com.dsa.stack;

/**
 * Code Fragment 6.8:
 * -----------------
 * Method for testing if every opening tag has a matching closing tag in HTML string.
 */
public class MatchingHTMLTags {

    public static void main(String[] args){
        String html = "<body> <center> <h1> The Little Boat </h1> </center> <p> The storm tossed the little boat " +
                "like a cheap sneaker in an old washing machine. The three drunken fishermen were used to such treatment, " +
                "of course, but not the tree salesman, who even as a stowaway now felt that he had overpaid for the voyage. " +
                "</p> <ol> <li> Will the salesman die? </li> <li> What color is the boat? </li> <li> And what about Naomi? </li> </ol> </body>";

        System.out.println("Are all tags matched? " + isHTMLMatched(html));
    }

    public static boolean isHTMLMatched(String html) {
        Stack<String> buﬀer = new LinkedStack<>();
        int j = html.indexOf('<');                                // ﬁnd ﬁrst ’<’ character (if any)
        while (j != -1) {
            int k = html.indexOf('>', j + 1);      // ﬁnd next ’>’ character
            if (k == -1)
                return false;                                   // invalid tag
            String tag = html.substring(j + 1, k);              // strip away < >
            if (!tag.startsWith("/"))                           // this is an opening tag
                buﬀer.push(tag);
            else {                                              // this is a closing tag
                if (buﬀer.isEmpty())
                    return false; // no tag to match
                if (!tag.substring(1).equals(buﬀer.pop()))
                    return false;                               // mismatched tag
            }
            j = html.indexOf('<', k + 1);       // ﬁnd next ’<’ character (if any)
        }
        return buﬀer.isEmpty();                              // were all opening tags matched?
    }
}

