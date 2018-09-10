package com.company.Painter.implementanion;

import com.company.LogicGame.implementation.Coordinate;
import com.company.Painter.Penguin;

import javax.swing.*;
import java.awt.*;


public class Penguin_impl extends JLabel implements Penguin {
    private double prevY;
    private double prevX;
    private Image wait = new ImageIcon("src/images/penguin_wait.png").getImage();
    private Image drop = new ImageIcon("src/images/penguin_fall.png").getImage();
    private Image up = new ImageIcon("src/images/penguin_up.png").getImage();
    private Image stright = new ImageIcon("src/images/penguin_stright.png").getImage();
    private Image down = new ImageIcon("src/images/penguin_down.png").getImage();
    private Image curImg = wait;

    public Image getImg() {
        return curImg;
    }

    public void imgDrop(){curImg = drop;}

    public void direction(Coordinate coord, double currentSpeed) {
        double d =  (prevY - coord.getY());
        if(prevX != coord.getX()) {
            if (d > 0) {
                curImg = up;
            } else {
                if (d < 0) {
                    curImg = down;
                } else {
                    curImg = stright;
                }
            }
        }else {
            imgDrop();
        }
        if (currentSpeed == 0) {
            curImg = stright;
        }
        prevX = coord.getX();
        prevY = coord.getY();
    }


}
