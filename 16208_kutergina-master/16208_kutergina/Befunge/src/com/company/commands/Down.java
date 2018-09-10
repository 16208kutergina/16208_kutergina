package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Down implements Command {
    private static Logger log = Logger.getLogger(Down.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Down.execute()");
        field.setDirection(0, 1);
    }
}
