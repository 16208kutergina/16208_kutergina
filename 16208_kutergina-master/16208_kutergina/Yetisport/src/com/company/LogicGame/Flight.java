package com.company.LogicGame;

import com.company.LogicGame.implementation.Coordinate;
import com.company.LogicGame.implementation.Life_status;

public interface Flight {
    Coordinate getNewCoord();
    Coordinate getCurrentCoord();
    double getCurrentSpeed();
    void setLife_status(Life_status life_status);
    void drop();
   }
