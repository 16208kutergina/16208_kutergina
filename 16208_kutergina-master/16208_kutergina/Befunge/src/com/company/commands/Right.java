package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Right implements Command {
    private static Logger log = Logger.getLogger(Right.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Right.execute()");
        field.setDirection(1, 0);
    }
}
