package com.devries;

public class Main {

    /** Author: Luc de Vries
     * Title: A Game of Breakfast
     *
     * This application is a simple implementation of
     * the dutch game "Boter, Kaas & Eieren". It is
     * made as the assignment for the Java-course by
     * YoungCapital & PluralSight.com.
     */

    /* Variable declarations */
    private static final String TITLE = "A Game of Breakfast";
    private static final int DIM = 500;
    private static final int BOARDSIZE = 3;

    /* Getters for Dimension and boardSize */
    static int getDim(){return DIM;}
    static int getBoardSize(){return BOARDSIZE;}
    static String getTITLE(){return TITLE;}

    public static void main(String[] args) {

        // Initialize a window.
        Frame myFrame = new Frame();

        // Reset the board for first use.
        ResetHandler.resetGame(myFrame);

    }
}
