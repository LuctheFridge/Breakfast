package com.devries;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

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