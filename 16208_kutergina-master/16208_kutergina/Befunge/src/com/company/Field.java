package com.company;

import java.util.Stack;
import java.util.logging.Logger;

/**
 * Класс для навигации по редактору и стеку
 * @autor Анна Кутергина
 */
public class Field implements Redactor{
    private static Logger log = Logger.getLogger(Field.class.getName());
    /**Поле хранящее программу на языке Befunge*/
    private char[][] field;
    /**Стек данных*/
    private Stack<Integer> stack;
    /**Текущее положение на поле field*/
    private int[] actualCoord;
    /**Направление движения по полю field*/
    private int [] direction;

    /**Конструктор редактора
     * @param filename - имя файла программы на языке Befunge
     * устанавливается курсор на (0, 0)
     * направление (1, 0)*/
    public Field(String filename){
        stack = new Stack<>();
        field = Parser.getProgram(filename);
        actualCoord = new int[2];
        direction = new int[2];
        setActualCoord(0, 0);
        setDirection(1, 0);
        log.fine("Field constructed");
    }
    /**Доступ к полю стека данных
     * @return Stack<Integer>*/
    public Stack<Integer> getStack() {
        log.fine("Get stack");
        return stack;
    }
    /**Получение информации о текущем
     * направлении по координате Х*/
    public int getDirectionX(){
        log.fine("Get direction X");
        return direction[0];
    }
    /**Получение информации о текущем
     * направлении по координате Y*/
    public int getDirectionY(){
        log.fine("Get direction Y");
        return direction[1];}

    /**Получение информации о текущем
     * положении по координате Х*/
    public int getActualCoordX() {
        log.fine("Get actual X");
        return actualCoord[0];
    }
    /**Получение информации о текущем
     * положении по координате Y*/
    public int getActualCoordY() {
        log.fine("Get actual Y");
        return actualCoord[1];
    }
    /**Добавить в стек число*/
    public void push(int n){
        log.fine("Push Integer");
        stack.push(n);
    }
    /**Добавить в стек ASCII код символа*/
    public void push(char c) {
        log.fine("Push Character");
        int n = c;
        stack.push(n);
    }
    /**Достать число из стека*/
    public int pop(){
        log.fine("Pop");
        if(stack.isEmpty()) return 0;
        return stack.pop();
    }
    /**Преобразовать координату Х */
    private int correctCoordX(int X){
        if (X > field.length - 1){
            X -= field.length;
        }
        if(X < 0){
            X += field.length;
        }
        log.fine("Correct coord X");
        return X;
    }
    /**Преобразовать координату Y*/
    private int correctCoordY(int Y){
        if (Y > field[0].length - 1){
            Y -= field[0].length;
        }
        if(Y < 0){
            Y += field[0].length;
        }
        log.fine("Correct coord Y");
        return Y;
    }
    /**Шаг программы*/
    public void Step(){
        actualCoord[0] += direction[0];
        actualCoord[1] += direction[1];
        actualCoord[0] = correctCoordX(actualCoord[0]);
        actualCoord[1] = correctCoordY(actualCoord[1]);
    log.fine("Step");
    }
    /**Получить элемент поля по координатам*/
    public char getElem(int x, int y){
        log.fine("Get element field");
        x = correctCoordX(x);
        y = correctCoordY(y);
        return field[x][y];
    }
    /**Установить элемент поля по координатам*/
    public void setElem(int x, int y, char elem){
        log.fine("Set elem");
        x = correctCoordX(x);
        y = correctCoordY(y);
        field[x][y] = elem;
    }
    /**Изменить направление движения курсора*/
    public void setDirection(int x, int y){
        log.fine("Set direction");
        direction[0] = x;
        direction[1] =y;
    }
    /**Установить положение курсора*/
    public void setActualCoord(int x, int y){
        log.fine("Set actual coord");
        actualCoord[0] = x;
        actualCoord[1] = y;
        actualCoord[0] = correctCoordX(actualCoord[0]);
        actualCoord[1] = correctCoordY(actualCoord[1]);
    }

}
