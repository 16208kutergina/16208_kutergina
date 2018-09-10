package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class PopNothing implements Command {
    private static Logger log = Logger.getLogger(PopNothing.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("PopNothing.execute()");
        field.pop();
    }
}
