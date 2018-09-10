package com.company;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс исполняющий программу на языке Befunge
 * @autor Анна Кутергина
 */

public class Run {
    private static Logger log = Logger.getLogger(Run.class.getName());
    /**Метод запускающий программу
     * возможны ошибки исполняемых команд*/
    static public Redactor run (String filename){
        Redactor field = new Field(filename);
        char namecomand = ' ';
        while (namecomand != '@'){
            namecomand = field.getElem(field.getActualCoordX(),field.getActualCoordY());
            try {
                Factory.instance().get(String.valueOf(namecomand)).execute(field);
                field.Step();
            } catch (ArithmeticException e) {
                log.log(Level.SEVERE,"Devide to 0");
                System.out.println("Devide to 0");
                return null;
            } catch (IllegalArgumentException e) {
                log.log(Level.SEVERE,"Syntax error");
                System.out.println("Syntax error");
                return null;
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error 404");
                System.out.println("Error 404");
            return null;
            }
        }
        log.fine("Program finish");
        return field;
    }

}
