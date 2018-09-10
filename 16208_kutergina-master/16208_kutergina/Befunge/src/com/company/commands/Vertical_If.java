package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Vertical_If implements Command {
    private static Logger log = Logger.getLogger(Vertical_If.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Vertical_If.execute()");
        int top = field.pop();
        if(top != 0) {
            field.setDirection(0, -1);
        }else{
            field.setDirection(0, 1);
        }
    }
}
