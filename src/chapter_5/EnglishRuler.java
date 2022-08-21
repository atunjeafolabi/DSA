package com.dsa.recusrion;

/**
 * Code Fragment 5.2
 * -----------------
 * A recursive implementation of a method that draws a ruler.
 */
public class EnglishRuler {

    public static void main(String[] args) {
        EnglishRuler.drawRuler(3, 3);
    }

    public static void drawRuler(int nInchesInRuler, int majorTickLength) {
        drawLine(majorTickLength, 0);
        for (int j = 1; j <= nInchesInRuler; j++) {
            drawInterval(majorTickLength - 1);
            drawLine(majorTickLength, j);
        }
    }

    public static void drawInterval(int centralLength) {
        if (centralLength >= 1) {
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }

    public static void drawLine(int tickLength) {
        drawLine(tickLength, - 1);
    }

    public static void drawLine(int tickLength, int tickLabel) {
        for (int j = 0; j < tickLength; j++) {
            System.out.print("-");
        }

        if (tickLabel >= 0) {
            System.out.print(" " + tickLabel);
        }

        System.out.print("\n");
    }
}

