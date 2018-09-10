package com.company;

import com.company.LogicGame.implementation.Level;
import com.company.Painter.Background;


import javax.swing.*;


public class Yetysport extends JFrame{

    public Yetysport(Level level, Menu menu) {
        JFrame f = new JFrame("Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(750, 480);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        Background background = new Background(level, menu, f);
        background.setFocusable(true);
        background.addKeyListener(background.getKeyListener());
        background.addMouseListener(background.getMouseListener());
        f.add(background);
        f.setVisible(true);
    }

}


