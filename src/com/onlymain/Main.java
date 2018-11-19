package com.onlymain;


/** IMPORTANT:
 *
 * This is the same application as a game of breakfast in com.devries.
 *
 * It is put in one "main" file to create a unified .txt file.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

class Frame extends JFrame {

    public Frame() {
        setTitle(Main.getTITLE());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Main.getDim(), Main.getDim()));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        Menu myMenu = new Menu(this);
        setJMenuBar(myMenu.bar);
        setVisible(true);
    }
}

class Menu {
    public JMenuBar bar;

    public Menu(Frame myFrame){
        this.bar = initMenu(myFrame);
    }

    /* Method to set up a menubar. */
    private JMenuBar initMenu(Frame myFrame) {
        JMenuBar myMenu = new JMenuBar();

        // This adds a listener to the new game button.
        ActionListener resetHandler = new ResetHandler(myFrame);
        JMenuItem itemNew = new JMenuItem("New Game");
        itemNew.addActionListener(resetHandler);

        // This creates the intructions.
        JMenuItem itemHelp = new JMenuItem("X begins. Get three in a row.");

        myMenu.add(itemNew);
        myMenu.add(itemHelp);

        return myMenu;
    }
}

class GameBoard extends JPanel {

    /* Initialize a field of buttons and variables for clarity */
    private JButton[][] myButtons;

    /* A board of 3x3 buttons in a 500x500 frame */
    private int boardSize = Main.getBoardSize();
    private int dimension = Main.getDim();

    public GameBoard() {
        setSize(dimension, dimension);
        setLayout(new GridLayout(boardSize, boardSize));

        // Create all buttons and place them on the board.
        myButtons = new Button[boardSize][boardSize];
        for (int u = 0; u < boardSize; u++) {
            for (int i = 0; i < boardSize; i++) {
                myButtons[i][u] = new Button(i, u);

                ActionListener ButtonHandler = new ButtonHandler();
                myButtons[i][u].addActionListener(ButtonHandler);

                this.add(myButtons[i][u]);
            }
        }
    }
}


class Button extends JButton {
    private int xPos,yPos;
    private boolean buttonPressed;

    public Button(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    /* Image getters and buttoncheckers */
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}

    public ImageIcon getIconX() {return resizeIcon((createImageIcon("buttonX.png")));}
    public ImageIcon getIconO() {return resizeIcon((createImageIcon("buttonO.png")));}
    public boolean getbuttonPressed(){return buttonPressed;}
    public void setbuttonPressed(boolean buttonPressed){this.buttonPressed = buttonPressed;}



