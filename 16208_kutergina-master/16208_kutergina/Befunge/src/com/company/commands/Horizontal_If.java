package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Horizontal_If implements Command {
    private static Logger log = Logger.getLogger(Horizontal_If.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Horizontal_If.execute()");
        int top = field.pop();
        if(top != 0) {
            field.setDirection(-1, 0);
        }else{
            field.setDirection(1, 0);
        }
    }
}
