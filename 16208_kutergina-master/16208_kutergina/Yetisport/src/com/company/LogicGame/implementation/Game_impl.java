package com.company.LogicGame.implementation;

import com.company.LogicGame.Flight;
import com.company.LogicGame.Game;
import com.company.LogicGame.Obstacle;
import com.company.LogicGame.Power;

public class Game_impl implements Game {
    private Flight flight = new Flight_impl();
    private Obstacle obstacle;
    private Power power = new Power_impl();
    private boolean flagMeeting = false;

    public Game_impl(Level level){
        if(level == Level.easy){
            obstacle = new Obstacle_impl(300);
        }
        if(level == Level.norm){
            obstacle = new Obstacle_impl(200);
        }
        if(level == Level.hard){
            obstacle = new Obstacle_impl(100);
        }
    }


    @Override
    public double getCurSpeed() {
        return flight.getCurrentSpeed();
    }

    @Override
    public Coordinate getNewFlightCoord() {
        return flight.getNewCoord();
    }

    @Override
    public Coordinate getCurrentCoord() {
        return flight.getCurrentCoord();
    }

    @Override
    public Coordinate getObstacleNormCoord() {
        return obstacle.getCoordNormObt();
    }

    @Override
    public Coordinate getObstacleInvertCoord() {
        return obstacle.getCoordInvertObt();
    }

    @Override
    public double getPower() {
        return power.getPower();
    }

    @Override
    public void beforeTime() {
//горизонтальная ездилка
        obstacle.slideHorizontal();
    }

    @Override
    public void fistTime() {
//вертикальная ездилка
        obstacle.slideVerticale();
    }

    @Override
    public void secondTime() {
//настройка скорости
        power.changePower();
    }

    @Override
    public void thirdTime() {

    }

    @Override
    public void fourthTime() {
//прыжок с горы
        flight.drop();
    }

    @Override
    public void fifthTime() {
//удар
        flight = new Flight_impl(flight.getCurrentCoord().getY(),power.getPower());
    }

    @Override
    public void remaneTime() {
        flight.getNewCoord();
        if(!flagMeeting){
            Life_status ls = obstacle.meetingObj(flight.getCurrentCoord());
            if(ls != Life_status.alive) {
                flight.setLife_status(ls);
                flagMeeting = true;
            }
        }

    }
}
