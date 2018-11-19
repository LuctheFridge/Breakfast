package com.devries;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

public class Menu {
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