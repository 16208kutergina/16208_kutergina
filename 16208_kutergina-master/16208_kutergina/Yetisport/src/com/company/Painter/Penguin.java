package com.company.Painter;

import com.company.LogicGame.implementation.Coordinate;

import java.awt.*;

public interface Penguin {
    Image getImg();
    void imgDrop();
    void direction(Coordinate coord, double currentSpeed);
}
