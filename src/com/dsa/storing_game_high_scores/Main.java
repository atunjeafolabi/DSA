package com.dsa.storing_game_high_scores;

/**
 * DSA in Java Textbook, Sixth Edition (By Michael T. Goodrich, Roberto Tamassia and Michael H. Goldwasser)
 * Chapter 3.1: Storing Game Entries in an Array
 */
public class Main {

    public static void main(String[] args) {
        GameEntry game1 = new GameEntry("John", 500);
        GameEntry game2 = new GameEntry("Sally", 600);
        GameEntry game3 = new GameEntry("Mike", 400);
        GameEntry game4 = new GameEntry("Ann", 450);
//        GameEntry game5 = new GameEntry("Adam", 800);

        Scoreboard newScoreboard = new Scoreboard(10);

        // Add games to newScoreboard
        newScoreboard.add(game1);
        newScoreboard.add(game2);
        newScoreboard.add(game3);
        newScoreboard.add(game4);

        // Display all the entries the board currently contains
        System.out.println(newScoreboard);

        // Remove one entry at index 2, i.e Mike
        newScoreboard.remove(2);

        System.out.println("===New Scoreboard after removal===" + "\n");
        System.out.println(newScoreboard);

        // This should throw IndexOutOfBoundsException
        // newScoreboard.remove(10);
    }
}
