package com.company;

import java.util.Stack;

public interface Redactor {
    int getActualCoordX();
    int getActualCoordY();
    int pop();
    void push(char c);
    void push(int n);
    void Step();
    char getElem(int x, int y);
    void setElem(int x, int y, char elem);
    void setDirection(int x, int y);
    void setActualCoord(int x, int y);
    Stack<Integer> getStack();
    public int getDirectionX();
    public int getDirectionY();

}
