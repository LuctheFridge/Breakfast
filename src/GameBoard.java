import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel implements ActionListener {
    private byte myTurn = 0;
    private JButton[] myButtons;

    public GameBoard(){
        setVisible(true);
        setSize(500,500);
        setLayout(new GridLayout(3, 3)); // A board of 3x3 tiles.

        myButtons = new Button[9];
        for (int i = 0; i < myButtons.length; i++) {
            myButtons[i] = new Button();
            myButtons[i].addActionListener(this);
            this.add(myButtons[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if(!clickedButton.getTest()) {
            switch (myTurn) {
                case 0:
                    clickedButton.setIcon(clickedButton.getIconX());
                    myTurn++;
                    break;
                case 1:
                    clickedButton.setIcon(clickedButton.getIconO());
                    myTurn--;
                    break;
            }
            clickedButton.setTest(true);
        }

    }

}
