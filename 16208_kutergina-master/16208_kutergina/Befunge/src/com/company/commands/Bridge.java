package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Bridge implements Command {
    private static Logger log = Logger.getLogger(Bridge.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Bridge.execute()");
        field.Step();
    }
}
