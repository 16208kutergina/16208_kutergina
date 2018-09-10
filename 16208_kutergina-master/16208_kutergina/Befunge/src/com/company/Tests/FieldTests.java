package com.company.Tests;

import com.company.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTests {
    Field field;

    @BeforeEach
    public void init(){
        field = new Field("Examples/Hello_world.txt");
    }

    @AfterEach
    public void delField(){
        field = null;

    }

    @Test
    public void getActualCoordXTest(){
        assertEquals(0, field.getActualCoordX());
    }

    @Test
    public void getActualCoordYTest(){
        assertEquals(0, field.getActualCoordY());
    }

    @Test
    public void pushTest(){
        Stack<Integer> stack = new Stack<>();
    stack.push(9);
    stack.push(33);
        field.push(9);
        field.push('!');
assertEquals(stack,field.getStack());
    }

    @Test
    public void popTest(){
        field.push(9);
        assertEquals(9,field.pop());
        assertEquals(0,field.pop());
    }

    @Test
    public void StepTest(){
        field.Step();
        assertEquals(1, field.getActualCoordX());
        assertEquals(0, field.getActualCoordY());
    }

    @Test
    public void getElemTest(){
        assertEquals('>',field.getElem(0,0));
    }

    @Test
    public void setElemTest(){
        field.setElem(0,0, '0');
        assertEquals('0',field.getElem(0,0));
    }

    @Test
    public void setDirectionTest(){
        field.setDirection(0, 1);
        field.Step();
        assertEquals(0, field.getActualCoordX());
        assertEquals(1, field.getActualCoordY());
    }

    @Test
    public void setActualCoordTest(){
        field.setActualCoord(1,1);
        assertEquals(1, field.getActualCoordX());
        assertEquals(1, field.getActualCoordY());
    }

    @Test
    public void correctCoordTest(){
        field.setDirection(-1,-1);
        field.Step();
        assertEquals(84, field.getActualCoordX());
        assertEquals(24, field.getActualCoordY());
    }
}
