package com.company.LogicGame;

import com.company.LogicGame.implementation.Coordinate;

public interface Game {
    double getCurSpeed();
    Coordinate getNewFlightCoord();
    Coordinate getCurrentCoord();
    Coordinate getObstacleNormCoord();
    Coordinate getObstacleInvertCoord();
    double getPower();
    void beforeTime();
    void fistTime();
    void secondTime();
    void thirdTime();
    void fourthTime();
    void fifthTime();
    void remaneTime();
}
