package com.company.LogicGame.implementation;


import com.company.LogicGame.Constants;
import com.company.LogicGame.Flight;

import static java.lang.Math.*;

public class Flight_impl implements Flight {

    public Flight_impl(double hight_impact_penguin, double startSpeed) {
        if (hight_impact_penguin > Constants.coord_impact_up && hight_impact_penguin < Constants.coord_impact_down) {
            alpha = degrees_to_radians((hight_impact_penguin - Constants.coord_impact_down) * 90 / Constants.hight_penguin);
        }
       this.startSpeed = startSpeed;
    }

    public Flight_impl(){
        currentCoord = new Coordinate(Constants.startX, Constants.hightMountain);
    }

    @Override
    public Coordinate getNewCoord() {
        if(lifeStatus == Life_status.die  || alpha == PI / 2){
            if(currentCoord.getY() >= Constants.maxCoordY){
                currentSpeed = 0;
                currentCoord.setY(Constants.maxCoordY);
            }
            drop();
        }else
        if (currentSpeed > 0) {
            if(lifeStatus == Life_status.wound){
                startSpeed/=2;
                lifeStatus = Life_status.alive;
            }
            if (currentCoord.getY() < Constants.maxCoordY && touchSpeed == 0) {
                position_body_flight();
                currentSpeed = speed_value(startSpeed, alpha);
            } else {
                if (speedFall < PI / 10 || currentSpeed < 60) {
                    position_body_slide_X();
                }else {
                    if (speedFall < PI / 5) {
                        position_body_jump();
                        currentTime+=Constants.precision*2;
                    } else {
                        currentSpeed = 0;
                    }
                }
            }
            currentTime += Constants.precision;
        }
        return currentCoord;
    }

    @Override
    public Coordinate getCurrentCoord() {
        return currentCoord;
    }
    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public void setLife_status(Life_status life_status) {
        lifeStatus = life_status;
    }


    @Override
    public void drop() {
        if(currentCoord.getY() >= Constants.maxCoordY){
            currentSpeed = 0;
            return;
        }
        double speedDrop = 200;
        currentCoord.setY(currentCoord.getY() + Constants.precision * speedDrop);
    }

    private double degrees_to_radians(double degrees) {
        return PI * degrees / 180;
    }

    private void position_body_flight() {
        currentCoord.setX((currentCoord.getX() - startSpeed * Constants.precision * cos(alpha)));
        currentCoord.setY((currentCoord.getY() +
                startSpeed * Constants.precision * sin(alpha) + Constants.g * currentTime * currentTime / 2));
        currentSpeed = speed_value(startSpeed, alpha);
        if(currentCoord.getY() >= Constants.maxCoordY){
            currentCoord.setY(Constants.maxCoordY);
            speedFall = speed_direction(startSpeed, alpha);
        }
    }


    private double speed_direction(double startSpeed, double alpha) {
        double x = -startSpeed * cos(alpha);
        double y = startSpeed * sin(alpha) + Constants.g * currentTime;
        return acos(-x / sqrt(x * x + y * y));
    }

    private double speed_value(double startSpeed, double alpha){
        double x = -startSpeed * cos(alpha);
        double y = startSpeed * sin(alpha) + Constants.g * currentTime;
        return sqrt(x * x + y * y);
    }

    private void position_body_slide_X() {
        currentSpeed -= Constants.coef_friction * Constants.precision;
        currentCoord.setX((currentCoord.getX() - currentSpeed * Constants.precision));
    }

    private void position_body_jump(){
        if(currentCoord.getY() >= Constants.maxCoordY){
            currentTime = Constants.precision;
            currentCoord.setY((int) Constants.maxCoordY);
            alpha = alpha / 2;
            touchSpeed = currentSpeed / 2;
        }
        currentCoord.setX((currentCoord.getX() - touchSpeed * Constants.precision * cos(alpha)));
        currentCoord.setY((currentCoord.getY() +
                touchSpeed * Constants.precision * sin(alpha) + Constants.g * currentTime * currentTime / 2));
        currentSpeed = speed_value(touchSpeed, alpha);
    }



    private Coordinate currentCoord = new Coordinate(Constants.startX, Constants.startY);
    private double currentTime = Constants.precision;
    private double startSpeed = 400;
    private double currentSpeed = startSpeed;
    private Life_status lifeStatus = Life_status.alive;
    private double touchSpeed;
    private double speedFall;
    private double alpha = PI / 2;;
}