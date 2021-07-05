package com.dsa.insertion_sort;

public class InsertionSort {
    /** Insertion-sort of an array of characters into nondecreasing order */
    public static void main(String[] args) {

        // Sample character array
        char[] data = new char[7];
        data[0] = 'H';
        data[1] = 'G';
        data[2] = 'M';
        data[3] = 'B';
        data[4] = 'C';
        data[5] = 'C';
        data[6] = 'E';

        System.out.println(insertionSort(data));
    }

    public static char[] insertionSort(char[] data){
        int n = data.length;

        for (int k = 1; k < n; k++) {// begin with second character

            char cur = data[k]; // time to insert cur=data[k]
            int j = k; // ﬁnd correct index j for cur

            while (j > 0 && data[j-1] > cur) { // thus, data[j-1] must go after cur
                data[j] = data[j-1]; // slide data[j-1] rightward
                j--; // and consider previous j for cur
            }
            data[j] = cur; // this is the proper place for cur
        }

        return data;
    }
}