    /* Returns an image or null if the path was invalid. */
    public ImageIcon createImageIcon(String path){
        ImageIcon imgURL = new ImageIcon(this.getClass().getResource(path));
        if (imgURL != null){
            return imgURL;
        } else{
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    /* Resize the image to fit the button. */
    private static ImageIcon resizeIcon(ImageIcon imgURL){
        Image oldIMG = imgURL.getImage();
        int newWidth = Main.getDim() / Main.getBoardSize();
        int newHeight = Main.getDim() / Main.getBoardSize();
        Image newIMG = oldIMG.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        return new ImageIcon(newIMG);

    }
}


class ButtonHandler implements ActionListener {

    /* Initialize and set variables */
    private static final byte BUTTON_X = 1;
    private static final byte BUTTON_O = 2;
    private static final byte GAME_OVER = 2;
    private static final String WIN_MESSAGE = "win";
    private static final String DRAW_MESSAGE = "draw";

    private char winner;
    private static int whoseTurn;
    private static int numberOfMoves;
    private static int[][] boardState;

    ButtonHandler(){
        boardState = new int[Main.getBoardSize()][Main.getBoardSize()];
        numberOfMoves = 0;
        whoseTurn = 1;

    }

    /* Method that controls actions when a button is pressed. */
    public void actionPerformed(ActionEvent buttonClicked) {
        Button clickedButton = (Button) buttonClicked.getSource();

        // Check which turn it is and place image accordingly.
        switch (checkWhoseTurn(clickedButton)) {
            case 0:
                clickedButton.setIcon(clickedButton.getIconX());
                boardState[clickedButton.getXPos()][clickedButton.getYPos()] = BUTTON_X;
                break;
            case 1:
                clickedButton.setIcon(clickedButton.getIconO());
                boardState[clickedButton.getXPos()][clickedButton.getYPos()] = BUTTON_O;
                break;
            case 2:
                break;
        }

        // Check if someone has won or it's a draw yet.
        whoseTurn = checkForWin() ? GAME_OVER :whoseTurn;
    }

    /* Method that keeps track of the turns.*/
    private int checkWhoseTurn(Button clickedButton){
        if(whoseTurn != 2 && !clickedButton.getbuttonPressed()){
            clickedButton.setbuttonPressed(true);
            whoseTurn = whoseTurn ==  1 ? whoseTurn - 1 : whoseTurn + 1;
            numberOfMoves++;
            return whoseTurn;
        }
        return 2;
    }

    /* Method for checking if the win condition (3 in a row) is met on a 3x3 grid. */
    private boolean checkForWin(){
        // Check horizontal lines.
        for (int i = 0; i< 3; i++) {
            if ((boardState[i][0] == boardState[i][1])
                    && (boardState[i][0] == boardState[i][2])
                    && (boardState[i][0] != 0)){
                winner = boardState[i][0] == BUTTON_X ? 'X' : 'O';
                return showMessage(WIN_MESSAGE);
            }}

        // Check vertical lines.
        for (int i = 0; i< 3; i++) {
            if ((boardState[0][i] == boardState[1][i])
                    && (boardState[0][i] == boardState[2][i])
                    && (boardState[0][i] != 0)){
                winner = boardState[0][i] == BUTTON_X ? 'X' : 'O';
                return showMessage(WIN_MESSAGE);
            }}

        // Check diagonal and anti-diagonal.
        if ((boardState[0][0] == boardState[1][1])
                && (boardState[0][0] == boardState[2][2])
                && (boardState[0][0] != 0)){
            winner = boardState[0][0] == BUTTON_X ? 'X' : 'O';
            return showMessage(WIN_MESSAGE);
        }
        else if ((boardState[0][2] == boardState[1][1])
                && (boardState[0][2] == boardState[2][0])
                && (boardState[0][2] != 0)){
            winner = boardState[0][2] == BUTTON_X ? 'X' : 'O';
            return showMessage(WIN_MESSAGE);
        }

        // Check for a draw.
        if (numberOfMoves == 9){
            showMessage(DRAW_MESSAGE);
        }

        return false;
    }

    /* Method for showing a message that corresponds to a win or a draw.*/
    private boolean showMessage(String m) {
        switch (m) {
            case WIN_MESSAGE:
                JOptionPane.showMessageDialog(null,
                        "Game over, " + winner + " won. Click new game to play again!",
                        "A game of Breakfast",
                        JOptionPane.PLAIN_MESSAGE);
                return true;
            case DRAW_MESSAGE:
                JOptionPane.showMessageDialog(null,
                        "Game over, it's a draw. Click new game to play again!",
                        "A game of Breakfast",
                        JOptionPane.PLAIN_MESSAGE);
                return true;
        }
        return false;
    }
}


class ResetHandler implements ActionListener {
    private Frame myFrame;

    public ResetHandler(Frame myFrame) {
        this.myFrame = myFrame;
    }

    /* Method that handles when menu button is pressed */
    public void actionPerformed(ActionEvent e) {
        myFrame.getContentPane().removeAll();
        resetGame(myFrame);
    }

    /* Set up a new GameBoard on reset. */
    public static void resetGame(Frame myFrame) {
        Container c = myFrame.getContentPane();
        GameBoard myBoard = new GameBoard();
        c.add(myBoard);
        myFrame.setContentPane(c);
    }
}