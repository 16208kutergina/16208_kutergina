package com.company.LogicGame.implementation;

import com.company.LogicGame.Constants;
import com.company.LogicGame.Obstacle;

import java.util.Date;

public class Obstacle_impl implements Obstacle {
    private Coordinate coordPickInvert;
    private Coordinate coordPickNorm;
    private double width = Constants.widthObstacle;
    private double gap;
    private Direction dirHor = Direction.left;
    private Direction dirVert = Direction.right;
    private long lastSysTime = new Date().getTime();

    public Obstacle_impl(double gap){
        coordPickInvert = new Coordinate(Constants.maxXforMount, 0);
        coordPickNorm = new Coordinate(Constants.maxXforMount, 0 + gap);
        this.gap = gap;
    }

    @Override
    public Coordinate getCoordNormObt() {
        coordPickNorm.setX(coordPickInvert.getX());
        coordPickNorm.setY(coordPickInvert.getY() + gap);
        return coordPickNorm;
    }


    @Override
    public Coordinate getCoordInvertObt() {
        return coordPickInvert;
    }

    @Override
    public void slideHorizontal() {
        if (coordPickInvert.getX() + Constants.widthObstacle/2 >= Constants.maxXforMount) {
            dirHor = Direction.left;
        }
        if (coordPickInvert.getX() <= 5 + Constants.widthObstacle/2) {
            dirHor = Direction.right;
        }
        if (dirHor == Direction.right) {
            coordPickInvert.setX(coordPickInvert.getX() + 2);//Constants.speedMount * delTime);
        }
        if (dirHor == Direction.left) {
            coordPickInvert.setX( coordPickInvert.getX() - 2);//Constants.speedMount * delTime);
        }
    }

    @Override
    public void slideVerticale() {
        if (coordPickInvert.getY() >= Constants.maxCoordY) {
            dirVert = Direction.up;
        }
        if (coordPickInvert.getY() <= 0) {
            dirVert = Direction.down;
        }
        if (dirVert == Direction.down) {
            coordPickInvert.setY(coordPickInvert.getY() + 2);//Constants.speedMount * delTime);
        }
        if (dirVert == Direction.up) {
            coordPickInvert.setY(coordPickInvert.getY() - 2);//Constants.speedMount * delTime);
        }
    }

    @Override
    public Life_status meetingObj(Coordinate coordObj) {
        if (checkMeeting(coordObj)) {
            double y = coordObj.getY();
            if ((y > coordPickInvert.getY() - 5 && y < coordPickInvert.getY()) ||(y < coordPickNorm.getY() && y > coordPickNorm.getY() - 10)) {
                return Life_status.wound;
            }
            return Life_status.die;
        }
        return Life_status.alive;
    }

    private boolean functionInvert(double x, double y) {
        return ((x <= coordPickInvert.getX() + Constants.widthObstacle/2 && x >= coordPickInvert.getX() - Constants.widthObstacle/2) && y <= coordPickInvert.getY());
    }

    private boolean functionNorm(double x, double y) {
        return ((x <= coordPickNorm.getX() + Constants.widthObstacle/2 && x >= coordPickNorm.getX() - Constants.widthObstacle/2)  && y >= coordPickNorm.getY());

    }

    private boolean checkMeeting(Coordinate coord) {
        return (functionInvert(coord.getX(), coord.getY()) || functionNorm(coord.getX(), coord.getY()));
    }

    private enum Direction {
        up, down, left, right
    }
}
