package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Not implements Command {
    private static Logger log = Logger.getLogger(Not.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Not.execute()");
        if(field.pop() != 0){
            field.push(0);
        }else{
            field.push(1);
        }
    }
}
