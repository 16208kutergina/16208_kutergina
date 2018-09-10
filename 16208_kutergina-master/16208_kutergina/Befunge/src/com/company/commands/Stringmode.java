package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Stringmode implements Command {
    private static Logger log = Logger.getLogger(Stringmode.class.getName());

    @Override
    public void execute(Redactor field) {

       char elem = ' ';
       int iter = 0;
        while(elem != '"'){
            field.Step();
            field.push(field.getElem(field.getActualCoordX(), field.getActualCoordY()));
            elem = field.getElem(field.getActualCoordX(), field.getActualCoordY());
            iter++;
        }
        if (iter == 85){
            log.log(Level.SEVERE, "Incorrect size of program");
            throw new IllegalArgumentException();
        }
        log.fine("Stringmode.execute()");
        field.pop();
    }
}
