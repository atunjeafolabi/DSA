package com.dsa.recusrion;

import java.io.File;

/**
 * Code Fragment 5.5:
 * ------------------
 *  A recursive method for reporting disk usage of a ﬁle system.
 *
 *  Calculates the total disk usage (in bytes) of the portion of the ﬁle system rooted at the given path,
 *  while printing a summary akin to the standard 'du' Unix tool.
 *  */
public class DiskUsage {

    public static void main(String[] args) {
        String path = "/Users/user/Documents/DOCS";
        File root = new File(path);

        System.out.println("Disk usage in " + path + ": " + diskUsage(root) + " bytes");
    }

    public static long diskUsage(File root) {
        long total = root.length();                         // start with direct disk usage
         if (root.isDirectory()) {                          // and if this is a directory,
              for (String childname : root.list()) {        // then for each child
                   File child = new File(root, childname);  // compose full path to child
                   total += diskUsage(child);               // add child’s usage to total
              }
         }
         System.out.println(total + "\t" + root);           // descriptive output
         return total;                                      // return the grand total
    }
}
