package com.company.LogicGame.implementation;

import com.company.LogicGame.Constants;
import com.company.LogicGame.Power;

public class Power_impl implements Power {
    private double power = Constants.minPower;
    private Direction direction = Direction.up;

    @Override
    public void changePower() {
        if(power >= Constants.maxPower){
            direction = Direction.down;
        }
        if(power <= Constants.minPower){
            direction = Direction.up;
        }
        if(direction == Direction.up){
            power+=1;
        }
        if(direction == Direction.down){
            power-=1;
        }
    }

    @Override
    public double getPower() {
        return power;
    }

    private enum Direction{
        up, down
    }
}
