package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Left implements Command {
    private static Logger log = Logger.getLogger(Left.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Left.execute()");
        field.setDirection(-1, 0);
    }
}
