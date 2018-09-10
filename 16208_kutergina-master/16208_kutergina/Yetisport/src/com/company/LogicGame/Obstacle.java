package com.company.LogicGame;

import com.company.LogicGame.implementation.Coordinate;
import com.company.LogicGame.implementation.Life_status;

public interface Obstacle {
    void slideHorizontal();
    void slideVerticale();
    Life_status meetingObj(Coordinate coordObj);
    Coordinate getCoordNormObt();
    Coordinate getCoordInvertObt();
}
